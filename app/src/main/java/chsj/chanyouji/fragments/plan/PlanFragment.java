package chsj.chanyouji.fragments.plan;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.BaseFragment;
import chsj.chanyouji.fragments.plan.entity.PlanEntity;
import chsj.chanyouji.fragments.plan.plan_adapter.PlanAdapter;


public class PlanFragment extends BaseFragment implements Callback.CommonCallback<String>, AdapterView.OnItemClickListener, PullToRefreshBase.OnPullEventListener<ListView> {

    PullToRefreshListView ptrListViwe;
    private final String PLAN_URL = "https://chanyouji.com/api/destinations.json";
    PlanAdapter adapter;
    //    攻略的城市列表
    private List<PlanEntity> planEntities;

    public PlanFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        x.Ext.init(getActivity().getApplication());
       // MyLog.isLog = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_fragment, container, false);
        loadData();

        ptrListViwe = (PullToRefreshListView) view.findViewById(R.id.plan_ptrlistview);
        ptrListViwe.setOnPullEventListener(this);
        planEntities = new LinkedList<PlanEntity>();
        adapter = new PlanAdapter(getActivity(), planEntities);
        adapter.setOnClickListener(this);
        ptrListViwe.setAdapter(adapter);
        return view;
    }

    private void loadData() {
        RequestParams requestParams = new RequestParams(PLAN_URL);
        FakeX509TrustManager.allowAllSSL();
        x.http().get(requestParams, this);
    }


    @Override
    public String getFragmentTitle() {
        return "攻略";
    }


    @Override
    public void onSuccess(String str) {
        PlanEntity planEntity;
        if (str != null) {

            try {
                if (planEntities!=null){
                    planEntities.clear();
                }
                JSONArray array = new JSONArray(str);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject json = array.getJSONObject(i);
                    planEntity = new PlanEntity();
                    planEntity.parseJSON(json);
                    planEntities.add(planEntity);

                }
//                请求到数据刷新适配器
                adapter.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ptrListViwe.onRefreshComplete();

    }

    @Override
    public void onError(Throwable throwable, boolean b) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCancelled(CancelledException e) {

    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        int listPosition= (int) view.findViewById(R.id.plan_country_gridview).getTag();
        int listPosition = (int) parent.getTag();

        int countryID = planEntities.get(listPosition).getDestinatonses().get(position).getId();
        Intent intent = new Intent(getActivity(), InfoStrategyActivity.class);
        intent.putExtra("id", countryID);
        startActivity(intent);


    }

    @Override
    public void onPullEvent(PullToRefreshBase<ListView> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
        loadData();

    }
}

package chsj.chanyouji.fragments.traveldiary;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.BaseFragment;
import chsj.chanyouji.fragments.traveldiary.adapter.MyPFListViewAdapter;
import chsj.chanyouji.fragments.traveldiary.model.Trips;
import chsj.chanyouji.fragments.traveldiary.tools.FakeX509TrustManager;


public class TraveldiaryFragment extends BaseFragment {


    //首页数据
    private List<Trips> datas;

    //首页pullyorfersh的适配器
    private MyPFListViewAdapter adapter;


    private PullToRefreshListView listView;

    private static int pageId;


    public TraveldiaryFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datas = new ArrayList<Trips>();
        adapter = new MyPFListViewAdapter(getActivity().getApplicationContext(), datas);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_traveldiary, container, false);

        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_traveldiary_pull_to_list_view);
        listView.setAdapter(adapter);
        initData();
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {


            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageId = 1;
                final String requetUrl = String.format(TravelConstant.TRIPS_URL, pageId);
                datas.clear();
                loadTrips(requetUrl);
            }

            /**
             * 上拉加载更多
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {


                pageId++;
                String url = String.format(TravelConstant.TRIPS_URL, pageId);
                loadTrips(url);
            }
        });

        return view;
    }

    private void initData() {


        pageId = 1;
        final String requetUrl = String.format(TravelConstant.TRIPS_URL, pageId);
        datas.clear();
        loadTrips(requetUrl);
    }

    private void loadTrips(String requetUrl) {
        // 解决访问https网站访问被限制
        FakeX509TrustManager.allowAllSSL();
        RequestParams params = new RequestParams(requetUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONArray array = new JSONArray(s);

                    int len = array.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject object = array.getJSONObject(i);
                        Trips trips = new Trips(0);
                        trips.parseJSON(object);
                        datas.add(trips);
                    }

                    adapter.notifyDataSetChanged();
                    listView.onRefreshComplete();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }

        });
    }


    @Override
    public String getFragmentTitle() {
        return "旅行日记";
    }


}

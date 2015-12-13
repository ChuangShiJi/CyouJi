package chsj.chanyouji.fragments.plan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import chsj.chanyouji.fragments.plan.entity.PlanInfoStrategyEntity;
import chsj.chanyouji.fragments.plan.plan_adapter.PlanInfoStrategyAdapter;

public class InfoStrategyActivity extends AppCompatActivity implements Callback.CommonCallback<String>, View.OnClickListener, PullToRefreshBase.OnPullEventListener<ListView> {
    private static final String INFO_STRATEGY_URL = "https://chanyouji.com/api/destinations/%d.json";
    private int conutryID;
    private List<PlanInfoStrategyEntity> datas;
    private PullToRefreshListView ptrlistView;
    private PlanInfoStrategyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_strategy_activity_info);
        conutryID = getIntent().getIntExtra("id", 55);
        ptrlistView = (PullToRefreshListView) findViewById(R.id.plan_info_strategy_ptrlistview);
        datas = new LinkedList<PlanInfoStrategyEntity>();

        adapter = new PlanInfoStrategyAdapter(this, datas);
        adapter.setOnClickListener(this);
        ptrlistView.setAdapter(adapter);
        ptrlistView.setOnPullEventListener(this);
        x.Ext.init(getApplication());
        loadData();


    }

    private void loadData() {
        FakeX509TrustManager.allowAllSSL();
        RequestParams requestParams = new RequestParams(String.format(INFO_STRATEGY_URL, conutryID));
        x.http().get(requestParams, this);
    }


    @Override
    public void onSuccess(String o) {
        if (o != null) {
            try {
                if (datas != null) {
                    datas.clear();
                }
                PlanInfoStrategyEntity entity;
                JSONArray array = new JSONArray(o);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject json = array.getJSONObject(i);
                    entity = new PlanInfoStrategyEntity();
                    entity.parseJSON(json);
                    datas.add(entity);
                }
                ptrlistView.onRefreshComplete();
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable throwable, boolean b) {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelled(CancelledException e) {
    }

    @Override
    public void onFinished() {
    }

    public void operate(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.plan_info_strategy_back:
                    this.finish();
                    break;
                case R.id.plan_info_strategy_share:
                    Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //监听adapter四个TextView的点击事件
    @Override
    public void onClick(View v) {
        if (v != null) {
            int position = (int) v.getTag();

            int id = v.getId();
            Intent intent = null;
            switch (id) {

                case R.id.plan_strategy_tips:
                    break;
                case R.id.plan_strategy_trip:
                    intent = new Intent(this, TripActivity.class);

                    break;

                case R.id.plan_strategy_travel:
                    break;
                case R.id.plan_strategy_special:
                    break;
                case R.id.plan_strategy_download:
                    Toast.makeText(this, "下载", Toast.LENGTH_SHORT).show();
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }
        }
    }

    //    下拉刷新
    @Override
    public void onPullEvent(PullToRefreshBase<ListView> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
        loadData();
    }
}

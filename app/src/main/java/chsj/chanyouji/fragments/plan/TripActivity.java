package chsj.chanyouji.fragments.plan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import chsj.chanyouji.fragments.plan.entity.TripEntity;
import chsj.chanyouji.fragments.plan.plan_adapter.TripAdapter;


public class TripActivity extends AppCompatActivity implements PullToRefreshBase.OnPullEventListener<ListView>, Callback.CommonCallback<String>, View.OnClickListener {
    private final String TRIP_URL = "https://chanyouji.com/api/destinations/plans/%d.json?page=%d";
    private PullToRefreshListView ptrListView;
    private int id = 55;
    private int page = 1;
    private List<TripEntity> datas;
    private TripAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_activity);
        ptrListView = (PullToRefreshListView) findViewById(R.id.trip_ptrlistview);
        datas = new LinkedList<TripEntity>();
        adapter = new TripAdapter(this, datas);
        adapter.setClickListener(this);
        ptrListView.setAdapter(adapter);
        loadData();
        ptrListView.setOnPullEventListener(this);

    }

    private void loadData() {
        x.Ext.init(getApplication());
        FakeX509TrustManager.allowAllSSL();
        RequestParams requestParams = new RequestParams(String.format(TRIP_URL, id, page));
        x.http().get(requestParams, this);
    }

    //回退设置
    public void operate(View view) {
        finish();
    }

    @Override
    public void onPullEvent(PullToRefreshBase<ListView> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
        loadData();

    }

    @Override
    public void onSuccess(String o) {
        if (o != null) {
            TripEntity tripEntity;
            if (datas != null) {
                datas.clear();
            }
            try {
                JSONArray array = new JSONArray(o);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject json = array.getJSONObject(i);
                    tripEntity = new TripEntity();
                    tripEntity.parseJSON(json);
                    datas.add(tripEntity);

                }
                ptrListView.onRefreshComplete();
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

    @Override
    public void onClick(View v) {
        if (v != null) {
            TripEntity tripEntity = (TripEntity) v.getTag();
//             点击图片跳转到相应的游记
            Intent intent = new Intent(this, TravelNoteActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("tripEntity", tripEntity);
            intent.putExtras(bundle);
            startActivity(intent);


        }
    }


}

package chsj.chanyouji.fragments.plan;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import chsj.chanyouji.fragments.plan.entity.TripNoteEntity;
import chsj.chanyouji.fragments.plan.plan_adapter.TravelNoteAdapter;

public class TravelNoteActivity extends AppCompatActivity implements Callback.CommonCallback<String>, AbsListView.OnScrollListener {
    private TripEntity tripEntity;
    private TextView addTextView;
    private RelativeLayout relativeLayout;
    private final String TRAVELNOTE_URL = "https://chanyouji.com/api/plans/%d.json";
    private ListView listView;
    private List<TripNoteEntity> datas;
    private TravelNoteAdapter adapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_note_activity);
        addTextView = (TextView) findViewById(R.id.travel_note_add_textview);
        tripEntity = getIntent().getExtras().getParcelable("tripEntity");
        listView = (ListView) findViewById(R.id.travel_note_listview);
        relativeLayout = (RelativeLayout) findViewById(R.id.travel_note_relativelayout);
        listView.setOnScrollListener(this);
        datas = new LinkedList<TripNoteEntity>();
        adapter = new TravelNoteAdapter(this, datas);
        initHeadView();
        listView.setAdapter(adapter);

        loadData();

    }

    private void loadData() {
        x.Ext.init(getApplication());
        FakeX509TrustManager.allowAllSSL();
        RequestParams requestParams = new RequestParams(String.format(TRAVELNOTE_URL, tripEntity.getId()));
        x.http().get(requestParams, this);
    }


    @Override
    public void onSuccess(String o) {
        if (o != null) {
            try {
                TripNoteEntity tripNoteEntity;
//                加载listView的headView

                JSONObject jsonObject = new JSONObject(o);
                JSONArray array = jsonObject.getJSONArray("plan_days");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject json = array.getJSONObject(i);
                    tripNoteEntity = new TripNoteEntity();
                    tripNoteEntity.parseJSON(json);
                    datas.add(tripNoteEntity);
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    //加载listVIew的HeadVIeW
    private void initHeadView() {
        View view = LayoutInflater.from(this).inflate(R.layout.trip_item_layout, null);
        TextView description = (TextView) view.findViewById(R.id.trip_description);
        ImageView imageView = (ImageView) view.findViewById(R.id.trip_imageview);
        TextView daycount = (TextView) view.findViewById(R.id.trip_plan_days_count);
        TextView nodeCount = (TextView) view.findViewById(R.id.trip_plan_nodes_count);
        TextView name = (TextView) view.findViewById(R.id.trip_name);
        description.setText(tripEntity.getDescription());
        daycount.setText(tripEntity.getPlan_days_count() + "天");
        nodeCount.setText(tripEntity.getPlan_nodes_count() + "个旅行地");
        name.setText(tripEntity.getName());
        x.image().bind(imageView, tripEntity.getImage_url());
        listView.addHeaderView(view);

    }

    @Override
    public void onError(Throwable throwable, boolean b) {
        Log.d("log", throwable.toString());
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
                case R.id.travel_note_back:
                    finish();
                    break;
                case R.id.travel_note_collect:
                    Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.travel_note_share:
                    Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        Log.d("item", firstVisibleItem + "  firstVIsibleitem   " + visibleItemCount + "visibleItemCount   " + totalItemCount);
        if (firstVisibleItem > 0) {
            addTextView.setVisibility(View.VISIBLE);
            addTextView.setText("DAY" + firstVisibleItem);
            addTextView.setBackgroundColor(Color.WHITE);
            relativeLayout.setBackgroundColor(Color.BLUE);
//            View item= (View) listView.getItemAtPosition(firstVisibleItem);
//            listView.getSelectedView().getX();
            View item = adapter.getView(firstVisibleItem, null, null);

//            Log.d("log", item.getMeasuredHeight()+"  item");

//            listView.getChildAt(firstVisibleItem).get
        } else {
            addTextView.setVisibility(View.INVISIBLE);
            addTextView.setBackgroundColor(Color.argb(0, 6, 6, 6));
            relativeLayout.setBackgroundColor(Color.argb(0, 6, 6, 6));
        }
    }


}

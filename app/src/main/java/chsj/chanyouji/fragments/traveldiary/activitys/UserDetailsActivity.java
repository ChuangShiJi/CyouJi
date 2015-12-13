package chsj.chanyouji.fragments.traveldiary.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.traveldiary.TravelConstant;
import chsj.chanyouji.fragments.traveldiary.adapter.MyPFListViewAdapter;
import chsj.chanyouji.fragments.traveldiary.model.Trips;

public class UserDetailsActivity extends Activity {


    @ViewInject(R.id.activity_user_detail_user_name_1)
    private TextView txtName1;

    @ViewInject(R.id.activity_user_detail_user_name_2)
    private TextView txtName2;

    @ViewInject(R.id.activity_user_details_user_icon)
    private ImageView iconUser;

    @ViewInject(R.id.activity_user_detail_trip_counts)
    private TextView txtTripCounts;

    @ViewInject(R.id.activity_user_detail_pf_list_view)
    private PullToRefreshListView mPRListView;

    @ViewInject(R.id.activity_user_detail_user_sex)
    private ImageView imgSex;

    private List<Trips> datas;
    private MyPFListViewAdapter adapter;

    private static int pageId;
    private static int userId;
    Trips trips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_details);


        x.view().inject(this);
        Bundle bundle = getIntent().getExtras();
        trips = (Trips) bundle.getSerializable("trip");
        userId = trips.getUser_id();

        datas = new ArrayList<Trips>();
        adapter = new MyPFListViewAdapter(this, datas);
        mPRListView.setAdapter(adapter);

        initData();
        mPRListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPRListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {


            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageId = 1;
                String url = String.format(TravelConstant.USER_DETAILS, userId, pageId);
                datas.clear();
                loadUserDatils(url);
            }

            /**
             * 上拉加载更多
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {


                pageId++;
                String url = String.format(TravelConstant.USER_DETAILS, userId, pageId);
                loadUserDatils(url);
            }
        });
    }

    private void initData() {

        pageId = 1;
        String url = String.format(TravelConstant.USER_DETAILS, userId, pageId);
        datas.clear();
        loadUserDatils(url);
    }

    private void loadUserDatils(String url) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                try {

                    JSONObject jsonObject = new JSONObject(s);


                    //设置用户基本信息
                    txtName1.setText(jsonObject.getString("name"));
                    txtName2.setText(jsonObject.getString("name"));
                    txtTripCounts.setText(jsonObject.getString("trips_count") + "篇游记");

                    //设置性别的图片
                    int gender = jsonObject.getInt("gender");
                    switch (gender) {
                        case 0:
                            imgSex.setImageResource(R.mipmap.female);
                            break;
                        case 1:
                            imgSex.setImageResource(R.mipmap.male);
                            break;
                    }

                    //给下边儿的pulltorefreshlistview填充数据
                    JSONArray jsonArray = jsonObject.getJSONArray("trips");
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        Trips trips = new Trips(1);
                        trips.parseJSON(jsonArray.getJSONObject(i));
                        datas.add(trips);
                    }
                    adapter.notifyDataSetChanged();

                    mPRListView.onRefreshComplete();
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

}

package chsj.chanyouji.fragments.traveldiary.activitys;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import chsj.chanyouji.fragments.traveldiary.adapter.MyTripDetailsAdapter;
import chsj.chanyouji.fragments.traveldiary.model.TripDetails.Nodes;
import chsj.chanyouji.fragments.traveldiary.model.TripDetails.Notes;
import chsj.chanyouji.fragments.traveldiary.model.TripDetails.TripDays;
import chsj.chanyouji.fragments.traveldiary.model.TripDetails.TripDetails;
import chsj.chanyouji.fragments.traveldiary.widgets.FullListView;
import chsj.chanyouji.fragments.traveldiary.widgets.MyScrollView;

public class TripDetailsActivity extends Activity implements MyScrollView.OnScrollListener {


    /**
     * 游记id
     */
    private double tripId;

    /**
     * 背景大图地址
     */
    private String coverPhotoUrl;

    private List<TripDays> tripDayses;

    private TripDetails tripDetails;

    @ViewInject(R.id.fl_img)
    private FrameLayout rlayout;

    @ViewInject(R.id.ll_float)
    private LinearLayout ll_float;

    @ViewInject(R.id.ll_date_1)
    private LinearLayout ll_date_1;

    @ViewInject(R.id.ll_date_2)
    private LinearLayout ll_date_2;

    @ViewInject(R.id.myScrollView)
    private MyScrollView myScrollView;


    @ViewInject(R.id.ll_bar)
    private LinearLayout llBar;

    private int searchLayoutTop;

    @ViewInject(R.id.activity_trip_details_full_listview)
    private FullListView fullListView;

    private List<Nodes> dataNodes = new ArrayList<Nodes>();
    private List<Notes> dataNotes = new ArrayList<Notes>();

    //-------------------------------
    /**
     * 个人信息
     *
     * @param savedInstanceState
     */

    @ViewInject(R.id.activity_tripdetails_cover_photo)
    private ImageView imgCover;

    @ViewInject(R.id.activity_trip_detail_user_icon)
    private ImageView imgUserIcon;

    @ViewInject(R.id.activity_trip_detail_title)
    private TextView title;

    @ViewInject(R.id.activity_trip_detail_content)
    private TextView content;


    //--------------------------------------

    private MyTripDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_trip_details);


        tripId = getIntent().getDoubleExtra("tripId", 0);
        coverPhotoUrl = getIntent().getStringExtra("coverPhoto");
        tripDetails = new TripDetails();
        x.view().inject(this);
        myScrollView.setOnScrollListener(this);

        tripDayses = new ArrayList<TripDays>();
        adapter = new MyTripDetailsAdapter(TripDetailsActivity.this, dataNotes);

        fullListView.setAdapter(adapter);

        //加载数据
        initData();

        //initJD();
    }

    private void initJD() {
        List<String> str = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {

            str.add(i + "==");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, str);
        fullListView.setAdapter(adapter);

    }


    private void initData() {

        final String url = String.format(TravelConstant.TRIP_DETAIL, (int) tripId + "");


        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                try {
                    JSONObject tripDetailsJson = new JSONObject(s);
                    tripDetails.parseJSON(tripDetailsJson);


                    x.image().bind(imgCover, coverPhotoUrl);
                    x.image().bind(imgUserIcon, tripDetails.getUser_image());
                    title.setText(tripDetails.getName());

                    int days = tripDetails.getTripDayses().size();
                    content.setText(tripDetails.getStart_date().replace('-', '.') + "|" + days + "天," + tripDetails.getPhotos_count() + "图");
                    tripDayses = tripDetails.getTripDayses();
                    int daysCount = tripDayses.size();
                    List<Notes> notess = new ArrayList<Notes>();
                    for (int i = 0; i < daysCount; i++) {

                        List<Nodes> nodes = tripDayses.get(i).getNodes();
                        int d = nodes.size();

                        for (int j = 0; j < d; j++) {

                            Nodes nodes1 = nodes.get(j);
                            dataNodes.add(nodes1);
                            List<Notes> notes = nodes1.getNotes();
                            notess.addAll(notes);

                        }
                    }

                    dataNotes.addAll(notess);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable, boolean b) {

                Log.e("details", throwable.getMessage() + url);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    //------------------------------


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            searchLayoutTop = rlayout.getBottom();
        }
    }

    @Override
    public void onScroll(int scrollY) {

        //dp-->px
        float scale = this.getResources().getDisplayMetrics().density;
        int px = (int) (50 * scale + 0.5f);

        if (scrollY >= searchLayoutTop - px) {
            if (ll_float.getParent() != ll_date_2) {
                ll_date_2.setVisibility(View.VISIBLE);
                ll_date_1.removeView(ll_float);
                ll_date_2.addView(ll_float);
                llBar.setBackgroundColor(Color.parseColor("#F13C2AFF"));
            }
        } else {
            if (ll_float.getParent() != ll_date_1) {
                ll_date_2.removeView(ll_float);
                ll_date_1.addView(ll_float);
                ll_date_2.setVisibility(View.INVISIBLE);
                llBar.setBackgroundColor(Color.parseColor("#00000000"));
            }
        }
    }

}

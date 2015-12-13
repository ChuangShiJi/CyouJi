package chsj.chanyouji.fragments.tools.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import chsj.chanyouji.Constants;
import chsj.chanyouji.R;
import chsj.chanyouji.adapter.MyFragmentPagerAdapter;
import chsj.chanyouji.fragments.BaseFragment;
import chsj.chanyouji.fragments.tools.ExpandableListViewCallback;
import chsj.chanyouji.fragments.tools.ToolFragment;
import chsj.chanyouji.fragments.tools.entity.City;
import chsj.chanyouji.fragments.tools.fragments.ForeignFragment;
import chsj.chanyouji.fragments.tools.fragments.HomeLandFragment;

public class SelectCityActivity extends AppCompatActivity
        implements View.OnClickListener,ExpandableListViewCallback {

    @ViewInject(R.id.select_activity_view_pager)
    private ViewPager viewPager;

    @ViewInject(R.id.select_activity_back)
    private RelativeLayout back;

    @ViewInject(R.id.select_activity_tab)
    private TabLayout tabLayout;

    private List<BaseFragment> fragments;
    private MyFragmentPagerAdapter pagerAdapter;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        manager = LocalBroadcastManager.getInstance(getApplicationContext());
        //使用 xutils
        x.view().inject(this);
        back.setOnClickListener(this);
        fragments = new ArrayList<>();
        initFragments();
    }

    private void initFragments() {
        //初始化fragment
        fragments.add(new ForeignFragment());

        fragments.add(new HomeLandFragment());

        FragmentManager fm = getSupportFragmentManager();

        pagerAdapter = new MyFragmentPagerAdapter(fm,fragments);
        viewPager.setAdapter(pagerAdapter);
//        实现联动：
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    //使用本地广播管理器
    private LocalBroadcastManager manager;
    //回去回调数据
    @Override
    public void sendMesssageCallback(Object object) {
        if (object!=null){
            //获取数据  将数据返回给  toolfragment数据  并刷新
            if (object instanceof City.ChildrenEntity){

                City.ChildrenEntity city = (City.ChildrenEntity) object;
                //返回数据后  开始 发送广播：
                Intent intent =  new Intent(Constants.SELECT_CITY_BROADCAST_ACTION);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.DATA_EXTRA_INTENT,city);
                intent.putExtras(bundle);

                manager.sendBroadcast(intent);
            }
            finish();//开始  发送广播 并结束当前  activity
        }
    }

}

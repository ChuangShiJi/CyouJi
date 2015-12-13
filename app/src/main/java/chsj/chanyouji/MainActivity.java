package chsj.chanyouji;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import chsj.chanyouji.adapter.MyFragmentPagerAdapter;
import chsj.chanyouji.fragments.BaseFragment;
import chsj.chanyouji.fragments.plan.PlanFragment;
import chsj.chanyouji.fragments.tools.ToolFragment;
import chsj.chanyouji.fragments.traveldiary.TraveldiaryFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private List<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.main_tab_bar);
        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        viewPager.setOffscreenPageLimit(3);
        //初始化 数据
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();

        //初始化
        fragments.add(new TraveldiaryFragment());
        fragments.add(new PlanFragment());
        fragments.add(new ToolFragment());

        //定义适配器：
        MyFragmentPagerAdapter pagerAdapter =
                new MyFragmentPagerAdapter(
                        getSupportFragmentManager(),
                        fragments
                );

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

}

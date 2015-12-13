package chsj.chanyouji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import chsj.chanyouji.fragments.BaseFragment;

/**
 * ProjectName : chsj.chanyouji.adapter
 * Created by : Chsj
 * Email : zhaoq_hero163.com
 * On 2015/11/9 // 17:45
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    private List<BaseFragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (fragments!=null){
            ret = fragments.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String ret = null;

        BaseFragment baseFragment = fragments.get(position);

        ret = baseFragment.getFragmentTitle();

        return ret;
    }
}

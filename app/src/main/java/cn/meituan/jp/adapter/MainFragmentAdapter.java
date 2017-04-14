package cn.meituan.jp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cn.meituan.jp.fragment.HomePageFragment;
import cn.meituan.jp.fragment.MineFragment;
import cn.meituan.jp.fragment.OrderFragment;

/**
 * Created by 11608 on 2017/4/13.
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position==0){
            fragment=new HomePageFragment();
            return fragment;
        }
        if (position==1){
            fragment=new OrderFragment();
            return fragment;
        }
        if (position==2){
            fragment=new MineFragment();
            return fragment;
        }
        return new HomePageFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}

package cn.meituan.jp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.meituan.jp.R;
import cn.meituan.jp.fragment.BusinessInfoFragment;
import cn.meituan.jp.fragment.LoginFragment;
import cn.meituan.jp.fragment.OrderDishFragment;
import cn.meituan.jp.fragment.QuickLoginFragment;
import cn.meituan.jp.fragment.ShopEvaluationFragment;

/**
 * Created by 11608 on 2017/4/14.
 */

public class TabIndicatorFragmentPagerShopAdapter extends com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private Context context;
    private String[] title;
    private Fragment fragment;
    private Bundle bundle;


    public TabIndicatorFragmentPagerShopAdapter(FragmentManager fragmentManager, Context context, String[] title) {
        super(fragmentManager);
        this.context = context;
        this.title = title;

    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_login_register_tab, container, false);
        }
        TextView tvTabText = (TextView) convertView.findViewById(R.id.tv_tab_text);
        tvTabText.setText(title[position]);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        switch (position) {
            case 0:
                fragment = new OrderDishFragment();
                bundle = new Bundle();
                bundle.putInt("position", position);
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new ShopEvaluationFragment();
                bundle = new Bundle();
                bundle.putInt("position", position);
                fragment.setArguments(bundle);
                break;
            case 2:
                fragment = new BusinessInfoFragment();
                bundle = new Bundle();
                bundle.putInt("position", position);
                fragment.setArguments(bundle);
                break;
            default:
                break;
        }
        return fragment;
    }
}

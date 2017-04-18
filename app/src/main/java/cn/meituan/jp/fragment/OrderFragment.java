package cn.meituan.jp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.LayoutBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.activity.LoginRegisterActivity;
import cn.meituan.jp.adapter.TabIndicatorFragmentPagerOrderEvaluationAdapter;

/**
 * Created by 11608 on 2017/4/13.
 */

public class OrderFragment extends BaseFragment {

    /**
     * Tab 标题
     *
     * @param savedInstanceState
     */
    private final String[] TITLES = new String[]{"全部订单", "待评价"};
    @Bind(R.id.ll_login_register)
    LinearLayout llLoginRegister;
    @Bind(R.id.indicator)
    FixedIndicatorView indicator;
    @Bind(R.id.vp_orders_waiting_evaluation)
    ViewPager vpOrdersWaitingEvaluation;
    @Bind(R.id.btn_login_register)
    Button btnLoginRegister;

    private IndicatorViewPager indicatorViewPager;
    private IndicatorViewPager.IndicatorFragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_login, container, false);
        ButterKnife.bind(this, view);
        if (-1 != UserSharedPreference.getInstance().getIsLogined()) {

            llLoginRegister.setVisibility(View.GONE);
            btnLoginRegister.setClickable(false);
        }
        initIndicator();
        return view;
    }

    public void initIndicator() {
        float unSelectSize = 15;

        float selectSize = 15;

        OnTransitionTextListener listener = new OnTransitionTextListener() {
            @Override
            public TextView getTextView(View tabItemView, int position) {
                return (TextView) tabItemView.findViewById(R.id.tv_tab_text);
            }
        };

        indicator.setOnTransitionListener(listener.setColor(getResources().getColor(R.color.color_green_3bb4bc),
                getResources().getColor(R.color.color_black_0e1214)).setSize(selectSize, unSelectSize));
        adapter = new TabIndicatorFragmentPagerOrderEvaluationAdapter(getActivity().getSupportFragmentManager(), getActivity(), TITLES);
        //将指示器和ViewPager绑定在一起
        indicatorViewPager = new IndicatorViewPager(indicator, vpOrdersWaitingEvaluation);
        indicatorViewPager.setAdapter(adapter);
        vpOrdersWaitingEvaluation.setOffscreenPageLimit(2);
        indicator.setScrollBar(new LayoutBar(getActivity(), R.layout.indicator_scroll_bar, ScrollBar.Gravity.BOTTOM));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_login_register)
    public void toLoginRegister() {
        startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
    }
}

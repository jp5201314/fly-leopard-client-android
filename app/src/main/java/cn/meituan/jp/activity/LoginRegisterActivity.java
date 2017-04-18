package cn.meituan.jp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
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
import cn.meituan.jp.adapter.TabIndicatorFragmentPagerLoginAdapter;

public class LoginRegisterActivity extends BaseActivity {

    @Bind(R.id.tv_register)
    TextView tvRegister;
    @Bind(R.id.indicator)
    FixedIndicatorView indicator;
    @Bind(R.id.vp_login_register)
    ViewPager vpLoginRegister;
    private IndicatorViewPager indicatorViewPager;
    private IndicatorViewPager.IndicatorFragmentPagerAdapter adapter;

    /**
     * Tab 标题
     *
     * @param savedInstanceState
     */
    private final String[] TITLES = new String[]{"账号密码登录", "手机号快捷登录"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        initIndicator();
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
        adapter = new TabIndicatorFragmentPagerLoginAdapter(getSupportFragmentManager(), this, TITLES);
        //将指示器和ViewPager绑定在一起
        indicatorViewPager = new IndicatorViewPager(indicator, vpLoginRegister);
        indicatorViewPager.setAdapter(adapter);
        vpLoginRegister.setOffscreenPageLimit(2);
        indicator.setScrollBar(new LayoutBar(this, R.layout.indicator_scroll_bar, ScrollBar.Gravity.BOTTOM));
    }

    @OnClick(R.id.tv_register)
    public void toRegister() {
        intoRegister();
    }
}

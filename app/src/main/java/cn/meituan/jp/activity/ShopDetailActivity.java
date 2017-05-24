package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.LayoutBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.adapter.TabIndicatorFragmentPagerShopAdapter;
import cn.meituan.jp.event.CollectionShopEvent;

public class ShopDetailActivity extends BaseActivity {


    @Bind(R.id.ib_back)
    ImageView ibBack;
    @Bind(R.id.iv_shop_search)
    ImageView ivShopSearch;
    @Bind(R.id.iv_shop_share)
    ImageView ivShopShare;
    @Bind(R.id.iv_together)
    ImageView ivTogether;
    @Bind(R.id.fl_shop_title)
    FrameLayout flShopTitle;
    @Bind(R.id.iv_shop_image)
    ImageView ivShopImage;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_notice)
    TextView tvShopNotice;
    @Bind(R.id.iv_collection)
    ImageView ivCollection;
    @Bind(R.id.indicator)
    FixedIndicatorView indicator;
    @Bind(R.id.vp_shop_info)
    ViewPager vpShopInfo;
    private IndicatorViewPager indicatorViewPager;
    private IndicatorViewPager.IndicatorFragmentPagerAdapter adapter;
    private int amount = 0;
    /**
     * Tab 标题
     *
     * @param savedInstanceState
     */
    private final String[] TITLES = new String[]{"点菜", "评价", "商家"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        getShopDetail();
        amount = UserSharedPreference.getInstance().getCollectionClickNum();
        if (amount==0){
            ivCollection.setImageResource(R.drawable.icon_collection);
        }else {
            ivCollection.setImageResource(R.drawable.icon_collection_full);
        }
    }

    private void getShopDetail() {
        Intent intent = getIntent();
        if (intent != null) {
            tvShopName.setText(intent.getStringExtra("shop_name"));
            tvShopNotice.setText(String.format(tvShopNotice.getText().toString(), tvShopName.getText().toString()));
            Picasso.with(this).load(intent.getStringExtra("shop_head_image")).resize(100, 80).centerCrop().into(ivShopImage);
        }
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

        indicator.setOnTransitionListener(listener.setColor(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.color_black_0e1214)).setSize(selectSize, unSelectSize));
        adapter = new TabIndicatorFragmentPagerShopAdapter(getSupportFragmentManager(), this, TITLES);
        //将指示器和ViewPager绑定在一起
        indicatorViewPager = new IndicatorViewPager(indicator, vpShopInfo);
        indicatorViewPager.setAdapter(adapter);
        vpShopInfo.setOffscreenPageLimit(2);
        indicator.setScrollBar(new LayoutBar(this, R.layout.indicator_scroll_bar, ScrollBar.Gravity.BOTTOM));
    }

    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }
    @OnClick(R.id.iv_collection)
    public void toColleaction(){
        if (UserSharedPreference.getInstance().getIsLogined()==-1){
            toLoginRegister();
        }else {
            amount++;
            if(amount%2==0){
                amount=0;
                ivCollection.setImageResource(R.drawable.icon_collection);
                EventBus.getDefault().post(new CollectionShopEvent(getIntent().getStringExtra("shop_name")));
            }else {
                UserSharedPreference.getInstance().setCollectionClickNum(amount);
                ivCollection.setImageResource(R.drawable.icon_collection_full);
                EventBus.getDefault().post(new CollectionShopEvent(getIntent().getStringExtra("shop_name")));
            }
        }
    }
}

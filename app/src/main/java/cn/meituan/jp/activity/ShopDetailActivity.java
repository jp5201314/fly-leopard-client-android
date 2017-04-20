package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.LayoutBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.adapter.TabIndicatorFragmentPagerShopAdapter;
import cn.meituan.jp.event.SelectGoodsEvent;
import cn.meituan.jp.event.ShoppingCartSumEvent;

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
    @Bind(R.id.iv_shopping_cart)
    ImageView ivShoppingCart;
    @Bind(R.id.tv_shopping_cate_sum)
    TextView tvShoppingCateSum;
    @Bind(R.id.ll_shopping_cate_sum)
    LinearLayout llShoppingCateSum;
    @Bind(R.id.tv_to_pay)
    TextView tvToPay;
    @Bind(R.id.btn_to_pay)
    Button btnToPay;
    @Bind(R.id.fl_purchased_food)
    FrameLayout flPurchasedFood;
    private IndicatorViewPager indicatorViewPager;
    private IndicatorViewPager.IndicatorFragmentPagerAdapter adapter;
    private static int amount = 0;
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
        EventBus.getDefault().register(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        getShopDetail();


    }

    private void getShopDetail() {
        Intent intent = getIntent();
        if (intent != null) {
            tvShopName.setText(intent.getStringExtra("shop_name"));
            tvShopNotice.setText(String.format(tvShopNotice.getText().toString(), tvShopName.getText().toString()));
            Picasso.with(this).load(intent.getStringExtra("shop_head_image")).resize(100, 80).centerCrop().into(ivShopImage);
        }
        initIndicator();
        setAmount();
    }

    private void setAmount() {
        amount = UserSharedPreference.getInstance().getShoppingCartAmount();
        if (amount != 0) {
            tvShoppingCateSum.setText(amount+"");
            llShoppingCateSum.setVisibility(View.VISIBLE);
            ivShoppingCart.setImageResource(R.drawable.icon_shopping_cart_normal);
            tvToPay.setVisibility(View.GONE);
            btnToPay.setVisibility(View.VISIBLE);
        }
        EventBus.getDefault().post(new ShoppingCartSumEvent(amount));
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
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPurchaseShop(SelectGoodsEvent event) {
        amount += event.getPrice();
        UserSharedPreference.getInstance().setShoppingCartSum(amount);
        setAmount();
    }

    @OnClick(R.id.btn_to_pay)
    public void toPay() {
        if (getLoginStatus() == 0) {
            startActivity(new Intent(this, CommitOrderActivity.class));
        } else {
            toLoginRegister();
        }

    }
}

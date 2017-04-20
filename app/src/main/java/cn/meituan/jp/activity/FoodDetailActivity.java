package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.entity.FoodsEntity;
import cn.meituan.jp.event.SelectGoodsEvent;
import cn.meituan.jp.event.ShoppingCartSumEvent;

public class FoodDetailActivity extends BaseActivity {
    @Bind(R.id.ib_food_back)
    ImageView ibFoodBack;
    @Bind(R.id.iv_food_share)
    ImageView ivFoodShare;
    @Bind(R.id.iv_food_image)
    ImageView ivFoodImage;
    @Bind(R.id.tv_food_name)
    TextView tvFoodName;
    @Bind(R.id.tv_food_price)
    TextView tvFoodPrice;
    @Bind(R.id.btn_add_shopping_cart)
    Button btnAddShoppingCart;
    @Bind(R.id.tv_food_info)
    TextView tvFoodInfo;
    @Bind(R.id.tv_food_evaluation)
    TextView tvFoodEvaluation;
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
    private FoodsEntity entity;
    private static  int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        this.setStatusBarColor(R.color.color_black_0e1214);
        entity = getIntent().getParcelableExtra("food");

        Picasso.with(this).load(entity.getPhoto()).resize(480, 320).centerCrop().into(ivFoodImage);

        tvFoodName.setText(entity.getName());
        tvFoodInfo.setText(entity.getContent());
        tvFoodPrice.setText(String.format(tvFoodPrice.getText().toString(), entity.getPrice()));
        tvFoodEvaluation.setText(entity.getContent());
        amount = UserSharedPreference.getInstance().getShoppingCartAmount();
        if (amount !=0) {
            tvShoppingCateSum.setText(amount+"");
            llShoppingCateSum.setVisibility(View.VISIBLE);
            ivShoppingCart.setImageResource(R.drawable.icon_shopping_cart_normal);
            tvToPay.setVisibility(View.GONE);
            btnToPay.setVisibility(View.VISIBLE);
        }

    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onShoppingCartChange(ShoppingCartSumEvent event){
        amount = event.getSum();
        if (amount !=0) {
            tvShoppingCateSum.setText(amount+"");
            llShoppingCateSum.setVisibility(View.VISIBLE);
            ivShoppingCart.setImageResource(R.drawable.icon_shopping_cart_normal);
            tvToPay.setVisibility(View.GONE);
            btnToPay.setVisibility(View.VISIBLE);
        }
    }


    @OnClick(R.id.ib_food_back)
    public void setIbFoodBack() {
        finish();
    }


    @OnClick(R.id.iv_food_share)
    public void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, entity.getPhoto());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "分享给好基友吧"));
    }

    @OnClick(R.id.btn_add_shopping_cart)
    public void toAddShoppingCart() {
        EventBus.getDefault().post(new SelectGoodsEvent(entity.getName(), entity.getPrice()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
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

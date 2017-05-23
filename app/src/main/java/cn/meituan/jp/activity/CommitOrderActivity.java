package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.event.AddressEvent;
import cn.meituan.jp.net.FlyHttpRequestCallBack;

public class CommitOrderActivity extends BaseActivity {



    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_add_info)
    TextView tvAddInfo;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.fl_set_address)
    FrameLayout flSetAddress;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_food_name)
    TextView tvFoodName;
    @Bind(R.id.ib_reduce_amount)
    ImageButton ibReduceAmount;
    @Bind(R.id.tv_food_amount)
    TextView tvFoodAmount;
    @Bind(R.id.ib_increase_amount)
    ImageButton ibIncreaseAmount;
    @Bind(R.id.tv_food_sum)
    TextView tvFoodSum;
    @Bind(R.id.tv_balance)
    TextView tvBalance;
    @Bind(R.id.tv_waiting_pay_num)
    TextView tvWaitingPayNum;
    @Bind(R.id.ll_shopping_cate_sum)
    LinearLayout llShoppingCateSum;
    @Bind(R.id.btn_to_commit)
    Button btnToCommit;
    @Bind(R.id.fl_purchased_food)
    FrameLayout flPurchasedFood;
    private int price;
    private int sum;
    private static int num;
    private int foodId;
    private int balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStatusBarColor(R.color.color_black_0e1214);
        setContentView(R.layout.activity_commit_order);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tvTitle.setText("下单");
        balance = UserSharedPreference.getInstance().getBalance();
        tvName.setText(UserSharedPreference.getInstance().getUserName());
        tvPhoneNum.setText(UserSharedPreference.getInstance().getPhone());
        tvAddress.setText(UserSharedPreference.getInstance().getAddress());
        tvBalance.setText(String.format(tvBalance.getText().toString(),balance+""));

        Intent intent = getIntent();
        foodId = intent.getIntExtra("id", 0);
        tvFoodName.setText(intent.getStringExtra("name"));
        tvShopName.setText(intent.getStringExtra("shop_name"));
        price = intent.getIntExtra("price", 0);
        setAmountAndSum(num);
    }

    private void setAmountAndSum(int num) {
        sum = num * price;
        tvFoodAmount.setText( String.valueOf(num));
        tvFoodSum.setText(String.valueOf(sum));
        tvWaitingPayNum.setText(String.valueOf(sum));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.fl_set_address)
    public void toSetAddress() {
        Intent intent = new Intent(this, MyAddressActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setAddress(AddressEvent event) {
        tvName.setText(event.getName());
        tvPhoneNum.setText(event.getPhone());
        tvAddress.setText(event.getAddress());
    }

    @OnClick(R.id.ib_reduce_amount)
    public void toReduceAmount() {
        if (num > 0) {
            num--;
            setAmountAndSum(num);
        }
    }

    @OnClick(R.id.ib_increase_amount)
    public void toIncreaseAmount() {
        num++;
        setAmountAndSum(num);
    }

    @OnClick(R.id.btn_to_commit)
    public void toCommitOrderAndPay() {
        if(num==0){
            toast("请输入购买数量");
            return;
        }
        if(balance<sum){
            toast("待支付金额应小于余额");
            return;
        }
        RequestParams params = new RequestParams(this);
        params.addFormDataPart("amount", num);
        HttpRequest.post(Constant.getHost() + String.format(Api.PALCEORDER, foodId, UserSharedPreference.getInstance().getId()), params, new FlyHttpRequestCallBack() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                super.onDataSuccess(data);
                int balance = UserSharedPreference.getInstance().getBalance() - sum;
                UserSharedPreference.getInstance().setBalance(balance);
                toast("支付成功");
                startActivity(new Intent(CommitOrderActivity.this,MainActivity.class));
                finish();
            }

            @Override
            protected void onDataError(int status, JSONObject statusInfo) {
                super.onDataError(status, statusInfo);
                toast("支付失败");
            }
        });
    }

    @OnClick(R.id.ib_back)
    public void setIbBack(){
        finish();
    }
}

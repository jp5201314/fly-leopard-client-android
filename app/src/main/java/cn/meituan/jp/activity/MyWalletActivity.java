package cn.meituan.jp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;

public class MyWalletActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_add_info)
    TextView tvAddInfo;
    @Bind(R.id.iv_myWallet)
    ImageView ivMyWallet;
    @Bind(R.id.tv_money_sum)
    TextView tvMoneySum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        this.setStatusBarColor(R.color.color_black_0e1214);
        ButterKnife.bind(this);
        tvTitle.setText("我的钱包");
        tvAddInfo.setText("余额明细");
        tvAddInfo.setVisibility(View.VISIBLE);
        if (getLoginStatus()==0){
            tvMoneySum.setText(UserSharedPreference.getInstance().getBalance()+"");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }
}

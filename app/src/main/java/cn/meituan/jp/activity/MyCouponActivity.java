package cn.meituan.jp.activity;

import android.os.Bundle;

import butterknife.OnClick;
import cn.meituan.jp.R;

public class MyCouponActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        this.setStatusBarColor(R.color.color_black_0e1214);
    }

    @OnClick(R.id.ib_back)
    public void back(){
        finish();
    }

}

package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.meituan.jp.R;

public class ShopDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);

        this.setStatusBarColor(R.color.color_black_0e1214);
        getShopDetail();
    }

    private void getShopDetail(){

    }

    @OnClick(R.id.ib_back)
    public void back(){
        finish();
    }
}

package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;

public class BusinessInToActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_in_to);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        tvTitle.setText("商家入驻");
        Intent intent = new Intent(this,HomePageCarouselWebViewActivity.class);
        intent.putExtra("url","http://waimai.meituan.com/contact/contactus");
        startActivity(intent);
    }

    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }

}

package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;

public class MyShareActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);


        tvTitle.setText("我的分享");
        Intent intent = new Intent(this,HomePageCarouselWebViewActivity.class);
        intent.putExtra("url","http://api.sunflower.waimai.meituan.com/redpacket_acquire_user/grab_redpacket_page?inviteCode=36849570&passportId=202728771&userTaskId=16659903&sf_source=appmine");
        startActivity(intent);
    }

    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }

}

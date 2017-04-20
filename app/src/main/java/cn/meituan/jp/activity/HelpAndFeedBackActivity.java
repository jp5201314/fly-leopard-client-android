package cn.meituan.jp.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;

public class HelpAndFeedBackActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_feed_back);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);

        tvTitle.setText("帮助与反馈");
    }

    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }

}

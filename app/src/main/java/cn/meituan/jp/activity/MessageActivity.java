package cn.meituan.jp.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;

public class MessageActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_add_info)
    TextView tvAddInfo;
    @Bind(R.id.ll_no_message)
    LinearLayout llNoMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        this.setStatusBarColor(R.color.color_black_0e1214);
        ButterKnife.bind(this);
        tvTitle.setText("飞豹外卖");
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

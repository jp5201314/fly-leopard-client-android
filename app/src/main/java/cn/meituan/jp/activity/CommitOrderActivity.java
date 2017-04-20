package cn.meituan.jp.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import cn.meituan.jp.R;

public class CommitOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_order);

        this.setStatusBarColor(R.color.color_black_0e1214);

        ButterKnife.bind(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

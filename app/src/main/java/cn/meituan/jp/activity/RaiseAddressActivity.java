package cn.meituan.jp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.meituan.jp.R;

public class RaiseAddressActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_add_info)
    TextView tvAddInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_address);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        tvTitle.setText("新增收货地址");
        tvAddInfo.setText("保存");
        tvAddInfo.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

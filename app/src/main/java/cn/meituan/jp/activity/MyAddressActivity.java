package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.adapter.ReceivedAddressAdapter;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class MyAddressActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_add_info)
    TextView tvAddInfo;
    @Bind(R.id.rv_address)
    RecyclerView rvAddress;
    @Bind(R.id.ll_address_add)
    LinearLayout llAddressAdd;
    @Bind(R.id.ptr)
    PtrClassicFrameLayout ptr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        this.setStatusBarColor(R.color.color_black_0e1214);
        ButterKnife.bind(this);
        tvTitle.setText("我的收货地址");
        tvAddInfo.setText("管理");
        tvAddInfo.setVisibility(View.VISIBLE);
        rvAddress.setLayoutManager(new LinearLayoutManager(this));
        rvAddress.setAdapter(new ReceivedAddressAdapter(this));
    }


    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ll_address_add)
    public void toRaiseAddress() {
        startActivity(new Intent(this, RaiseAddressActivity.class));
    }
}

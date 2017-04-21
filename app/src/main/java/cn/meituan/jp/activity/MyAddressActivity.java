package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.R;
import cn.meituan.jp.adapter.ReceivedAddressAdapter;
import cn.meituan.jp.entity.UserEntity;
import cn.meituan.jp.net.FlyHttpRequestCallBack;
import cn.meituan.jp.utils.RecyclerViewDivider;

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
    FrameLayout llAddressAdd;
    @Bind(R.id.btn_add_address)
    Button btnAddAddress;
    private UserEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        this.setStatusBarColor(R.color.color_black_0e1214);
        ButterKnife.bind(this);
        tvTitle.setText("我的收货地址");
        tvAddInfo.setText("管理");
        tvAddInfo.setVisibility(View.VISIBLE);
        rvAddress.addItemDecoration(new RecyclerViewDivider(
                this, LinearLayoutManager.VERTICAL));
       /* rvAddress.addItemDecoration(new RecyclerViewDivider(
                this, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.color_black_0e1214)));*/
        getAddress();
    }

    private void getAddress() {
        HttpRequest.get(Constant.getHost() + String.format(Api.PERSONALINFO, getIntent().getIntExtra("id", 0)), new FlyHttpRequestCallBack() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                super.onDataSuccess(data);
                entity = JSON.parseObject(data.toJSONString(), UserEntity.class);
                rvAddress.setLayoutManager(new LinearLayoutManager(MyAddressActivity.this));
                rvAddress.setAdapter(new ReceivedAddressAdapter(MyAddressActivity.this, entity));
            }

            @Override
            protected void onDataError(int status, JSONObject statusInfo) {
                super.onDataError(status, statusInfo);
            }
        });

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
    public void tollRaiseAddress() {
        startActivity(new Intent(this, RaiseAddressActivity.class));
    }

    @OnClick(R.id.btn_add_address)
    public void tobtnRaiseAddress() {
        startActivity(new Intent(this, RaiseAddressActivity.class));
    }
}

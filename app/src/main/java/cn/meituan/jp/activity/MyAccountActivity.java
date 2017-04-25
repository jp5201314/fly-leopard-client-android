package cn.meituan.jp.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.entity.UserEntity;
import cn.meituan.jp.event.ExitLoginEvent;
import cn.meituan.jp.net.FlyHttpRequestCallBack;

public class MyAccountActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.iv_head_image)
    ImageView ivHeadImage;
    @Bind(R.id.fl_head_image)
    FrameLayout flHeadImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.fl_user_name)
    FrameLayout flUserName;
    @Bind(R.id.tv_password)
    TextView tvPassword;
    @Bind(R.id.fl_password)
    FrameLayout flPassword;
    @Bind(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @Bind(R.id.fl_phone_num)
    FrameLayout flPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        tvTitle.setText("我的账号");
        getUserInfo();

    }


    private void getUserInfo() {
        Picasso.with(this).load("http://www.th7.cn/d/file/p/2013/03/09/1dc921af6f1741e53f89ea258885c0d9.jpg").resize(100,80).centerCrop().into(ivHeadImage);
        HttpRequest.get(Constant.getHost()+String.format(Api.PERSONALINFO,getIntent().getIntExtra("id",0)),new FlyHttpRequestCallBack(){
            @Override
            protected void onDataSuccess(JSONObject data) {
                super.onDataSuccess(data);
                tvUserName.setText(data.getString("nick_name"));
                tvPhoneNum.setText(data.getString("phone"));
                if(!TextUtils.isEmpty(getIntent().getStringExtra("password"))){
                    tvPassword.setText("已设置");
                }
            }

            @Override
            protected void onDataError(int status, JSONObject statusInfo) {
                super.onDataError(status, statusInfo);
            }
        });

    }


    @OnClick(R.id.ib_back)
    public void back(){
        finish();
    }

    @OnClick(R.id.fl_head_image)
    public void toHeadImage() {

    }

    @OnClick(R.id.fl_user_name)
    public void toUpdateUserName() {
        startActivity(new Intent(this,UpdateUserNameActivity.class));
    }

    @OnClick(R.id.fl_password)
    public void toSetPassword() {
        startActivity(new Intent(this,SetPasswordActivity.class));
    }

    @OnClick(R.id.fl_phone_num)
    public void toBindMobile() {

    }

    @OnClick(R.id.btn_exit_current_account)
    public void toExitCurrentAccount() {
        ivHeadImage.setImageResource(R.drawable.icon_mine_head);
        tvUserName.setText("");
        tvPassword.setText("未设置");
        tvPhoneNum.setText("");
        UserSharedPreference.getInstance().removeLoginMsg();
        EventBus.getDefault().post(new ExitLoginEvent(-1));
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

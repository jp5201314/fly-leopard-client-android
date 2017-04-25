package cn.meituan.jp.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.event.LocationEvent;
import cn.meituan.jp.net.FlyHttpRequestCallBack;

public class RaiseAddressActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_add_info)
    TextView tvAddInfo;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.et_address)
    EditText etAddress;

    private double lon;
    private double lat;
    private  String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_address);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        tvTitle.setText("新增收货地址");
        tvAddInfo.setText("保存");
        tvAddInfo.setVisibility(View.VISIBLE);
        SpannableString ss = new SpannableString("请填写收货人的姓名");//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(10, true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        etName.setHint(new SpannedString(ss));
        SpannableString ss1 = new SpannableString("请填写收货人的电话号码");//定义hint的值
        AbsoluteSizeSpan ass1 = new AbsoluteSizeSpan(10, true);//设置字体大小 true表示单位是sp
        ss1.setSpan(ass1, 0, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        etPhone.setHint(new SpannedString(ss1));
        SpannableString ss2 = new SpannableString("例:16号楼124室");//定义hint的值
        AbsoluteSizeSpan ass2 = new AbsoluteSizeSpan(10, true);//设置字体大小 true表示单位是sp
        ss2.setSpan(ass2, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        etPhone.setHint(new SpannedString(ss2));


    }


    @OnClick(R.id.tv_add_info)
    public void toSave() {
        RequestParams params = new RequestParams(this);
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        if(!TextUtils.isEmpty(name)){
            params.addFormDataPart("name",name);
        }else{
            toast("收货人姓名不能为空");
            return;
        }
        if(!TextUtils.isEmpty(phone)){
            params.addFormDataPart("phone",phone);
        }else{
            toast("收货人电话号码不能为空");
            return;
        }
        if(!TextUtils.isEmpty(address)){
            params.addFormDataPart("address",address);
        }else{
            toast("收货人地址不能为空");
            return;
        }
       if(lon!=0&&lat!=0){
           params.addFormDataPart("lat",lat);
           params.addFormDataPart("lon",lon);
       }

        HttpRequest.post(Constant.getHost() + String.format(Api.ADDTAKEDELIVERYADDRESS, UserSharedPreference.getInstance().getId()), params,new FlyHttpRequestCallBack(){
            @Override
            protected void onDataSuccess(JSONObject data) {
                super.onDataSuccess(data);
                toast("添加成功");
            }

            @Override
            protected void onDataError(int status, JSONObject statusInfo) {
                super.onDataError(status, statusInfo);
                toast("添加失败");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setCurrentLocation(LocationEvent event){
        address = event.getAddress();
        tvAddress.setText(String.format(tvAddress.getText().toString(),address));
        lon = event.getLon();
        lat = event.getLat();
    }

}

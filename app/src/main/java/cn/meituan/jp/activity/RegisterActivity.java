package cn.meituan.jp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.R;
import cn.meituan.jp.net.FlyHttpRequestCallBack;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_nick_name)
    EditText etNickName;
    @Bind(R.id.et_phone_num)
    EditText etPhoneNum;
    @Bind(R.id.et_password)
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void toRegister(){
         String name = etName.getText().toString();
         String nickName = etNickName.getText().toString();
         String phoneNum  = etPhoneNum.getText().toString();
         String password = etPassword.getText().toString();

        RequestParams params = new RequestParams(this);
        if (TextUtils.isEmpty(name)) {
            toast("请填写用户名");
            return;
        } else {
            params.addFormDataPart("name", name);
        }
        if (TextUtils.isEmpty(nickName)) {
            toast("请填写密码");
            return;
        } else {
            params.addFormDataPart("nick_name", nickName);
        }
        if (TextUtils.isEmpty(phoneNum)) {
            toast("请填写用户名");
            return;
        } else {
            params.addFormDataPart("phone", phoneNum);
        }
        if (TextUtils.isEmpty(password)) {
            toast("请填写密码");
            return;
        } else {
            params.addFormDataPart("pass", password);
        }

        HttpRequest.post(Constant.getHost()+ Api.REGISTER,params,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                switch (jsonObject.getIntValue("status")){
                    case 0:
                        toast("注册成功");
                        finish();
                        break;
                    case 10000:
                        toast(jsonObject.getJSONObject("statusInfo").getString("message"));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                toast(msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

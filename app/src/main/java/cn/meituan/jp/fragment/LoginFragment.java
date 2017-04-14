package cn.meituan.jp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.alibaba.fastjson.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.activity.MainActivity;
import cn.meituan.jp.net.FlyHttpRequestCallBack;


/**
 * Created by 11608 on 2017/4/14.
 */

public class LoginFragment extends BaseFragment {
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acount_password_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_login)
    public void login(){
        final String phoneNum = etAccount.getText().toString();
        final String  password = etPassword.getText().toString();

        RequestParams params = new RequestParams((HttpCycleContext) getActivity());
        if (TextUtils.isEmpty(phoneNum)) {
            toast("请填写手机号");
            return;
        } else {
            params.addFormDataPart("mobile", phoneNum);
        }
        if (TextUtils.isEmpty(password)) {
           toast("请填写密码");
            return;
        } else {
            params.addFormDataPart("password", password);
        }

        HttpRequest.post(Constant.getHost() + Api.LOGIN, params, new FlyHttpRequestCallBack() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                super.onDataSuccess(data);
               toast("登录成功");
                String token = data.getString("token");
                UserSharedPreference.getInstance().setIsLogined(token);
                UserSharedPreference.getInstance().setPhoneAndPassword(phoneNum, password);
                OkHttpFinal.getInstance().updateCommonHeader("Authorization", "Bearer " + token);
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }

            @Override
            protected void onDataError(int status, JSONObject statusInfo) {
                super.onDataError(status, statusInfo);
            }
        });
    }
}

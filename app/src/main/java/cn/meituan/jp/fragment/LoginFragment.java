package cn.meituan.jp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.activity.MainActivity;
import cn.meituan.jp.entity.UserEntity;
import cn.meituan.jp.event.ErrorMessageEvent;
import cn.meituan.jp.net.FlyHttpRequestCallBack;
import okhttp3.Headers;


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
        final String name = etAccount.getText().toString();
        final String  password = etPassword.getText().toString();

        RequestParams params = new RequestParams(this);
        if (TextUtils.isEmpty(name)) {
            toast("请填写用户名");
            return;
        } else {
            params.addFormDataPart("name", name);
        }
        if (TextUtils.isEmpty(password)) {
           toast("请填写密码");
            return;
        } else {
            params.addFormDataPart("pass", password);
        }
        HttpRequest.post(Constant.getHost() + Api.LOGIN, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                toast("登录成功");
                int status = jsonObject.getIntValue("status");
                UserSharedPreference.getInstance().setIsLogined(status);
                UserSharedPreference.getInstance().setPhoneAndPassword(name, password);
                JSONObject data = jsonObject.getJSONObject("data");
                UserSharedPreference.getInstance().setBalance(data.getIntValue("money"));
                UserSharedPreference.getInstance().setId(data.getIntValue("id"));
                UserSharedPreference.getInstance().setPhone(data.getString("phone"));
                UserSharedPreference.getInstance().setNickName(data.getString("nick_name"));
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);

                EventBus.getDefault().post(new ErrorMessageEvent("登录失败"));
            }

        });
    }
}

package cn.meituan.jp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.activity.LoginRegisterActivity;

/**
 * Created by 11608 on 2017/4/13.
 */

public class OrderFragment extends BaseFragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (TextUtils.isEmpty(UserSharedPreference.getInstance().getIsLogined())){
            view = inflater.inflate(R.layout.fragment_order_unlogin, container, false);
        }
            view = inflater.inflate(R.layout.fragment_order_login,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_login_register)
    public void toLoginRegister(){
        startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
    }
}

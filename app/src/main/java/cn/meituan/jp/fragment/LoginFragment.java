package cn.meituan.jp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.meituan.jp.R;

/**
 * Created by 11608 on 2017/4/14.
 */

public class LoginFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_acount_password_login,container,false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

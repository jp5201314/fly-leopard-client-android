package cn.meituan.jp.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.meituan.jp.activity.LoginRegisterActivity;
import cn.meituan.jp.view.RefreshHeaderView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by 11608 on 2017/4/14.
 */

public class BaseFragment extends Fragment implements HttpCycleContext{

    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();

    public BaseFragment(){

    }
    protected void setMaterialHeader(PtrClassicFrameLayout ptr) {
        RefreshHeaderView ptrHeader = new RefreshHeaderView(getActivity().getApplicationContext());
        ptrHeader.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        ptrHeader.setPtrFrameLayout(ptr);
        ptr.setLoadingMinTime(800);
        ptr.setDurationToCloseHeader(800);
        ptr.setHeaderView(ptrHeader);
        ptr.addPtrUIHandler(ptrHeader);
    }

    protected void jumpLogin(){
        startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
    }

    protected void toast(String msg){
        Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }
}

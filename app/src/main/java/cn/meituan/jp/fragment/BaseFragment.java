package cn.meituan.jp.fragment;

import android.support.v4.app.Fragment;

import cn.meituan.jp.view.RefreshHeaderView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by 11608 on 2017/4/14.
 */

public class BaseFragment extends Fragment {

    protected void setMaterialHeader(PtrClassicFrameLayout ptr) {
        RefreshHeaderView ptrHeader = new RefreshHeaderView(getActivity().getApplicationContext());
        ptrHeader.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        ptrHeader.setPtrFrameLayout(ptr);
        ptr.setLoadingMinTime(800);
        ptr.setDurationToCloseHeader(800);
        ptr.setHeaderView(ptrHeader);
        ptr.addPtrUIHandler(ptrHeader);
    }
}

package cn.meituan.jp.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shizhefei.mvc.MVCHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.activity.HomePageSearchActivity;
import cn.meituan.jp.adapter.HomePageRecycleViewAdapter;
import cn.meituan.jp.datasource.BusinessDataSource;
import cn.meituan.jp.entity.BusinessEntity;
import cn.meituan.jp.event.AddressEvent;
import cn.meituan.jp.location.listener.BaiduLocation;
import cn.meituan.jp.manager.FullyLinearLayoutManager;
import cn.meituan.jp.utils.PhoneManager;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by 11608 on 2017/4/13.
 */

public class HomePageFragment extends BaseFragment {

    @Bind(R.id.fl_title)
    FrameLayout flTitle;
    @Bind(R.id.rv_show)
    RecyclerView rvShow;
    @Bind(R.id.ptr)
    PtrClassicFrameLayout ptr;
    @Bind(R.id.tv_current_location)
    TextView tvCurrentLocation;
    private MVCHelper<List<BusinessEntity>> mvc;
    private HomePageRecycleViewAdapter homePageRecycleViewAdapter;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        PhoneManager.openGPSSettings();
        BaiduLocation.initLocation(getActivity());
        //MVCHelper.setLoadViewFractory(new DefinedLoadViewFactory());
       /* this.setMaterialHeader(ptr);
        mvc = new MVCUltraHelper<>(ptr);
        setRecyclerView();
        mvc.refresh();*/
        rvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvShow.setAdapter(new HomePageRecycleViewAdapter(getActivity(), this));
        return view;
    }

    //设置recycleview的数据
    private void setRecyclerView() {
        rvShow.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rvShow.setNestedScrollingEnabled(false);
        mvc.setDataSource(new BusinessDataSource());
        homePageRecycleViewAdapter = new HomePageRecycleViewAdapter(getActivity(), this);
        mvc.setAdapter(homePageRecycleViewAdapter);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddressEvent event) {
        tvCurrentLocation.setText(event.getAddress());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_search)
    public void search() {
        startActivity(new Intent(getActivity(), HomePageSearchActivity.class));
    }
}

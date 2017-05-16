package cn.meituan.jp.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.shizhefei.mvc.IDataAdapter;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCUltraHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.activity.HomePageSearchActivity;
import cn.meituan.jp.adapter.HomePageRecycleViewAdapter;
import cn.meituan.jp.adapter.HomePageRoundAdapter;
import cn.meituan.jp.datasource.BusinessDataSource;
import cn.meituan.jp.entity.BusinessEntity;
import cn.meituan.jp.event.LocationEvent;
import cn.meituan.jp.listener.Grid1Listener;
import cn.meituan.jp.listener.Grid2Listener;
import cn.meituan.jp.location.listener.BaiduLocation;
import cn.meituan.jp.manager.FullyLinearLayoutManager;
import cn.meituan.jp.utils.PhoneManager;
import cn.meituan.jp.view.DefinedLoadViewFactory;
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
    @Bind(R.id.vp_round)
    ViewPager vpRound;
    @Bind(R.id.iv_round)
    ImageView ivRound;
    @Bind(R.id.gv_one)
    GridView gvOne;
    @Bind(R.id.gv_two)
    GridView gvTwo;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.show_business)
    TextView showBusiness;
    private List<Map<String, Object>> gridList1;
    private List<Map<String, Object>> gridList2;
    private int TAG = 0;
    private Handler handler;
    private Thread myThread;
    private MVCHelper<List<BusinessEntity>> mvc;

    private int[] gridIcon1 = {R.drawable.icon_homepage_grid_one, R.drawable.icon_homepage_grid_two, R.drawable.icon_homepage_grid_tre, R.drawable.icon_homepage_grid_four, R.drawable.icon_homepage_grid_five, R.drawable.icon_homepage_grid_six, R.drawable.icon_homepage_grid_seven, R.drawable.icon_homepage_grid_eight};
    private String[] iconName = {"美食", "超市", "鲜果购"
            , "甜点饮品", "正餐精选", "美团专送"
            , "鲜花蛋糕", "精选小吃"};

    private int[] gridIcon2 = {R.drawable.icon_homepage_grid2_one, R.drawable.icon_homepage_grid2_two, R.drawable.icon_homepage_grid2_tre, R.drawable.icon_homepage_grid2_four};

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        PhoneManager.openGPSSettings();
        BaiduLocation.initLocation(getActivity());
        init();
        notifyTochangeUi();
        MVCHelper.setLoadViewFractory(new DefinedLoadViewFactory());
        this.setMaterialHeader(ptr);
        mvc = new MVCUltraHelper<>(ptr);
        setRecyclerView();
        return view;
    }

    //设置recycleview的数据
    private void setRecyclerView() {
        rvShow.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rvShow.setNestedScrollingEnabled(false);
        BusinessDataSource dataSource = new BusinessDataSource(this);
        mvc.setDataSource(dataSource);
        mvc.setAdapter(new IDataAdapter<List<BusinessEntity>>() {
            @Override
            public void notifyDataChanged(List<BusinessEntity> list, boolean isRefresh) {
                if (rvShow!=null){
                    // 数据给recycleView
                    rvShow.setAdapter(new HomePageRecycleViewAdapter(getActivity(),list));
                }
      
            }

            @Override
            public List<BusinessEntity> getData() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        mvc.refresh();

    }

    //初始化固定布局
    private void init() {
        final List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new CarouselFragmentOne(getActivity()));
        fragmentList.add(new CarouselFragmentTwo(getActivity()));
        fragmentList.add(new CarouselFragmentTre(getActivity()));
        vpRound.setAdapter(new HomePageRoundAdapter(getFragmentManager(), fragmentList));
        vpRound.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    ivRound.setBackgroundResource(R.drawable.icon_carousel_tre);
                } else if (position == 1) {
                    ivRound.setBackgroundResource(R.drawable.icon_carousel_two);
                } else {
                    ivRound.setBackgroundResource(R.drawable.icon_carousel_one);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        SimpleAdapter grid1Adapter = new SimpleAdapter(getActivity(), getGrid1Data(), R.layout.item_grid1, new String[]{"gridIcon", "iconName"}, new int[]{R.id.iv_homepage_grid1, R.id.tv_homepage_grid1});
        gvOne.setAdapter(grid1Adapter);
        gvOne.setOnItemClickListener(new Grid1Listener(getActivity()));

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(vpRound!=null){
                    vpRound.setCurrentItem(msg.what);
                }
            }
        };
        SimpleAdapter grid2Adapter = new SimpleAdapter(getActivity(), getGrid2Data(), R.layout.item_grid2, new String[]{"gridIcon2"}, new int[]{R.id.iv_homepage_grid2});
        gvTwo.setAdapter(grid2Adapter);
        gvTwo.setOnItemClickListener(new Grid2Listener(getActivity()));
    }

    /**
     * GridView1数据源
     */
    public List<Map<String, Object>> getGrid1Data() {
        gridList1 = new ArrayList<>();
        for (int i = 0; i < gridIcon1.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gridIcon", gridIcon1[i]);
            map.put("iconName", iconName[i]);
            gridList1.add(map);
        }

        return gridList1;
    }

    /**
     * GridView2数据源
     */
    public List<Map<String, Object>> getGrid2Data() {
        gridList2 = new ArrayList<>();
        for (int i = 0; i < gridIcon2.length; i++) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("gridIcon2", gridIcon2[i]);
            gridList2.add(map2);
        }
        return gridList2;
    }

    /**
     * 开启线程通知ViewPager更新UI
     */

    private void notifyTochangeUi() {
        if (myThread == null) {
            myThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep(3000);
                            TAG++;
                            if (TAG % 3 == 0) {
                                TAG = 0;
                            }
                            Message msg = new Message();
                            msg.what = TAG;

                            handler.sendMessage(msg);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            myThread.start();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LocationEvent event) {
        UserSharedPreference.getInstance().setAddress(event.getAddress());
        tvCurrentLocation.setText(event.getAddress());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
        if(myThread.isAlive()){
            myThread.interrupt();
            TAG=0;
        }
    }

    @OnClick(R.id.btn_search)
    public void search() {
        startActivity(new Intent(getActivity(), HomePageSearchActivity.class));
    }

}

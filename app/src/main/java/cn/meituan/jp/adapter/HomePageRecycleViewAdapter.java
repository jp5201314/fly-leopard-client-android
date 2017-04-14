package cn.meituan.jp.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.meituan.jp.R;
import cn.meituan.jp.fragment.CarouselFragmentOne;
import cn.meituan.jp.fragment.CarouselFragmentTre;
import cn.meituan.jp.fragment.CarouselFragmentTwo;
import cn.meituan.jp.listener.Grid1Listener;
import cn.meituan.jp.listener.Grid2Listener;

/**
 * Created by 11608 on 2017/4/14.
 */

public class HomePageRecycleViewAdapter extends BaseRecyclerAdapter {


    private Context context;
    private Fragment fragment;
    private List<Map<String, Object>> gridList1;
    private List<Map<String, Object>> gridList2;
    private int TAG = 0;
    private Handler handler;
    private Thread myThread;

    public HomePageRecycleViewAdapter(Context context, Fragment fragment) {
        super(context);
        this.context = context;
        this.fragment = fragment;
    }

    //轮转图布局    //食品分类布局
    private static final int ROUNDCHAT = 0;

    //折扣减价布局
    private static final int DISCOUNTREDUCEPRICE = 2;
    private int[] gridIcon1 = {R.drawable.icon_homepage_grid_one, R.drawable.icon_homepage_grid_two, R.drawable.icon_homepage_grid_tre, R.drawable.icon_homepage_grid_four, R.drawable.icon_homepage_grid_five, R.drawable.icon_homepage_grid_six, R.drawable.icon_homepage_grid_seven, R.drawable.icon_homepage_grid_eight};
    private String[] iconName = {"美食", "超市", "鲜果购"
            , "甜点饮品", "正餐精选", "美团专送"
            , "鲜花蛋糕", "精选小吃"};

    private int[] gridIcon2 = {R.drawable.icon_homepage_grid2_one, R.drawable.icon_homepage_grid2_two, R.drawable.icon_homepage_grid2_tre, R.drawable.icon_homepage_grid2_four};


    @Override
    public int getItemViewType(int position) {
        if (position == ROUNDCHAT) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ROUNDCHAT) {
            return new ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_homepage_grid_one, parent, false));
        } else {
            return new ViewHolder3(LayoutInflater.from(context).inflate(R.layout.item_homepage_nearby_business, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {
            List<Fragment> fragmentList = new ArrayList<Fragment>();
            fragmentList.add(new CarouselFragmentOne(context));
            fragmentList.add(new CarouselFragmentTwo(context));
            fragmentList.add(new CarouselFragmentTre(context));
            ((ViewHolder1) holder).vpRound.setAdapter(new HomePageRoundAdapter(fragment.getFragmentManager(), fragmentList));
            ((ViewHolder1) holder).vpRound.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (position == 2) {
                        ((ViewHolder1) holder).ivRound.setBackgroundResource(R.drawable.icon_carousel_tre);
                    } else if (position == 1) {
                        ((ViewHolder1) holder).ivRound.setBackgroundResource(R.drawable.icon_carousel_two);
                    } else {
                        ((ViewHolder1) holder).ivRound.setBackgroundResource(R.drawable.icon_carousel_one);
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            SimpleAdapter grid1Adapter = new SimpleAdapter(context, getGrid1Data(), R.layout.item_grid1, new String[]{"gridIcon", "iconName"}, new int[]{R.id.iv_homepage_grid1, R.id.tv_homepage_grid1});
            ((ViewHolder1) holder).gvOne.setAdapter(grid1Adapter);
            ((ViewHolder1) holder).gvOne.setOnItemClickListener(new Grid1Listener(context));

            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    ((ViewHolder1) holder).vpRound.setCurrentItem(msg.what);
                }
            };
            SimpleAdapter grid2Adapter = new SimpleAdapter(context, getGrid2Data(), R.layout.item_grid2, new String[]{"gridIcon2"}, new int[]{R.id.iv_homepage_grid2});
            ((ViewHolder1) holder).gvTwo.setAdapter(grid2Adapter);
            ((ViewHolder1) holder).gvTwo.setOnItemClickListener(new Grid2Listener(context));
        } else {

        }
    }

    @Override
    public int getItemCount() {
        notifyTochangeUi();
        return 2;
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder {
        @Bind(R.id.vp_round)
        ViewPager vpRound;
        @Bind(R.id.gv_one)
        GridView gvOne;
        @Bind(R.id.iv_round)
        ImageView ivRound;
        @Bind(R.id.gv_two)
        GridView gvTwo;

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        @Bind(R.id.show_business)
        TextView showBusiness;
        @Bind(R.id.rv_show_business)
        RecyclerView rvShowBusiness;

        public ViewHolder3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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

}

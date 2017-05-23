package cn.meituan.jp.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.adapter.MainFragmentAdapter;
import cn.meituan.jp.utils.PhoneManager;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.iv_homepage)
    ImageView ivHomepage;
    @Bind(R.id.rl_homepage)
    RelativeLayout rlHomepage;
    @Bind(R.id.iv_order)
    ImageView ivOrder;
    @Bind(R.id.main_order_tv)
    TextView mainOrderTv;
    @Bind(R.id.rl_order)
    RelativeLayout rlOrder;
    @Bind(R.id.iv_mine)
    ImageView ivMine;
    @Bind(R.id.main_mine_tv)
    TextView mainMineTv;
    @Bind(R.id.rl_mine)
    RelativeLayout rlMine;
    MainFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(!PhoneManager.isNetWorkAvailable()){
            toast("网络未连接，请打开网络连接");
        }
        vp.setCurrentItem(0);
        changeHomePageState();
        adapter = new MainFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch(position){
            case 0:
                changeHomePageState();
                break;
            case 1:
                changeOrderState();
                break;
            case 2:
               changeMineState();
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 显示首页界面状态
     * */
    private void changeHomePageState() {
        ivHomepage.setBackgroundResource(R.drawable.icon_home_page_pressed);
        ivOrder.setBackgroundResource(R.drawable.icon_order_unpressed);
        ivMine.setBackgroundResource(R.drawable.icon_mine_unpressed);
    }
    /*
     * 显示订单界面状态
     * */
    private void changeOrderState() {
        ivHomepage.setBackgroundResource(R.drawable.icon_home_page_unpressed);
        ivOrder.setBackgroundResource(R.drawable.icon_order_pressed);
        ivMine.setBackgroundResource(R.drawable.icon_mine_unpressed);
    }
    /*
      * 显示我的界面状态
      * */
    private void changeMineState() {
        ivHomepage.setBackgroundResource(R.drawable.icon_home_page_unpressed);
        ivOrder.setBackgroundResource(R.drawable.icon_order_unpressed);
        ivMine.setBackgroundResource(R.drawable.icon_mine_pressed);
    }

    @OnClick(R.id.rl_homepage)
    public void showHomePage(){
        vp.setCurrentItem(0);
        changeHomePageState();
    }

    @OnClick(R.id.rl_order)
    public void showOrder(){
        vp.setCurrentItem(1);
        changeOrderState();
    }

    @OnClick(R.id.rl_mine)
    public void showMine(){
        vp.setCurrentItem(2);
        changeMineState();
    }

}

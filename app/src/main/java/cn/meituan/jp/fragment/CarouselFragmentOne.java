package cn.meituan.jp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.activity.HomePageCarouselWebViewActivity;

/**
 * Created by 11608 on 2017/4/14.
 */

public class CarouselFragmentOne extends BaseFragment {
    private Context context;
    public CarouselFragmentOne(Context context){
        this.context = context;
    }
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carousel_one,container,false);
    }


    @OnClick(R.id.iv_carousel_one)
    public void onClick(){
        Intent intent = new Intent(context,HomePageCarouselWebViewActivity.class);
        intent.putExtra("url","http://i.waimai.meituan.com/external/poi/508730?utm_source=5801&wmi_from=cpoiinfo");
        context.startActivity(intent);
    }
}

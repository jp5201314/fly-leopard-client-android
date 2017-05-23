package cn.meituan.jp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.activity.HomePageCarouselWebViewActivity;

/**
 * Created by 11608 on 2017/4/14.
 */

public class CarouselFragmentTre extends BaseFragment {
    private Context context;

    public CarouselFragmentTre(Context context) {
        this.context = context;
    }
    public CarouselFragmentTre(){}

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carousel_tre, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


    @OnClick(R.id.iv_carousel_tre)
    public void onClick() {
        Intent intent = new Intent(context, HomePageCarouselWebViewActivity.class);
        intent.putExtra("url", "http://i.meituan.com/firework/160608ouzhoubeicipian?activity_id=2811");
        context.startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

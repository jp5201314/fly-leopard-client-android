package cn.meituan.jp.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import cn.meituan.jp.activity.HomePageCarouselWebViewActivity;
import cn.meituan.jp.activity.HomePageGrid1DetailActivity;

/**
 * Created by 11608 on 2017/4/14.
 */

public class Grid1Listener implements AdapterView.OnItemClickListener {
    private Context context;

    public Grid1Listener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(context, HomePageCarouselWebViewActivity.class);
        switch (i) {
            case 0:
                intent.putExtra("topic", "美食");
                intent.putExtra("url", "http://i.waimai.meituan.com/restaurant/72807290364720249");
                context.startActivity(intent);
                break;
            case 1:
                intent.putExtra("topic", "超市");
                intent.putExtra("url", "http://i.waimai.meituan.com/restaurant/72882108695029287");
                context.startActivity(intent);
                break;
            case 2:
                intent.putExtra("topic", "鲜果购");
                intent.putExtra("url", "http://i.waimai.meituan.com/restaurant/72764800253267143");
                context.startActivity(intent);
                break;
            case 3:
                intent.putExtra("topic", "甜点饮品");
                intent.putExtra("url", "http://i.waimai.meituan.com/restaurant/72654071701428285");
                context.startActivity(intent);
                break;
            case 4:
                intent.putExtra("topic", "正餐优选");
                intent.putExtra("url", "http://i.waimai.meituan.com/restaurant/72801732677067155");
                context.startActivity(intent);
                break;
            case 5:
                intent.putExtra("topic", "飞豹专送");
                intent.putExtra("url", "http://i.waimai.meituan.com/restaurant/72775941398444772");
                context.startActivity(intent);
                break;
            case 6:
                intent.putExtra("topic", "创意西餐");
                intent.putExtra("url", "http://i.waimai.meituan.com/restaurant/72663194211946655");
                context.startActivity(intent);
                break;
            case 7:
                intent.putExtra("topic", "民间小吃");
                intent.putExtra("url", "http://i.waimai.meituan.com/restaurant/72622052720231325");
                context.startActivity(intent);
                break;
        }

    }
}

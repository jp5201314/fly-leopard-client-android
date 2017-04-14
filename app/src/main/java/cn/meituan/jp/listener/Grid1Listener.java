package cn.meituan.jp.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

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
        Intent intent = new Intent(context, HomePageGrid1DetailActivity.class);
        switch (i) {
            case 0:
                intent.putExtra("topic", "美食");
                context.startActivity(intent);
                break;
            case 1:
                intent.putExtra("topic", "超市");
                context.startActivity(intent);
                break;
            case 2:
                intent.putExtra("topic", "鲜果购");
                context.startActivity(intent);
                break;
            case 3:
                intent.putExtra("topic", "甜点饮品");
                context.startActivity(intent);
                break;
            case 4:
                intent.putExtra("topic", "正餐优选");
                context.startActivity(intent);
                break;
            case 5:
                intent.putExtra("topic", "美团专送");
                context.startActivity(intent);
                break;
            case 6:
                intent.putExtra("topic", "鲜花蛋糕");
                context.startActivity(intent);
                break;
            case 7:
                intent.putExtra("topic", "精选小吃");
                context.startActivity(intent);
                break;
        }

    }
}

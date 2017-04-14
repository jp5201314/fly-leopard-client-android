package cn.meituan.jp.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import cn.meituan.jp.activity.HomePageCarouselWebViewActivity;

/**
 * Created by 11608 on 2017/4/14.
 */

public class Grid2Listener implements AdapterView.OnItemClickListener {
    private Context context;

    public Grid2Listener(Context context){
        this.context = context;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(context, HomePageCarouselWebViewActivity.class);
        switch (i) {
            case 0:
                intent.putExtra("url", "http://i.meituan.com/firework/160705?activity_id=3999");
                context.startActivity(intent);
                break;
            case 1:
                intent.putExtra("url", "http://i.meituan.com/firework/dianpingqa?activity_id=3175");
                context.startActivity(intent);
                break;
            case 2:
                intent.putExtra("url", "http://i.meituan.com/firework/0504daemanjian?activity_id=1586");
                context.startActivity(intent);
                break;
            case 3:
                intent.putExtra("url", "http://i.meituan.com/firework/0504discount?activity_id=1822");
                context.startActivity(intent);
                break;
        }
    }
}

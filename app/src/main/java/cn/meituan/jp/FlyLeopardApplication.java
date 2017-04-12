package cn.meituan.jp;

import android.app.Application;
import android.content.Context;

/**
 * Created by 11608 on 2017/4/12.
 */

public class FlyLeopardApplication extends Application{

    private static FlyLeopardApplication INSTANCE;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        mContext = getApplicationContext();
    }
    public static synchronized FlyLeopardApplication getInstance() {
        return INSTANCE;
    }

    public static synchronized Context getContext() {
        return mContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

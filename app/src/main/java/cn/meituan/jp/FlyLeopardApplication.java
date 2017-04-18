package cn.meituan.jp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import cn.meituan.jp.activity.LoginRegisterActivity;
import cn.meituan.jp.event.ErrorMessageEvent;
import cn.meituan.jp.event.UnLoginEvent;

/**
 * Created by 11608 on 2017/4/12.
 */

public class FlyLeopardApplication extends Application {

    private static FlyLeopardApplication INSTANCE;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        mContext = getApplicationContext();
        initOkHttpFinal();
        EventBus.getDefault().register(mContext);
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
        EventBus.getDefault().unregister(mContext);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnLoginEvent(UnLoginEvent event) {
        switch (event.getType()) {
            case 40000://NOT_PROVIDED_JWT_TOKEN
                Toast.makeText(mContext, "您还未登录，请登录", Toast.LENGTH_SHORT).show();
                jumpToLogin();
                break;
            case 40001://INVALID_JWT_TOKEN
                Toast.makeText(mContext, "你的账号已在其他设备登录，请重新登录", Toast.LENGTH_SHORT).show();
                jumpToLogin();
                break;
            case 40004://NOT_FOUND_JWT_USER
                Toast.makeText(mContext, "用户信息已过期，请重新登录", Toast.LENGTH_SHORT).show();
                jumpToLogin();
                break;
            case 40005://EXPIRED_JWT_TOKEN
                Toast.makeText(mContext, "令牌已失效，请重新登录", Toast.LENGTH_SHORT).show();
                jumpToLogin();
                break;
            case 40006://EXPIRED_JWT_TOKEN
                Toast.makeText(mContext, "你的账号已在其他设备登录", Toast.LENGTH_SHORT).show();
                jumpToLogin();
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorMessageEvent(ErrorMessageEvent event) {
        Toast.makeText(mContext, event.getMsg(), Toast.LENGTH_SHORT).show();
    }

    protected boolean allowJumpToLogin() {
        return -1 == UserSharedPreference.getInstance().getIsLogined();
    }

    protected void jumpToLogin() {
        if (!allowJumpToLogin()) return;
        startActivity(new Intent(mContext, LoginRegisterActivity.class));
    }

    /**
     * 初始化OkHttpFinal
     */
    private void initOkHttpFinal() {
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        builder.setDebug(true);
        OkHttpFinal.getInstance().init(builder.build());

        OkHttpFinal.getInstance().updateCommonHeader("Accept", "application/json");
    }
}

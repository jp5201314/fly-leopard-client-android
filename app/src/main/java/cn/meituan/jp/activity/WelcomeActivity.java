package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;

import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //判断是否是第一次运行

                    boolean isFirstRun = UserSharedPreference.getInstance().getIsFristLogin();
                    if (isFirstRun)//是第一次运行
                    {
                        sleep(2000);//线程睡眠
                        startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                        WelcomeActivity.this.finish();
                        UserSharedPreference.getInstance().setIsFristLogin(false);
                    } else//不是第一次运行
                    {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                        WelcomeActivity.this.finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

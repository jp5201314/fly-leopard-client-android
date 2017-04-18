package cn.meituan.jp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.meituan.jp.R;

public class UpdateUserNameActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_name);

        this.setStatusBarColor(R.color.color_black_0e1214);
    }
}

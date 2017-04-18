package cn.meituan.jp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.OnClick;
import cn.meituan.jp.R;

public class MyOnlineServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_online_service);

        this.setStatusBarColor(R.color.color_black_0e1214);
    }

    @OnClick(R.id.ib_back)
    public void back(){
        finish();
    }

}

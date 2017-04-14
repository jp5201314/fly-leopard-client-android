package cn.meituan.jp.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;

public class HomePageGrid1DetailActivity extends BaseActivity {

    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_food_topic)
    TextView tvFoodTopic;
    @Bind(R.id.ib_search)
    ImageButton ibSearch;
    @Bind(R.id.rv_show)
    RecyclerView rvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_grid1_detail);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        tvFoodTopic.setText(getIntent().getStringExtra("topic"));

    }
    @OnClick(R.id.ib_back)
    public void back(){
        finish();
    }


}

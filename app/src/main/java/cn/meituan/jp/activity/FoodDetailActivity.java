package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.entity.FoodsEntity;

public class FoodDetailActivity extends BaseActivity {

    @Bind(R.id.ib_food_back)
    ImageView ibFoodBack;
    @Bind(R.id.iv_food_share)
    ImageView ivFoodShare;
    @Bind(R.id.iv_food_image)
    ImageView ivFoodImage;
    @Bind(R.id.tv_food_name)
    TextView tvFoodName;
    @Bind(R.id.tv_food_price)
    TextView tvFoodPrice;
    @Bind(R.id.btn_to_commit)
    Button btnToCommit;
    @Bind(R.id.tv_food_info)
    TextView tvFoodInfo;
    @Bind(R.id.tv_food_evaluation)
    TextView tvFoodEvaluation;
    private  FoodsEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        entity = getIntent().getParcelableExtra("food");
        Picasso.with(this).load(entity.getPhoto()).resize(480, 320).centerCrop().into(ivFoodImage);
        Log.i("FSLog",entity.getShop().toString());
        tvFoodName.setText(entity.getName());
        tvFoodInfo.setText(entity.getContent());
        tvFoodPrice.setText(String.format(tvFoodPrice.getText().toString(), entity.getPrice()));
        tvFoodEvaluation.setText(entity.getContent());


    }


    @OnClick(R.id.ib_food_back)
    public void setIbFoodBack() {
        finish();
    }


    @OnClick(R.id.iv_food_share)
    public void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, entity.getPhoto());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "分享给好基友吧"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_to_commit)
    public void toCommit() {
        if (getLoginStatus() == 0) {
            Intent intent = new Intent(this, CommitOrderActivity.class);
            intent.putExtra("id", entity.getId());
            intent.putExtra("name", entity.getName());
            intent.putExtra("price", entity.getPrice());
            intent.putExtra("shop_name", entity.getShop().getName());
            startActivity(intent);
        } else {
            toLoginRegister();
        }
    }
}

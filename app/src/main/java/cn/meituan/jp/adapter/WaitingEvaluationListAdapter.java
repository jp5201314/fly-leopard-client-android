package cn.meituan.jp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.meituan.jp.R;
import cn.meituan.jp.activity.EvaluationListActivity;
import cn.meituan.jp.entity.OrderEntity;
import cn.meituan.jp.utils.DateUtils;

/**
 * Created by 11608 on 2017/4/24.
 */

public class WaitingEvaluationListAdapter extends BaseRecyclerAdapter<OrderEntity> {


    String[] images = new String[]{"http://img0.imgtn.bdimg.com/it/u=3083783834,572394683&fm=214&gp=0.jpg", "http://img2.imgtn.bdimg.com/it/u=4117982762,2906562027&fm=214&gp=0.jpg", "http://img.dangaoss.com/public/b/80/8/40967_m.jpg", "http://awb.img.xmtbang.com/img/uploadnew/201510/23/5f91a84b11c345a791e2c7c3f0ff283f.jpg"};
    String foodInfo = "共%s件商品,实付￥%s";
    String[] shopName = new String[]{"德克士(中和店)", "大蓉和(世纪城店)", "清粥小菜(天府店)", "小龙坎火锅(春熙路店)"};


    public WaitingEvaluationListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_waiting_evaluation, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderEntity orderEntities = list.get(position);
        int i = position % images.length;
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).tvShopName.setText(shopName[i]);
            Picasso.with(context).load(images[i]).resize(320, 240).centerCrop().into(((ViewHolder) holder).ivShopImage);
            ((ViewHolder) holder).tvOrderCreateTime.setText(DateUtils.dateFormatConvert(orderEntities.getCreateAt(), "MM:dd hh:mm:ss"));
            ((ViewHolder) holder).tvFoodName.setText(orderEntities.getFoodsEntity().getName());
            ((ViewHolder) holder).tvFoodNum.setText(orderEntities.getAmount() + "");
            ((ViewHolder) holder).tvShowFoodInfo.setText(String.format(foodInfo, orderEntities.getAmount(), orderEntities.getTotalFee()));
            ((ViewHolder) holder).btnWaitEvaluation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, EvaluationListActivity.class));
                }
            });
        } else {

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_shop_image)
        ImageView ivShopImage;
        @Bind(R.id.tv_shop_name)
        TextView tvShopName;
        @Bind(R.id.tv_order_create_time)
        TextView tvOrderCreateTime;
        @Bind(R.id.tv_food_name)
        TextView tvFoodName;
        @Bind(R.id.tv_type)
        TextView tvType;
        @Bind(R.id.tv_food_num)
        TextView tvFoodNum;
        @Bind(R.id.tv_show_food_info)
        TextView tvShowFoodInfo;
        @Bind(R.id.btn_wait_evaluation)
        Button btnWaitEvaluation;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

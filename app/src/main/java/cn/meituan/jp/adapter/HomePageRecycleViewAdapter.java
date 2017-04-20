package cn.meituan.jp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.meituan.jp.R;
import cn.meituan.jp.activity.ShopDetailActivity;
import cn.meituan.jp.entity.BusinessEntity;
import cn.meituan.jp.listener.OnItemClickListener;

/**
 * Created by 11608 on 2017/4/14.
 */

public class HomePageRecycleViewAdapter extends RecyclerView.Adapter {

    public List<BusinessEntity> list;
    private Context context;

    public HomePageRecycleViewAdapter(Context context, List<BusinessEntity> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder3(LayoutInflater.from(context).inflate(R.layout.item_homepage_nearby_business, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BusinessEntity entity = list.get(position);
        final ViewHolder3 viewHolder3 = (ViewHolder3) holder;
        Picasso.with(context)
                .load(entity.getPhoto())
                .resize(200, 160)
                .centerCrop()
                .into((viewHolder3).ivBusinessPicture);

        viewHolder3.tvShopName.setText(entity.getName());
        viewHolder3.tvDistance.setText(entity.getDistance() + "m");
        switch (entity.getStarNum()) {
            case 2:
                (viewHolder3).ivShopStar.setBackgroundResource(R.drawable.icon_star2);
                break;
            case 3:
                (viewHolder3).ivShopStar.setBackgroundResource(R.drawable.icon_star3);
                break;
            case 4:
                (viewHolder3).ivShopStar.setBackgroundResource(R.drawable.icon_star4);
                break;
            case 5:
                (viewHolder3).ivShopStar.setBackgroundResource(R.drawable.icon_star5);
                break;
            default:
                (viewHolder3).ivShopStar.setBackgroundResource(R.drawable.icon_star5);
                break;
        }
        (viewHolder3).tvSaleMonth.setText("月售" + entity.getSaleMonth() + "单");
        (viewHolder3).tvTime.setText((entity.getTime() / 60) + "分");
        (viewHolder3).tvMinFee.setText("起送价 ￥" + entity.getMinFee());
        (viewHolder3).tvPackingFee.setText("配送价 ￥" + entity.getPackingFee());
        (viewHolder3).tvActivityNum.setOnClickListener(new View.OnClickListener() {
            int num = 0;

            @Override
            public void onClick(View view) {
                num++;
                if (num % 2 == 0) {
                    viewHolder3.llActivityList.setVisibility(View.VISIBLE);
                } else {
                    viewHolder3.llActivityList.setVisibility(View.GONE);
                }
            }
        });
        /**
         * RecycleView的每一项的监听事件
         */
        viewHolder3.itemView.setOnClickListener(new OnItemClickListener<List<BusinessEntity>>(position, list) {

            @Override
            public void onItemClick(int position, List<BusinessEntity> list) {
                Intent intent = new Intent(context, ShopDetailActivity.class);
                intent.putExtra("shop_id", list.get(position).getId());
                intent.putExtra("shop_name",list.get(position).getName());
                intent.putExtra("shop_head_image",list.get(position).getPhoto());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_business_picture)
        ImageView ivBusinessPicture;
        @Bind(R.id.tv_shop_name)
        TextView tvShopName;
        @Bind(R.id.iv_shop_star)
        ImageView ivShopStar;
        @Bind(R.id.tv_sale_month)
        TextView tvSaleMonth;
        @Bind(R.id.tv_min_fee)
        TextView tvMinFee;
        @Bind(R.id.tv_divide)
        TextView tvDivide;
        @Bind(R.id.tv_packing_fee)
        TextView tvPackingFee;
        @Bind(R.id.tv_distance)
        TextView tvDistance;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.rl_shop_info)
        RelativeLayout rlShopInfo;
        @Bind(R.id.item_divide)
        View itemDivide;
        @Bind(R.id.tv_now_reduce)
        TextView tvNowReduce;
        @Bind(R.id.tv_discount)
        TextView tvDiscount;
        @Bind(R.id.tv_activity3)
        TextView tvActivity3;
        @Bind(R.id.tv_activity4)
        TextView tvActivity4;
        @Bind(R.id.tv_activity5)
        TextView tvActivity5;
        @Bind(R.id.tv_activity6)
        TextView tvActivity6;
        @Bind(R.id.ll_activity_list)
        LinearLayout llActivityList;
        @Bind(R.id.tv_activity_num)
        TextView tvActivityNum;
        @Bind(R.id.rl_shop_activity)
        RelativeLayout rlShopActivity;
        @Bind(R.id.home_listitem4_layout)
        RelativeLayout homeListitem4Layout;

        public ViewHolder3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


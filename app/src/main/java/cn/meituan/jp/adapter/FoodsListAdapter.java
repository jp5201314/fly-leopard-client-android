package cn.meituan.jp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.meituan.jp.R;
import cn.meituan.jp.activity.FoodDetailActivity;
import cn.meituan.jp.entity.FoodsEntity;
import cn.meituan.jp.event.SelectGoodsEvent;
import cn.meituan.jp.listener.OnItemClickListener;

/**
 * Created by 11608 on 2017/4/19.
 */

public class FoodsListAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<FoodsEntity> list;

    public FoodsListAdapter(Context context, List<FoodsEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_food, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FoodsEntity entity = list.get(position);
        if (holder instanceof ViewHolder) {
            Picasso.with(context).load(entity.getPhoto()).resize(200, 160).centerCrop().into(((ViewHolder) holder).ivFoodImage);
            ((ViewHolder) holder).tvFoodInfo.setText(entity.getContent());
            ((ViewHolder) holder).tvFoodPrice.setText(String.format(((ViewHolder) holder).tvFoodPrice.getText().toString(),entity.getPrice()));
            ((ViewHolder) holder).tvFoodName.setText(entity.getName());
            ((ViewHolder) holder).btnAddShoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new SelectGoodsEvent(entity.getName(), entity.getPrice()));
                }
            });

            holder.itemView.setOnClickListener(new OnItemClickListener<FoodsEntity>(position, entity) {
                @Override
                public void onItemClick(int position, FoodsEntity foodsEntity) {
                    Intent intent = new Intent(context, FoodDetailActivity.class);
                    intent.putExtra("food", foodsEntity);
                    context.startActivity(intent);
                }
            });
        } else {

        }
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_food_image)
        ImageView ivFoodImage;
        @Bind(R.id.tv_food_name)
        TextView tvFoodName;
        @Bind(R.id.tv_food_info)
        TextView tvFoodInfo;
        @Bind(R.id.tv_food_price)
        TextView tvFoodPrice;
        @Bind(R.id.btn_add_shopping_cart)
        Button btnAddShoppingCart;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

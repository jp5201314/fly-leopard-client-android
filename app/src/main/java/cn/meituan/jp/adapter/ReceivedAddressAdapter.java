package cn.meituan.jp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.meituan.jp.R;
import cn.meituan.jp.entity.AddressEntity;
import cn.meituan.jp.entity.UserEntity;

/**
 * Created by 11608 on 2017/4/20.
 */

public class ReceivedAddressAdapter extends RecyclerView.Adapter {

    private Context context;

    private UserEntity entity;

    public ReceivedAddressAdapter(Context context, UserEntity entity) {
        this.context = context;
        this.entity = entity;
        Log.i("FSLog", entity.toString());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_address, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddressEntity addressEntity = entity.getAddressEntityList().get(position);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).tvAddress.setText(addressEntity.getAddress());
            ((ViewHolder) holder).tvName.setText(entity.getName() + "   先生");
            ((ViewHolder) holder).tvPhoneNum.setText(entity.getPhone());
        }
    }

    @Override
    public int getItemCount() {
        return entity.getAddressEntityList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_address)
        TextView tvAddress;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_phone_num)
        TextView tvPhoneNum;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

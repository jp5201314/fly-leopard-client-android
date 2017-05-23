package cn.meituan.jp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 11608 on 2017/5/2.
 */

public class FoodsComment implements Parcelable{
    private int id;
    private OrderEntity orderEntity;
    private UserEntity userEntity;
    private int use_time;
    private int star;
    private String content;
    private String createdAt;

    public FoodsComment() {
    }

    protected FoodsComment(Parcel in) {
        id = in.readInt();
        orderEntity = in.readParcelable(OrderEntity.class.getClassLoader());
        use_time = in.readInt();
        star = in.readInt();
        content = in.readString();
        createdAt = in.readString();
    }

    public static final Creator<FoodsComment> CREATOR = new Creator<FoodsComment>() {
        @Override
        public FoodsComment createFromParcel(Parcel in) {
            return new FoodsComment(in);
        }

        @Override
        public FoodsComment[] newArray(int size) {
            return new FoodsComment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeParcelable(orderEntity, i);
        parcel.writeInt(use_time);
        parcel.writeInt(star);
        parcel.writeString(content);
        parcel.writeString(createdAt);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public int getUse_time() {
        return use_time;
    }

    public void setUse_time(int use_time) {
        this.use_time = use_time;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public static Creator<FoodsComment> getCREATOR() {
        return CREATOR;
    }
}

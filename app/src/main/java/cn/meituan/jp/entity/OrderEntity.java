package cn.meituan.jp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 11608 on 2017/4/24.
 */

public class OrderEntity implements Parcelable {
    /**
     *  "orders": [
     {
     "id": 85,
     "food_id": 4,
     "user_id": 1,
     "amount": 0,
     "price": 0,
     "total_fee": 0,
     "created_at": "2017-04-16 20:42:09",
     "has_comment": 0,
     "food": {
     "id": 4,
     "shop_id": 2,
     "type_id": 2,
     "name": "蒜泥白肉",
     "price": 13,
     "content": "牛逼",
     "photo": "http://epaper.hldnews.com/paperdata/hldwb/20150602/m_wb11b602c_3.jpg"
     },
     "user": {
     "id": 1,
     "name": "张三",
     "nick_name": "帅哥",
     "money": 1000,
     "phone": "18080791836"
     }
     }
     */
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "food_id")
    private int foodId;
    @JSONField(name = "user_id")
    private int userId;
    @JSONField(name = "amount")
    private int amount;
    @JSONField(name = "price")
    private int price;
    @JSONField(name = "total_fee")
    private int totalFee;
    @JSONField(name = "created_at")
    private String createAt;
    @JSONField(name = "food")
    private FoodsEntity foodsEntity;
    @JSONField(name = "user")
    private UserEntity userEntity;

    public OrderEntity() {
    }

    protected OrderEntity(Parcel in) {
        id = in.readInt();
        foodId = in.readInt();
        userId = in.readInt();
        amount = in.readInt();
        price = in.readInt();
        totalFee = in.readInt();
        createAt = in.readString();
        foodsEntity = in.readParcelable(FoodsEntity.class.getClassLoader());
    }

    public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel in) {
            return new OrderEntity(in);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public FoodsEntity getFoodsEntity() {
        return foodsEntity;
    }

    public void setFoodsEntity(FoodsEntity foodsEntity) {
        this.foodsEntity = foodsEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(foodId);
        parcel.writeInt(userId);
        parcel.writeInt(amount);
        parcel.writeInt(price);
        parcel.writeInt(totalFee);
        parcel.writeString(createAt);
        parcel.writeParcelable(foodsEntity, i);
    }
}

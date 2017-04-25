package cn.meituan.jp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 11608 on 2017/4/19.
 */

public class FoodsEntity implements Parcelable {

    /**
     *  "foods": [
     {
     "id": 1,
     "shop_id": 1,
     "type_id": 1,
     "name": "姜爆鸭丝",
     "price": 15,
     "content": "还不错噢",
     "photo": "http://images.meishij.net/p/20110309/b92d7fe0013b429f3838b22343168375.jpg",
     "shop": {
     "id": 1,
     "name": "明洞炸鸡店",
     "star_num": 4,
     "packing_fee": 4,
     "min_fee": 20,
     "photo": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492513697011&di=4b4358d15a52f8e4e56d02e71bd7c8ed&imgtype=0&src=http%3A%2F%2F365jia.cn%2Fuploads%2Fnews%2Ffolder_1659049%2Fimages%2Fcfefbb2aa06f878ddec528a7582f9ded.jpg",
     "content": "好吃",
     "point_x": 16.22,
     "point_y": 22.33,
     "time": 0,
     "distance": 0,
     "phone": null,
     "sale_month": 66
     },
     "type": {
     "id": 1,
     "name": "热菜"
     }
     },
     */

    @JSONField(name = "id")
    private int id;
    @JSONField(name = "shop_id")
    private int shopId;
    @JSONField(name = "type_id")
    private int typeId;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "price")
    private int price;
    @JSONField(name = "content")
    private String content;
    @JSONField(name = "photo")
    private String photo;
    @JSONField(name = "shop")
    private BusinessEntity shop;
    @JSONField(name = "type")
    private FoodTypeEntity type;

    public FoodsEntity(){

    }


    protected FoodsEntity(Parcel in) {
        id = in.readInt();
        shopId = in.readInt();
        typeId = in.readInt();
        name = in.readString();
        price = in.readInt();
        content = in.readString();
        photo = in.readString();
        shop = in.readParcelable(BusinessEntity.class.getClassLoader());
        type = in.readParcelable(FoodTypeEntity.class.getClassLoader());
    }

    public static final Creator<FoodsEntity> CREATOR = new Creator<FoodsEntity>() {
        @Override
        public FoodsEntity createFromParcel(Parcel in) {
            return new FoodsEntity(in);
        }

        @Override
        public FoodsEntity[] newArray(int size) {
            return new FoodsEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BusinessEntity getShop() {
        return shop;
    }

    public void setShop(BusinessEntity shop) {
        this.shop = shop;
    }

    public FoodTypeEntity getType() {
        return type;
    }

    public void setType(FoodTypeEntity type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeInt(shopId);
        parcel.writeInt(typeId);
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeString(content);
        parcel.writeString(photo);
        parcel.writeParcelable(shop, i);
        parcel.writeParcelable(type, i);
    }


}

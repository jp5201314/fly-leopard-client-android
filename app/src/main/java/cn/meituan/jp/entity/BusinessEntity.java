package cn.meituan.jp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by 11608 on 2017/4/14.
 */

public class BusinessEntity implements Parcelable{
/**
 *   "id": 1,
 "name": "明洞炸鸡店",
 "star_num": 4,
 "packing_fee": 4,
 "min_fee": 20,
 "photo": "shopphoto/sa.png",
 "content": "好吃",
 "point_x": 16.22,
 "point_y": 22.33,
 "time": 0,
 "distance": 0,
 "phone": null,
 "sale_month": 66
 */
@JSONField(name = "id")
    private int id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "star_num")
    private int starNum;
    @JSONField(name = "packing_fee")
    private int packingFee;
    @JSONField(name = "min_fee")
    private int minFee;
    @JSONField(name = "photo")
    private String photo;
    @JSONField(name = "content")
    private String content;
    @JSONField(name = "point_x")
    private double pointX;
    @JSONField(name = "point_y")
    private double pointY;
    @JSONField(name = "time")
    private int time;
    @JSONField(name = "distance")
    private double distance;
    @JSONField(name = "phone")
    private String phone;
    @JSONField(name = "sale_month")
    private int saleMonth;

    public BusinessEntity(){}
    protected BusinessEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        starNum = in.readInt();
        packingFee = in.readInt();
        minFee = in.readInt();
        photo = in.readString();
        content = in.readString();
        pointX = in.readDouble();
        pointY = in.readDouble();
        time = in.readInt();
        distance = in.readDouble();
        phone = in.readString();
        saleMonth = in.readInt();
    }

    public static final Creator<BusinessEntity> CREATOR = new Creator<BusinessEntity>() {
        @Override
        public BusinessEntity createFromParcel(Parcel in) {
            return new BusinessEntity(in);
        }

        @Override
        public BusinessEntity[] newArray(int size) {
            return new BusinessEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public int getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(int packingFee) {
        this.packingFee = packingFee;
    }

    public int getMinFee() {
        return minFee;
    }

    public void setMinFee(int minFee) {
        this.minFee = minFee;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSaleMonth() {
        return saleMonth;
    }

    public void setSaleMonth(int saleMonth) {
        this.saleMonth = saleMonth;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(starNum);
        parcel.writeInt(packingFee);
        parcel.writeInt(minFee);
        parcel.writeString(photo);
        parcel.writeString(content);
        parcel.writeDouble(pointX);
        parcel.writeDouble(pointY);
        parcel.writeInt(time);
        parcel.writeDouble(distance);
        parcel.writeString(phone);
        parcel.writeInt(saleMonth);
    }
}

package cn.meituan.jp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 11608 on 2017/4/19.
 */

public class FoodTypeEntity implements Parcelable{
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "name")
    private String name;

    protected FoodTypeEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public FoodTypeEntity(){}
    public static final Creator<FoodTypeEntity> CREATOR = new Creator<FoodTypeEntity>() {
        @Override
        public FoodTypeEntity createFromParcel(Parcel in) {
            return new FoodTypeEntity(in);
        }

        @Override
        public FoodTypeEntity[] newArray(int size) {
            return new FoodTypeEntity[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}

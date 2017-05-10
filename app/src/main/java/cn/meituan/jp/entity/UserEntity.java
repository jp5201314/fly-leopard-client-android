package cn.meituan.jp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by 11608 on 2017/4/17.
 */

public class UserEntity implements Parcelable{
    /**
     * "data": {
     "id": 1,
     "name": "张三",
     "nick_name": "123",
     "money": 10,
     "phone": "1234567890"
     "address": [
     {
     "id": 1,
     "user_id": 1,
     "name": "张三",
     "address": "科华大厦",
     "phone": "1234567890",
     "lat": null,
     "lon": null
     },
     {
     "id": 2,
     "user_id": 1,
     "name": "张三",
     "address": "科华北路",
     "phone": "1234567890",
     "lat": null,
     "lon": null
     },
     {
     "id": 3,
     "user_id": 1,
     "name": "张三",
     "address": "建中大厦",
     "phone": "1234567890",
     "lat": null,
     "lon": null
     },
     {
     "id": 4,
     "user_id": 1,
     "name": "张三",
     "address": "磨子街",
     "phone": "1234567890",
     "lat": null,
     "lon": null
     }
     }
     */

    @JSONField(name = "id")
    private int id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "nick_name")
    private String nickName;

    @JSONField(name = "money")
    private int money;

    public UserEntity() {
    }

    protected UserEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        nickName = in.readString();
        money = in.readInt();
        phone = in.readString();
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel in) {
            return new UserEntity(in);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };


    public List<AddressEntity> getAddressEntityList() {
        return addressEntityList;
    }

    public void setAddressEntityList(List<AddressEntity> addressEntityList) {
        this.addressEntityList = addressEntityList;
    }

    @JSONField(name = "phone")

    private String phone;
    @JSONField(name = "address")
    private List<AddressEntity> addressEntityList;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(nickName);
        parcel.writeInt(money);
        parcel.writeString(phone);
    }
}

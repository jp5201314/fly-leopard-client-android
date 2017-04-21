package cn.meituan.jp.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by 11608 on 2017/4/17.
 */

public class UserEntity {
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
}

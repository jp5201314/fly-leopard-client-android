package cn.meituan.jp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 11608 on 2017/4/18.
 */

public class AddressEntity {

    /**
     *
     "id": 1,
     "user_id": 1,
     "name": "张三",
     "address": "科华大厦",
     "lat": null,
     "lon": null
     */

    @JSONField(name = "id")
    private int id;
    @JSONField(name = "user_id")
    private int userId;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "address")
    private String address;
    @JSONField(name = "lat")
    private double lat;
    @JSONField(name = "lon")
    private double lon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}

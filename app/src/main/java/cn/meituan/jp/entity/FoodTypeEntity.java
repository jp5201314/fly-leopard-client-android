package cn.meituan.jp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 11608 on 2017/4/19.
 */

public class FoodTypeEntity {
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "name")
    private String name;

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
}

package cn.meituan.jp.event;

/**
 * Created by Administrator on 2017/5/24.
 */

public class CollectionShopEvent {
    private String name;

    public CollectionShopEvent(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

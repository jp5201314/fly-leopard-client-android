package cn.meituan.jp.event;

/**
 * Created by Administrator on 2017/5/24.
 */

public class CancleCollectionShopEvent {
    private String name;

    public CancleCollectionShopEvent(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

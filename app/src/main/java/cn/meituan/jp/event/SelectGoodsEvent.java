package cn.meituan.jp.event;

/**
 * Created by 11608 on 2017/4/19.
 */

public class SelectGoodsEvent {
    private String name;
    private int price;
    public SelectGoodsEvent(String name,int price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }


    public int getPrice() {
        return price;
    }

}

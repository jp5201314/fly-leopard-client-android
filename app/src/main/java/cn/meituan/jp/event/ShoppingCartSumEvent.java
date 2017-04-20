package cn.meituan.jp.event;

/**
 * Created by 11608 on 2017/4/20.
 * 购物车总金额
 */

public class ShoppingCartSumEvent {
    private int sum;
    public ShoppingCartSumEvent(int sum){
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }
}

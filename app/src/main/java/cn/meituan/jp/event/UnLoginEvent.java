package cn.meituan.jp.event;

/**
 * Created by 11608 on 2017/4/13.
 */

public class UnLoginEvent {
    private int type;

    public UnLoginEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
package cn.meituan.jp.event;

/**
 * Created by 11608 on 2017/4/18.
 */

public class ExitLoginEvent {
    private int msg;

    public ExitLoginEvent(int msg) {
        this.msg = msg;
    }

    public int getType() {
        return msg;
    }
}

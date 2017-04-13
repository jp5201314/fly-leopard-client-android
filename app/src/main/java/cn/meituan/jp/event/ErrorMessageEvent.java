package cn.meituan.jp.event;

public class ErrorMessageEvent {
    private String msg;

    public ErrorMessageEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

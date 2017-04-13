package cn.meituan.jp.event;

/**
 * Created by 11608 on 2017/4/13.
 */

public class AddressEvent {
    private String address;

    public AddressEvent(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

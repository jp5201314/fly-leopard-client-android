package cn.meituan.jp.event;

/**
 * Created by 11608 on 2017/4/24.
 */

public class AddressEvent {
    private String name;
    private String phone;
    private String address;

    public AddressEvent(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}

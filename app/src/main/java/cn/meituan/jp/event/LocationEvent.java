package cn.meituan.jp.event;

/**
 * Created by 11608 on 2017/4/13.
 */

public class LocationEvent {
    private String address;

    private double lat;
    private double lon;

    public LocationEvent(String address, double lon, double lat) {
        this.address = address;
        this.lon = lon;
        this.lat = lat;

    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
    }
}

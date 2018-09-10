package com.caspar.baidu;

/**
 * Created by Administrator on 2018/9/10.
 */

public class LocationBean {
    private int GroudId;
    private double longitude;
    private double latitude;
    private String deviceName;
    private String locationName;
    private String groupDescribe;

    public LocationBean(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getGroudId() {
        return GroudId;
    }

    public void setGroudId(int groudId) {
        GroudId = groudId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGroupDescribe() {
        return groupDescribe;
    }

    public void setGroupDescribe(String groupDescribe) {
        this.groupDescribe = groupDescribe;
    }
}

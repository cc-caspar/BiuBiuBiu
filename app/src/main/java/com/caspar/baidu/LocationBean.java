package com.caspar.baidu;

/**
 * Created by Administrator on 2018/9/10.
 */

public class LocationBean {
    private int deviceId;
    private int groupId;
    private double longitude;
    private double latitude;
    private String deviceName;
    private String locationName;
    private String groupDescribe;
    private boolean isChecked;

    public LocationBean(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public LocationBean(int deviceId, int groupId, double longitude, double latitude, String deviceName, String locationName, String groupDescribe, boolean isChecked) {
        this.deviceId = deviceId;
        this.groupId = groupId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.deviceName = deviceName;
        this.locationName = locationName;
        this.groupDescribe = groupDescribe;
        this.isChecked = isChecked;
    }

    public int getGroudId() {
        return groupId;
    }

    public void setGroudId(int groupId) {
        groupId = groupId;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

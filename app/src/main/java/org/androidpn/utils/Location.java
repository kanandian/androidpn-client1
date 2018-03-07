package org.androidpn.utils;

import com.amap.api.navi.model.NaviLatLng;

import java.io.Serializable;

/**
 * Created by pro1 on 18/2/8.
 */

public class Location implements Serializable {

    private String address;
    private double longitude;
    private double latitude;

    public Location() {

    }

    public Location(String location) {
        String[] locationContents = location.split("[:,]");
        this.address = locationContents[0];
        this.longitude = Double.parseDouble(locationContents[1]);
        this.latitude = Double.parseDouble(locationContents[2]);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public NaviLatLng toNaviLatLng() {
        NaviLatLng naviLatLng = new NaviLatLng(this.getLatitude(), this.getLongitude());
        return naviLatLng;
    }

    @Override
    public String toString() {
        return address+":"+longitude+","+latitude;
    }
}

package org.androidpn.model;

import org.androidpn.info.ShopInfo;
import org.androidpn.utils.Location;

import java.lang.ref.SoftReference;

/**
 * Created by pro1 on 18/2/6.
 */

public class Bussiness {

    private Long bussinessId;
    private String imageURL;
    private String bussinessName;
    private String classification;
    private String tag;
    private String feature;
    private Location location;
    private String mobile;
    private double price;
    private String level;
    private String des;

    private String holder;

    private String startTime;
    private String endTime;

    public Bussiness() {

    }

    public Long getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Long bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = new Location(location);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ShopInfo toShopInfo() {
        ShopInfo shopInfo = new ShopInfo();

        shopInfo.setSid(String.valueOf(this.getBussinessId()));
        shopInfo.setSname(this.getBussinessName());
        shopInfo.setIname(this.getImageURL());
        shopInfo.setStype(this.getClassification());
        shopInfo.setLatitude(this.getLocation().getLatitude());
        shopInfo.setLongitude(this.getLocation().getLongitude());
        shopInfo.setSintroduction(this.getDes());
        shopInfo.setStel(this.getMobile());

        shopInfo.setSmoney(String.valueOf(this.getPrice()));
        shopInfo.setSaddress(this.getLocation().getAddress());
        shopInfo.setSlevel(this.getLevel());
        shopInfo.setSholder(this.getHolder());

        shopInfo.setStartTime(this.getStartTime());
        shopInfo.setEndTime(this.getEndTime());
        shopInfo.setFeature(this.getFeature());


        return shopInfo;
    }
}

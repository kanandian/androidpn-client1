package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class AddBussinessIQ extends IQ {

    private String businessName;
    private String imageURL;
    private String classification;
    private String tag;
    private String feature;
    private String location;
    private String mobile;
    private String des;
    private String holder;
    private String fromTime;
    private String toTime;

    public AddBussinessIQ() {

    }

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("bussiness").append(" xmlns=\"").append(
                "androidpn:add:bussiness").append("\">");
        if(businessName != null){
            buf.append("<businessName>").append(businessName).append("</businessName>");
        }
        if(imageURL != null){
            buf.append("<imageURL>").append(imageURL).append("</imageURL>");
        }
        if (classification != null) {
            buf.append("<classification>").append(classification).append("</classification>");
        }
        if (tag != null) {
            buf.append("<tag>").append(tag).append("</tag>");
        }
        if (feature != null) {
            buf.append("<feature>").append(feature).append("</feature>");
        }
        if (location != null) {
            buf.append("<location>").append(location).append("</location>");
        }
        if (mobile != null) {
            buf.append("<mobile>").append(mobile).append("</mobile>");
        }
        if (des != null) {
            buf.append("<des>").append(des).append("</des>");
        }
        if (holder != null) {
            buf.append("<holder>").append(holder).append("</holder>");
        }
        if (fromTime != null) {
            buf.append("<fromtime>").append(fromTime).append("</fromtime>");
        }
        if (toTime != null) {
            buf.append("<totime>").append(toTime).append("</totime>");
        }
        buf.append("</").append("bussiness").append("> ");
        return buf.toString();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }
}

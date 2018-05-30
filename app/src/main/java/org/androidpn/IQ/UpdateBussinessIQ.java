package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class UpdateBussinessIQ extends IQ {

    private String bussinessId;
//    private String bussinessName;
    private String feature;
    private String mobile;
    private String des;
    private String fromTime;
    private String toTime;

    public UpdateBussinessIQ() {
    }

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("bussiness").append(" xmlns=\"").append(
                "androidpn:update:bussiness").append("\">");
        if (bussinessId != null) {
            buf.append("<bussinessId>").append(bussinessId).append("</bussinessId>");
        }
        if(feature != null){
            buf.append("<feature>").append(feature).append("</feature>");
        }
        if (mobile != null) {
            buf.append("<mobile>").append(mobile).append("</mobile>");
        }
        if (des != null) {
            buf.append("<des>").append(des).append("</des>");
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

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

//    public String getBussinessName() {
//        return bussinessName;
//    }

//    public void setBussinessName(String bussinessName) {
//        this.bussinessName = bussinessName;
//    }


    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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

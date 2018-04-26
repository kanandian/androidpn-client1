package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

/**
 * Created by macpro on 2018/4/3.
 */

public class PaymentIQ extends IQ {

    private String fromUserName;
    private String toUserName;
    private String bussinessId;
    private String price;

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("payment").append(" xmlns=\"").append(
                "androidpn:iq:payment").append("\">");
        if(fromUserName != null){
            buf.append("<fromusername>").append(fromUserName).append("</fromusername>");
        }
        if(toUserName != null){
            buf.append("<tousername>").append(toUserName).append("</tousername>");
        }
        if (bussinessId != null) {
            buf.append("<bussinessid>").append(bussinessId).append("</bussinessid>");
        }
        if (price != null) {
            buf.append("<price>").append(price).append("</price>");
        }
        buf.append("</").append("payment").append("> ");
        return buf.toString();
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

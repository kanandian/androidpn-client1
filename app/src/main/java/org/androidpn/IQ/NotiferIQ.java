package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class NotiferIQ extends IQ {

    private String userName;
    private String bussinessId;
    private String title;
    private String message;

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("notifer").append(" xmlns=\"").append(
                "androidpn:notifer:bussiness").append("\">");
        if(userName != null){
            buf.append("<username>").append(userName).append("</username>");
        }
        if(bussinessId != null){
            buf.append("<bussinessid>").append(bussinessId).append("</bussinessid>");
        }
        if (title != null) {
            buf.append("<title>").append(title).append("</title>");
        }
        if (message != null) {
            buf.append("<message>").append(message).append("</message>");
        }
        buf.append("</").append("notifer").append("> ");
        return buf.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

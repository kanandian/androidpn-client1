package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class UpdateUserInfoIQ extends IQ {

    private String userName;
    private String name;
    private String mobile;

    public UpdateUserInfoIQ() {
    }

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("userinfo").append(" xmlns=\"").append(
                "androidpn:info:update").append("\">");
        if(userName != null){
            buf.append("<username>").append(userName).append("</username>");
        }
        if(name != null){
            buf.append("<name>").append(name).append("</name>");
        }
        if (mobile != null) {
            buf.append("<mobile>").append(mobile).append("</mobile>");
        }
        buf.append("</").append("userinfo").append("> ");
        return buf.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

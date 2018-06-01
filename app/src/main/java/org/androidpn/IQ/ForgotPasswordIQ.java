package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class ForgotPasswordIQ extends IQ {

    private String mobile;
    private String vcode;
    private String password;

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("manage").append(" xmlns=\"").append(
                "androidpn:forgot:password").append("\">");
        if(mobile != null){
            buf.append("<mobile>").append(mobile).append("</mobile>");
        }
        if(vcode != null){
            buf.append("<vcode>").append(vcode).append("</vcode>");
        }
        if(password != null){
            buf.append("<password>").append(password).append("</password>");
        }
        buf.append("</").append("manage").append("> ");
        return buf.toString();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

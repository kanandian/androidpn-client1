package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

/**
 * Created by pro1 on 18/3/2.
 */

public class LoginIQ extends IQ {

    private String userName;
    private String password;

    public LoginIQ() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("login").append(" xmlns=\"").append(
                "androidpn:iq:login").append("\">");
        if(userName != null){
            buf.append("<userName>").append(userName).append("</userName>");
        }
        if(password != null){
            buf.append("<password>").append(password).append("</password>");
        }
        buf.append("</").append("login").append("> ");
        return buf.toString();
    }
}

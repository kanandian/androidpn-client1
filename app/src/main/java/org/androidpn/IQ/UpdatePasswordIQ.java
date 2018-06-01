package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class UpdatePasswordIQ extends IQ {

    private String userName;
    private String opassword;
    private String npassword;

    public UpdatePasswordIQ() {
    }

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("manage").append(" xmlns=\"").append(
                "androidpn:update:password").append("\">");
        if(userName != null){
            buf.append("<userName>").append(userName).append("</userName>");
        }
        if(opassword != null){
            buf.append("<opassword>").append(opassword).append("</opassword>");
        }
        if(npassword != null){
            buf.append("<npassword>").append(npassword).append("</npassword>");
        }
        buf.append("</").append("manage").append("> ");
        return buf.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpassword() {
        return opassword;
    }

    public void setOpassword(String opassword) {
        this.opassword = opassword;
    }

    public String getNpassword() {
        return npassword;
    }

    public void setNpassword(String npassword) {
        this.npassword = npassword;
    }
}

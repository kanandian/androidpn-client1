package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

/**
 * Created by pro1 on 18/2/20.
 */

public class RegisterIQ extends IQ {

    String userName;
    String password;
    String localName;
    String localPassword;
    String name;
    String email;
    String idCard;
    String mobile;
    String sex;

    public RegisterIQ() {

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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getLocalPassword() {
        return localPassword;
    }

    public void setLocalPassword(String localPassword) {
        this.localPassword = localPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getChildElementXML() {
//        StringBuilder buf = new StringBuilder();
//        buf.append("<").append("inquiry").append(" xmlns=\"").append(
//                "androidpn:iq:inquiry").append("\">");
//        if(target != null){
//            buf.append("<target>").append(target).append("</target>");
//        }
//        if(title != null){
//            buf.append("<title>").append(title).append("</title>");
//        }
//        buf.append("</").append("inquiry").append("> ");
//        return buf.toString();

        StringBuilder buf = new StringBuilder();
        buf.append("<").append("inquiry").append(" xmlns=\"").append(
                "androidpn:iq:inquiry").append("\">");

        if(userName != null){
            buf.append("<userName>").append(userName).append("</userName>");
        }
        if(password != null){
            buf.append("<password>").append(password).append("</password>");
        }
        if(localName != null){
            buf.append("<localName>").append(localName).append("</localName>");
        }
        if(localPassword != null){
            buf.append("<localPassword>").append(localPassword).append("</localPassword>");
        }
        if(name != null){
            buf.append("<name>").append(name).append("</name>");
        }
        if(email != null){
            buf.append("<email>").append(email).append("</email>");
        }
        if(idCard != null){
            buf.append("<idCard>").append(idCard).append("</idCard>");
        }
        if(mobile != null){
            buf.append("<mobile>").append(mobile).append("</mobile>");
        }
        if(sex != null){
            buf.append("<sex>").append(sex).append("</sex>");
        }

        buf.append("</").append("inquiry").append("> ");
        return buf.toString();
    }
}

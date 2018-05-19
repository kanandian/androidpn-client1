package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

/**
 * Created by pro1 on 18/3/2.
 */

public class LoginResponseIQ extends IQ {

    private boolean isError;
    private String message;

    private String userName;
    private String password;
    private String name;
    private String mobile;

    private boolean isRealUser;
    private String imageURL;

    public LoginResponseIQ() {
        this.isError = false;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public boolean isRealUser() {
        return isRealUser;
    }

    public void setRealUser(boolean realUser) {
        isRealUser = realUser;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String getChildElementXML() {
        return null;
    }
}

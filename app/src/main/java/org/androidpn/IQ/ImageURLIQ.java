package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class ImageURLIQ extends IQ {

    private String imageURL;
    private String userName;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getChildElementXML() {
        return null;
    }
}

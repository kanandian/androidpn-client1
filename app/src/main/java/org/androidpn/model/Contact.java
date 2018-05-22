package org.androidpn.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Contact extends DataSupport implements Serializable {
    private String fromUserName;
    private String imageURL;
    private long createTime;
    private String userName;

    private int unReadCount;

    private String lastUnRead;

    private String fromJID;

    public Contact() {
        unReadCount = 0;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFromJID() {
        return fromJID;
    }

    public void setFromJID(String fromJID) {
        this.fromJID = fromJID;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public String getLastUnRead() {
        return lastUnRead;
    }

    public void setLastUnRead(String lastUnRead) {
        this.lastUnRead = lastUnRead;
    }
}

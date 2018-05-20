package org.androidpn.entity;

import org.litepal.crud.DataSupport;

public class SearchHistory extends DataSupport {

    private String key;
    private long createTime;
    private String userName;

    public SearchHistory() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}

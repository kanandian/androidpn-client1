package org.androidpn.entity;

import org.litepal.crud.DataSupport;

import java.util.StringTokenizer;

public class ChatMessage extends DataSupport {
    private String fromUserName;
    private String content;
    private long createTime;
    private String userName;
    private String fromJID;
    private int tag;
    private String target;

    public ChatMessage() {
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}

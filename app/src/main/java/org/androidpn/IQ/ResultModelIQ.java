package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class ResultModelIQ extends IQ {

    private int errCode;
    private String errMsg;
    private String action;

    public ResultModelIQ() {
    }

    @Override
    public String getChildElementXML() {
        return null;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class PaymentResponseIQ extends IQ {

    private int errCode;
    private String errMsg;

    public PaymentResponseIQ() {
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

    @Override
    public String getChildElementXML() {
        return null;
    }
}

package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class DeleteBussinessIQ extends IQ {
    private String bussinessId;

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("admin").append(" xmlns=\"").append(
                "androidpn:delete:bussiness").append("\">");
        if(bussinessId != null){
            buf.append("<bussinessid>").append(bussinessId).append("</bussinessid>");
        }
        buf.append("</").append("admin").append("> ");
        return buf.toString();
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }
}

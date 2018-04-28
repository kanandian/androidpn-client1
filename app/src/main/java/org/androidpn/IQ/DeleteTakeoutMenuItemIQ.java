package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class DeleteTakeoutMenuItemIQ extends IQ {

    private String target;
    private String itemId;

    public DeleteTakeoutMenuItemIQ() {
    }


    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("manage").append(" xmlns=\"").append(
                "androidpn:manage:takeoutitem").append("\">");
        if(target != null){
            buf.append("<target>").append(target).append("</target>");
        }
        if(itemId != null){
            buf.append("<itemid>").append(itemId).append("</itemid>");
        }
        buf.append("</").append("manage").append("> ");
        return buf.toString();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}

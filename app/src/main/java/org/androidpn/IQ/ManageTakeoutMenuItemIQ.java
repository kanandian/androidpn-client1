package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class ManageTakeoutMenuItemIQ extends IQ {

    private String target;
    private String itemId;

    private String bussinessId;
    private String foodName;
    private String price;

    public ManageTakeoutMenuItemIQ() {
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
        if(bussinessId != null){
            buf.append("<bussinessid>").append(bussinessId).append("</bussinessid>");
        }
        if(foodName != null){
            buf.append("<foodname>").append(foodName).append("</foodname>");
        }
        if(price != null){
            buf.append("<price>").append(price).append("</price>");
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

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

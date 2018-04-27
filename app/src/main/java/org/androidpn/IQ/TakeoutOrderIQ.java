package org.androidpn.IQ;

import org.androidpn.model.FoodOrderItem;
import org.jivesoftware.smack.packet.IQ;

import java.lang.reflect.Field;
import java.util.List;

public class TakeoutOrderIQ extends IQ {

    private List<FoodOrderItem> foodOrderItemList;
    private String address;
    private String note;
    private String mobile;
    private String totalPrice;

    private String bussinessId;
    private String bussinessName;

    private String fromUserName;
    private String toUserName;

    public TakeoutOrderIQ() {

    }

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("order").append(" xmlns=\"").append(
                "androidpn:takeout:order").append("\">");
        if(foodOrderItemList != null){
            for (FoodOrderItem foodOrderItem : foodOrderItemList) {
                buf.append("<orderitem");
                Class<?> clazz = foodOrderItem.getClass();
                Field[] fields = clazz.getDeclaredFields();

                try {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        buf.append(" "+field.getName() + "=\"" + field.get(foodOrderItem) + "\"");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                buf.append(" />");
            }
        }
        if (address != null) {
            buf.append("<address>").append(address).append("</address>");
        }
        if (note != null) {
            buf.append("<note>").append(note).append("</note>");
        }
        if (mobile != null) {
            buf.append("<mobile>").append(mobile).append("</mobile>");
        }
        if (totalPrice != null) {
            buf.append("<totalprice>").append(totalPrice).append("</totalprice>");
        }
        if (bussinessId != null) {
            buf.append("<bussinessid>").append(bussinessId).append("</bussinessid>");
        }
        if (bussinessName != null) {
            buf.append("<bussinessname>").append(bussinessName).append("</bussinessname>");
        }
        if (fromUserName != null) {
            buf.append("<fromusername>").append(fromUserName).append("</fromusername>");
        }
        if (toUserName != null) {
            buf.append("<tousername>").append(toUserName).append("</tousername>");
        }
        buf.append("</").append("order").append("> ");
        return buf.toString().replaceAll("[$]", "");
    }

    public List<FoodOrderItem> getFoodOrderItemList() {
        return foodOrderItemList;
    }

    public void setFoodOrderItemList(List<FoodOrderItem> foodOrderItemList) {
        this.foodOrderItemList = foodOrderItemList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}

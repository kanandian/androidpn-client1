package org.androidpn.IQ;

import org.androidpn.model.FoodOrderItem;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class OrderDetailIQ extends IQ {

    private String orderId;
    private String address;
    private String bussinessId;
    private String mobile;
    private String note;
    private String totalPrice;
    private String bussinessName;
    private String createTime;
    private String orderStatus;

    private List<FoodOrderItem> foodOrderItemList;

    @Override
    public String getChildElementXML() {
        return null;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<FoodOrderItem> getFoodOrderItemList() {
        return foodOrderItemList;
    }

    public void setFoodOrderItemList(List<FoodOrderItem> foodOrderItemList) {
        this.foodOrderItemList = foodOrderItemList;
    }
}

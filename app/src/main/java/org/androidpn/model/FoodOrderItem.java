package org.androidpn.model;

import java.io.Serializable;

public class FoodOrderItem implements Serializable {
    private long itemId;
    private String foodName;
    private double price;
    private int count;

    private long bussinessId;

    public FoodOrderItem() {

    }

    public FoodOrderItem(FoodMenuItem foodMenuItem, int count) {
        this.itemId = foodMenuItem.getItemId();
        this.foodName = foodMenuItem.getFoodName();
        this.price = foodMenuItem.getPrice();
        this.count = count;
        this.bussinessId = foodMenuItem.getBussinessId();

    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(long bussinessId) {
        this.bussinessId = bussinessId;
    }
}

package org.androidpn.IQ;

import org.androidpn.model.FoodMenuItem;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class FoodMenuIQ extends IQ {

    private List<FoodMenuItem> foodMenuItemList;

    public FoodMenuIQ() {

    }

    public List<FoodMenuItem> getFoodMenuItemList() {
        return foodMenuItemList;
    }

    public void setFoodMenuItemList(List<FoodMenuItem> foodMenuItemList) {
        this.foodMenuItemList = foodMenuItemList;
    }

    @Override
    public String getChildElementXML() {
        return null;
    }
}

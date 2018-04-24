package org.androidpn.IQprovider;

import android.util.Log;

import org.androidpn.IQ.ActivityInquiryIQ;
import org.androidpn.IQ.FoodMenuIQ;
import org.androidpn.model.Bussiness;
import org.androidpn.model.FoodMenuItem;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        Log.d("qzf", "parseIQ1: "+parser.getAttributeCount());
        FoodMenuIQ foodMenuIQ = new FoodMenuIQ();
        List<FoodMenuItem> foodMenuItemList = new ArrayList<FoodMenuItem>();
        for (boolean done = false; !done; ) {
            int eventType = parser.next();
            if (eventType == 2) {
                FoodMenuItem foodMenuItem = new FoodMenuItem();
                for(int i=0; i<parser.getAttributeCount(); i++) {
                    if("menuId".equals(parser.getAttributeName(i))) {
                        foodMenuItem.setItemId(Long.parseLong(parser.getAttributeValue(i)));
                    }
                    if("foodName".equals(parser.getAttributeName(i))) {
                        foodMenuItem.setFoodName(parser.getAttributeValue(i));
                    }
                    if("price".equals(parser.getAttributeName(i))) {
                        foodMenuItem.setPrice(Double.parseDouble(parser.getAttributeValue(i)));
                    }
                    if("bussinessId".equals(parser.getAttributeName(i))) {
                        foodMenuItem.setBussinessId(Long.parseLong(parser.getAttributeValue(i)));
                    }
                }
                foodMenuItemList.add(foodMenuItem);
            } else if (eventType == 3
                    && "foodmeniu".equals(parser.getName())) {
                done = true;
            }
        }

        foodMenuIQ.setFoodMenuItemList(foodMenuItemList);
        return foodMenuIQ;
    }
}

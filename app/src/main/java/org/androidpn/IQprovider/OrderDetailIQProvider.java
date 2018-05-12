package org.androidpn.IQprovider;

import android.util.Log;

import org.androidpn.IQ.BussinessIQ;
import org.androidpn.IQ.OrderDetailIQ;
import org.androidpn.IQ.TakeoutOrderIQ;
import org.androidpn.model.Bussiness;
import org.androidpn.model.FoodOrderItem;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {

        OrderDetailIQ orderDetailIQ = new OrderDetailIQ();

        List<FoodOrderItem> foodOrderItemList = new ArrayList<FoodOrderItem>();
        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("item".equals(parser.getName())) {
                    FoodOrderItem foodOrderItem = new FoodOrderItem();
                    for(int i=0; i<parser.getAttributeCount(); i++) {
                        Log.d("qzf", ""+i);
                        if("itemid".equals(parser.getAttributeName(i))) {
                            Log.d("qzf", "id: "+parser.getAttributeValue(i));
                            foodOrderItem.setItemId(Long.parseLong(parser.getAttributeValue(i)));
                        }
                        if("foodname".equals(parser.getAttributeName(i))) {
                            foodOrderItem.setFoodName(parser.getAttributeValue(i));
                        }
                        if("count".equals(parser.getAttributeName(i))) {
                            foodOrderItem.setCount(Integer.parseInt(parser.getAttributeValue(i)));
                        }
                        if("bussinessId".equals(parser.getAttributeName(i))) {
                            foodOrderItem.setBussinessId(Long.parseLong(parser.getAttributeValue(i)));
                        }
                        if("price".equals(parser.getAttributeName(i))) {
                            foodOrderItem.setPrice(Double.parseDouble(parser.getAttributeValue(i)));
                        }
                    }
                    foodOrderItemList.add(foodOrderItem);
                }
                if ("orderid".equals(parser.getName())) {
                    orderDetailIQ.setOrderId(parser.nextText());
                }
                if ("bussinessname".equals(parser.getName())) {
                    orderDetailIQ.setBussinessName(parser.nextText());
                }
                if ("address".equals(parser.getName())) {
                    orderDetailIQ.setAddress(parser.nextText());
                }
                if ("mobile".equals(parser.getName())) {
                    orderDetailIQ.setMobile(parser.nextText());
                }
                if ("note".equals(parser.getName())) {
                    orderDetailIQ.setNote(parser.nextText());
                }
                if ("totalprice".equals(parser.getName())) {
                    orderDetailIQ.setTotalPrice(parser.nextText());
                }
                if ("bussinessid".equals(parser.getName())) {
                    orderDetailIQ.setBussinessId(parser.nextText());
                }
                if ("orderstatus".equals(parser.getName())) {
                    orderDetailIQ.setOrderStatus(parser.nextText());
                }
            } else if (eventType == 3
                    && "order".equals(parser.getName())) {
                done = true;
            }
        }

        orderDetailIQ.setFoodOrderItemList(foodOrderItemList);

        return orderDetailIQ;
    }
}

package org.androidpn.IQprovider;

import android.util.Log;

import org.androidpn.IQ.ActivityInquiryIQ;
import org.androidpn.IQ.TakeoutListIQ;
import org.androidpn.IQ.TakeoutOrderIQ;
import org.androidpn.model.Bussiness;
import org.androidpn.model.TakeoutOrder;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class TakeoutListIQProvider implements IQProvider {

    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        TakeoutListIQ takeoutOrderIQ = new TakeoutListIQ();
        List<TakeoutOrder> takeoutOrderList = new ArrayList<TakeoutOrder>();
        for (boolean done = false; !done; ) {
            int eventType = parser.next();
            if (eventType == 2) {
                TakeoutOrder takeoutOrder = new TakeoutOrder();
                for(int i=0; i<parser.getAttributeCount(); i++) {
                    if("orderid".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setOrderId(Long.parseLong(parser.getAttributeValue(i)));
                    }
                    if("bussinessname".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setBussinessName(parser.getAttributeValue(i));
                    }
                    if("address".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setAddress(parser.getAttributeValue(i));
                    }
                    if("bussinessid".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setBussinessId(Long.parseLong(parser.getAttributeValue(i)));
                    }
                    if("mobile".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setMobile(parser.getAttributeValue(i));
                    }
                    if("note".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setNote(parser.getAttributeValue(i));
                    }
                    if("totalprice".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setTotalPrice(Double.parseDouble(parser.getAttributeValue(i)));
                    }
                    if("firstfoodname".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setFirstFoodName(parser.getAttributeValue(i));
                    }
                    if("itemcount".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setItemCount(Integer.parseInt(parser.getAttributeValue(i)));
                    }
                    if ("orderstatus".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setOrderStatus(Integer.parseInt(parser.getAttributeValue(i)));
                    }
                    if ("createtime".equals(parser.getAttributeName(i))) {
                        takeoutOrder.setCreateTime(parser.getAttributeValue(i));
                    }
                }
                takeoutOrderList.add(takeoutOrder);
            } else if (eventType == 3
                    && "order".equals(parser.getName())) {
                done = true;
            }
        }


        takeoutOrderIQ.setTakeoutOrderList(takeoutOrderList);
        return takeoutOrderIQ;
    }
}

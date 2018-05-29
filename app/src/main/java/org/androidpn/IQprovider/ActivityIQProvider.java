package org.androidpn.IQprovider;

import android.util.Log;

import org.androidpn.IQ.ActivityInquiryIQ;
import org.androidpn.model.Bussiness;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro1 on 18/1/31.
 */

public class ActivityIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        Log.d("qzf", "parseIQ1: "+parser.getAttributeCount());
        ActivityInquiryIQ activityInquiryIQ = new ActivityInquiryIQ();
        List<Bussiness> bussinessList = new ArrayList<Bussiness>();
        for (boolean done = false; !done; ) {
            int eventType = parser.next();
            Log.d("qzf", "type: "+eventType);
            if (eventType == 2) {
                Bussiness bussiness = new Bussiness();
                for(int i=0; i<parser.getAttributeCount(); i++) {
                    Log.d("qzf", ""+i);
                    if("id".equals(parser.getAttributeName(i))) {
                        Log.d("qzf", "id: "+parser.getAttributeValue(i));
                        bussiness.setBussinessId(Long.parseLong(parser.getAttributeValue(i)));
                    }
                    if("name".equals(parser.getAttributeName(i))) {
                        bussiness.setBussinessName(parser.getAttributeValue(i));
                    }
                    if("imageURL".equals(parser.getAttributeName(i))) {
                        bussiness.setImageURL(parser.getAttributeValue(i));
                    }
                    if("classification".equals(parser.getAttributeName(i))) {
                        bussiness.setClassification(parser.getAttributeValue(i));
                    }
                    if("tag".equals(parser.getAttributeName(i))) {
                        bussiness.setTag(parser.getAttributeValue(i));
                    }
                    if("location".equals(parser.getAttributeName(i))) {
                        bussiness.setLocation(parser.getAttributeValue(i));
                    }
                    if("mobile".equals(parser.getAttributeName(i))) {
                        bussiness.setMobile(parser.getAttributeValue(i));
                    }
                    if("price".equals(parser.getAttributeName(i))) {
                        bussiness.setPrice(Double.parseDouble(parser.getAttributeValue(i)));
                    }
                    if("level".equals(parser.getAttributeName(i))) {
                        bussiness.setLevel(parser.getAttributeValue(i));
                    }
                    if("des".equals(parser.getAttributeName(i))) {
                        bussiness.setDes(parser.getAttributeValue(i));
                    }
                    if("holder".equals(parser.getAttributeName(i))) {
                        bussiness.setHolder(parser.getAttributeValue(i));
                    }
                    if ("starttime".equals(parser.getAttributeName(i))) {
                        bussiness.setStartTime(parser.getAttributeValue(i));
                    }
                    if ("endtime".equals(parser.getAttributeName(i))) {
                        bussiness.setEndTime(parser.getAttributeValue(i));
                    }
                    if ("feature".equals(parser.getAttributeName(i))) {
                        bussiness.setFeature(parser.getAttributeValue(i));
                    }
                }
                bussinessList.add(bussiness);
            } else if (eventType == 3
                    && "activity".equals(parser.getName())) {
                done = true;
                Log.d("qzf", "parseActivityIQ: done");
            }
        }


        activityInquiryIQ.setBussinessList(bussinessList);
        return activityInquiryIQ;
    }
}

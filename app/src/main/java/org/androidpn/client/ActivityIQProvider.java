package org.androidpn.client;

import android.util.Log;

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
        ActivityInquiryIQ activityInquiryIQ = new ActivityInquiryIQ();
        List<Bussiness> bussinessList = new ArrayList<Bussiness>();
        for (boolean done = false; !done; ) {
            int eventType = parser.next();
            if (eventType == 2) {
                Bussiness bussiness = new Bussiness();
                for(int i=0; i<parser.getAttributeCount(); i++) {
                    if("id".equals(parser.getAttributeName(i))) {
                        bussiness.setBussinessId(Long.parseLong(parser.getAttributeValue(i)));
                    }
                    if("name".equals(parser.getAttributeName(i))) {
                        bussiness.setBusinessName(parser.getAttributeValue(i));
                    }
                    if("imageURL".equals(parser.getAttributeName(i))) {
                        bussiness.setImageURL(parser.getAttributeValue(i));
                    }
                    if("tag".equals(parser.getAttributeName(i))) {
                        bussiness.setTag(parser.getAttributeValue(i));
                    }
                    if("location".equals(parser.getAttributeName(i))) {
                        bussiness.setLocation(parser.getAttributeValue(i));
                    }
                    if("des".equals(parser.getAttributeName(i))) {
                        bussiness.setDes(parser.getAttributeValue(i));
                    }
                }
                bussinessList.add(bussiness);
            } else if (eventType == 3
                    && "response".equals(parser.getName())) {
                done = true;
            }
        }

        activityInquiryIQ.setBussinessList(bussinessList);
        return activityInquiryIQ;
    }
}

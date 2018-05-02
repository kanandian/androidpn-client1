package org.androidpn.IQprovider;

import org.androidpn.IQ.BussinessIQ;
import org.androidpn.model.Bussiness;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class OrderDetailIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {

        BussinessIQ bussinessIQ = new BussinessIQ();
        Bussiness bussiness = new Bussiness();
        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("id".equals(parser.getName())) {
                    bussiness.setBussinessId(Long.parseLong(parser.nextText()));
                }
                if ("name".equals(parser.getName())) {
                    bussiness.setBussinessName(parser.nextText());
                }
                if ("imageURL".equals(parser.getName())) {
                    bussiness.setImageURL(parser.nextText());
                }
                if ("location".equals(parser.getName())) {
                    bussiness.setLocation(parser.nextText());
                }
                if ("classification".equals(parser.getName())) {
                    bussiness.setClassification(parser.nextText());
                }
                if ("tag".equals(parser.getName())) {
                    bussiness.setTag(parser.nextText());
                }
                if ("mobile".equals(parser.getName())) {
                    bussiness.setMobile(parser.nextText());
                }
                if ("des".equals(parser.getName())) {
                    bussiness.setDes(parser.nextText());
                }
                if ("holder".equals(parser.getName())) {
                    bussiness.setHolder(parser.nextText());
                }
            } else if (eventType == 3
                    && "bussiness".equals(parser.getName())) {
                done = true;
            }
        }

        bussinessIQ.setBussiness(bussiness);

        return bussinessIQ;
    }
}

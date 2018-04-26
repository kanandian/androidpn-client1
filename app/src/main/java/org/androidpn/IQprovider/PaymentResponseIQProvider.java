package org.androidpn.IQprovider;

import android.content.Intent;

import org.androidpn.IQ.BussinessIQ;
import org.androidpn.IQ.PaymentResponseIQ;
import org.androidpn.model.Bussiness;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class PaymentResponseIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        PaymentResponseIQ paymentResponseIQ = new PaymentResponseIQ();
        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("errcode".equals(parser.getName())) {
                    paymentResponseIQ.setErrCode(Integer.parseInt(parser.nextText()));
                }
                if ("errmessage".equals(parser.getName())) {
                    paymentResponseIQ.setErrMsg(parser.nextText());
                }
            } else if (eventType == 3
                    && "payment".equals(parser.getName())) {
                done = true;
            }
        }

        return paymentResponseIQ;
    }
}

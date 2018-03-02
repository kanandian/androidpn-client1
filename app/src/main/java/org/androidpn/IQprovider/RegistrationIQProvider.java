package org.androidpn.IQprovider;

import android.util.Log;

import org.androidpn.IQ.NotificationIQ;
import org.androidpn.IQ.RegistrationResponseIQ;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by pro1 on 18/3/2.
 */

public class RegistrationIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        Log.d("qzf", "parseIQ: registration response provider");

        RegistrationResponseIQ registrationResponseIQ = new RegistrationResponseIQ();

        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("userName".equals(parser.getName())) {
                    registrationResponseIQ.setUserName(parser.nextText());
                }
                if ("password".equals(parser.getName())) {
                    registrationResponseIQ.setPassword(parser.nextText());
                }
                if ("name".equals(parser.getName())) {
                    registrationResponseIQ.setName(parser.nextText());
                }
                if ("mobile".equals(parser.getName())) {
                    registrationResponseIQ.setMobile(parser.nextText());
                }
            } else if (eventType == 3
                    && "registeration".equals(parser.getName())) {
                done = true;
            }
        }

        return registrationResponseIQ;
    }
}

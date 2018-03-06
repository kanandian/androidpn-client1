package org.androidpn.IQprovider;

import android.util.Log;

import org.androidpn.IQ.LoginIQ;
import org.androidpn.IQ.LoginResponseIQ;
import org.androidpn.IQ.RegistrationResponseIQ;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by pro1 on 18/3/2.
 */

public class LoginIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {

        Log.d("qzf", "parseIQ: LoginResponseIQ");

        LoginResponseIQ loginResponseIQ = new LoginResponseIQ();

        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("message".equals(parser.getName())) {
                    loginResponseIQ.setMessage(parser.nextText());
                    loginResponseIQ.setError(false);
                }
                if ("userName".equals(parser.getName())) {
                    loginResponseIQ.setUserName(parser.nextText());
                }
                if ("password".equals(parser.getName())) {
                    loginResponseIQ.setPassword(parser.nextText());
                }
                if ("name".equals(parser.getName())) {
                    loginResponseIQ.setName(parser.nextText());
                }
                if ("mobile".equals(parser.getName())) {
                    loginResponseIQ.setMobile(parser.nextText());
                }
            } else if (eventType == 3
                    && "login".equals(parser.getName())) {
                done = true;
            }
        }

        return loginResponseIQ;

    }
}

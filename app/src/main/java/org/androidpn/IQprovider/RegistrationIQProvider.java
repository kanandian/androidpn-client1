package org.androidpn.IQprovider;

import android.util.Log;

import org.androidpn.IQ.RegistrationResponseIQ;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by pro1 on 18/3/2.
 */

public class RegistrationIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        Log.d("qzf", "parseIQ: registration response provider");

        RegistrationResponseIQ registrationResponseIQ = new RegistrationResponseIQ();

        return registrationResponseIQ;
    }
}

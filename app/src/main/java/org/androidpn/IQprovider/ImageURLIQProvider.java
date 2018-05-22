package org.androidpn.IQprovider;

import org.androidpn.IQ.ImageURLIQ;
import org.androidpn.IQ.LoginResponseIQ;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class ImageURLIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        ImageURLIQ imageURLIQ = new ImageURLIQ();

        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("username".equals(parser.getName())) {
                    imageURLIQ.setUserName(parser.nextText());
                }
                if ("imageurl".equals(parser.getName())) {
                    imageURLIQ.setImageURL(parser.nextText());
                }
            } else if (eventType == 3
                    && "image".equals(parser.getName())) {
                done = true;
            }
        }
        return imageURLIQ;
    }
}

package org.androidpn.IQprovider;

import org.androidpn.IQ.ResultModelIQ;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class AdminResponseIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        ResultModelIQ resultModelIQ = new ResultModelIQ();
        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("errcode".equals(parser.getName())) {
                    resultModelIQ.setErrCode(Integer.parseInt(parser.nextText()));
                }
                if ("errmessage".equals(parser.getName())) {
                    resultModelIQ.setErrMsg(parser.nextText());
                }
            } else if (eventType == 3
                    && "admin".equals(parser.getName())) {
                done = true;
            }
        }
        return resultModelIQ;
    }
}

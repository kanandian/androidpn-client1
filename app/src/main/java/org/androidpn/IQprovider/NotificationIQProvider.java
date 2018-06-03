/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.IQprovider;

import org.androidpn.IQ.NotificationIQ;
import org.androidpn.model.Bussiness;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

/** 
 * This class parses incoming IQ packets to NotificationIQ objects.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class NotificationIQProvider implements IQProvider {

    public NotificationIQProvider() {
    }

    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {

        NotificationIQ notification = new NotificationIQ();
        Bussiness bussiness = new Bussiness();

        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("id".equals(parser.getName())) {
                    notification.setId(parser.nextText());
                }
                if ("apiKey".equals(parser.getName())) {
                    notification.setApiKey(parser.nextText());
                }
                if ("title".equals(parser.getName())) {
                    notification.setTitle(parser.nextText());
                }
                if ("message".equals(parser.getName())) {
                    notification.setMessage(parser.nextText());
                }
                if ("uri".equals(parser.getName())) {
                    notification.setUri(parser.nextText());
                }
                if ("bussinessid".equals(parser.getName())) {
                    bussiness.setBussinessId(Long.parseLong(parser.nextText()));
                }
                if ("bussinessname".equals(parser.getName())) {
                    bussiness.setBussinessName(parser.nextText());
                }
                if ("classification".equals(parser.getName())) {
                    bussiness.setClassification(parser.nextText());
                }
                if ("des".equals(parser.getName())) {
                    bussiness.setDes(parser.nextText());
                }
                if ("imageurl".equals(parser.getName())) {
                    bussiness.setImageURL(parser.nextText());
                }
                if ("level".equals(parser.getName())) {
                    bussiness.setLevel(parser.nextText());
                }
                if ("location".equals(parser.getName())) {
                    bussiness.setLocation(parser.nextText());
                }
                if ("mobile".equals(parser.getName())) {
                    bussiness.setMobile(parser.nextText());
                }
                if ("price".equals(parser.getName())) {
                    bussiness.setPrice(Double.parseDouble(parser.nextText()));
                }
                if ("tag".equals(parser.getName())) {
                    bussiness.setTag(parser.nextText());
                }
                if ("holder".equals(parser.getName())) {
                    bussiness.setHolder(parser.nextText());
                }
                if ("starttime".equals(parser.getName())) {
                    bussiness.setStartTime(parser.nextText());
                }
                if ("endtime".equals(parser.getName())) {
                    bussiness.setEndTime(parser.nextText());
                }
                if ("feature".equals(parser.getName())) {
                    bussiness.setFeature(parser.nextText());
                }

            } else if (eventType == 3
                    && "notification".equals(parser.getName())) {
                done = true;
            }
        }

        if (bussiness.getBussinessId() != null && !"".equals(bussiness.getBussinessId())) {
            notification.setBussiness(bussiness);
        }

        return notification;
    }

}

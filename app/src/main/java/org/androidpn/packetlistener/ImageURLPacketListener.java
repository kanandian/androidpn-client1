package org.androidpn.packetlistener;

import android.app.Activity;
import android.content.ContentValues;
import android.media.Image;

import org.androidpn.IQ.ImageURLIQ;
import org.androidpn.demoapp.ChatActivity;
import org.androidpn.demoapp.FrameActivity;
import org.androidpn.demoapp.MyMessageActivity;
import org.androidpn.enity.MessageInfo;
import org.androidpn.model.Contact;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.Constants;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;
import org.litepal.crud.DataSupport;

public class ImageURLPacketListener implements PacketListener {
    @Override
    public void processPacket(Packet packet) {
        if (packet instanceof ImageURLIQ) {
            ImageURLIQ imageURLIQ = (ImageURLIQ) packet;


            Contact contact = new Contact();
            contact.setImageURL(imageURLIQ.getImageURL());
            contact.updateAll("fromUserName = ?", imageURLIQ.getUserName());


            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if (activity instanceof ChatActivity) {
                ChatActivity chatActivity = (ChatActivity) activity;

                if (imageURLIQ.getUserName().equals(chatActivity.getToJID())) {
                    chatActivity.updateImage(imageURLIQ.getImageURL());
                }

            } else if (activity instanceof FrameActivity) {
                FrameActivity frameActivity = (FrameActivity) activity;
                MyMessageActivity myMessageActivity = (MyMessageActivity) frameActivity.getLocalActivityManager().getActivity("sign");
                myMessageActivity.updateImage();
            }
        }
    }
}

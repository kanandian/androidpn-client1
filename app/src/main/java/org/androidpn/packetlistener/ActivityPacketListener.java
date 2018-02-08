package org.androidpn.packetlistener;

import android.app.Activity;
import android.util.Log;

import org.androidpn.IQ.ActivityInquiryIQ;
import org.androidpn.client.LogUtil;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.MainActivity;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by pro1 on 18/2/6.
 */

public class ActivityPacketListener implements PacketListener {

    private static final String LOGTAG = LogUtil
            .makeLogTag(ActivityPacketListener.class);

    private final XmppManager xmppManager;

    public ActivityPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        Log.d(LOGTAG, "processPacket: " + packet.toXML());
        if(packet instanceof ActivityInquiryIQ) {
            ActivityInquiryIQ activityInquiryIQ = (ActivityInquiryIQ) packet;
//            for(Bussiness bussiness : activityInquiryIQ.getBussinessList()) {
//                Log.d(LOGTAG, bussiness.getBusinessName());
//            }
            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if(activity instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.setBussinessList(activityInquiryIQ.getBussinessList());
            }
        }
    }
}

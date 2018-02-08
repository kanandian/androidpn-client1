package org.androidpn.packetlistener;

import android.app.Activity;
import android.util.Log;

import org.androidpn.IQ.BussinessIQ;
import org.androidpn.client.LogUtil;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.BussinessInfoActivity;
import org.androidpn.model.Bussiness;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by pro1 on 18/2/8.
 */

public class BussinessPacketListener implements PacketListener {

    private static final String LOGTAG = LogUtil
            .makeLogTag(BussinessPacketListener.class);

    private final XmppManager xmppManager;

    public BussinessPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        Log.d(LOGTAG, "processPacket: " + packet.toXML());

        if(packet instanceof BussinessIQ) {
            BussinessIQ bussinessIQ = (BussinessIQ) packet;

            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if(activity instanceof BussinessInfoActivity) {
                BussinessInfoActivity bussinessInfoActivity = (BussinessInfoActivity) activity;
                bussinessInfoActivity.setBussiness(bussinessIQ.getBussiness());
            }
        }


    }
}

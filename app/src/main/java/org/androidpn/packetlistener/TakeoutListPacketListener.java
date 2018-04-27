package org.androidpn.packetlistener;

import android.app.Activity;

import org.androidpn.IQ.TakeoutListIQ;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.TakeoutListActivity;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

public class TakeoutListPacketListener implements PacketListener {

    private final XmppManager xmppManager;

    public TakeoutListPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        if (packet instanceof TakeoutListIQ) {
            TakeoutListIQ takeoutListIQ = (TakeoutListIQ) packet;

            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if (activity instanceof TakeoutListActivity) {
                TakeoutListActivity takeoutListActivity = (TakeoutListActivity) activity;
                takeoutListActivity.setContentList(takeoutListIQ.getTakeoutOrderList());
            }
        }
    }
}

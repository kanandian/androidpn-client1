package org.androidpn.packetlistener;

import android.app.Activity;

import org.androidpn.IQ.OrderDetailIQ;
import org.androidpn.client.LogUtil;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.OrderDetailActivity;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

public class OrderDetailPacketListener implements PacketListener {

    private static final String LOGTAG = LogUtil
            .makeLogTag(OrderDetailPacketListener.class);

    private final XmppManager xmppManager;

    public OrderDetailPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        if (packet instanceof OrderDetailIQ) {
            OrderDetailIQ orderDetailIQ = (OrderDetailIQ) packet;

            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if (activity instanceof OrderDetailActivity) {
                OrderDetailActivity orderDetailActivity = (OrderDetailActivity) activity;
                orderDetailActivity.setContentList(orderDetailIQ);
            }
        }
    }
}

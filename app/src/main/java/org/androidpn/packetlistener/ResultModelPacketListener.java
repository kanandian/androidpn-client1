package org.androidpn.packetlistener;

import android.app.Activity;

import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.BaseActivity;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

public class ResultModelPacketListener implements PacketListener {

    private final XmppManager xmppManager;

    public ResultModelPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        if (packet instanceof ResultModelIQ) {
            ResultModelIQ resultModelIQ = (ResultModelIQ) packet;

            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if (activity instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) activity;

                baseActivity.updateForResponse(resultModelIQ);
            }
        }
    }
}

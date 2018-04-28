package org.androidpn.packetlistener;

import android.app.Activity;

import org.androidpn.IQ.FoodMenuIQ;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.EditTakeoutMenuActivity;
import org.androidpn.demoapp.TakeOutActivity;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

public class FoodMenuPacketListener implements PacketListener {

    private final XmppManager xmppManager;

    public FoodMenuPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        if (packet instanceof FoodMenuIQ) {
            FoodMenuIQ foodMenuIQ = (FoodMenuIQ) packet;

            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if (activity instanceof TakeOutActivity) {
                TakeOutActivity takeOutActivity = (TakeOutActivity) activity;
                takeOutActivity.setContentList(foodMenuIQ.getFoodMenuItemList());
            } else if (activity instanceof EditTakeoutMenuActivity) {
                EditTakeoutMenuActivity editTakeoutMenuActivity = (EditTakeoutMenuActivity) activity;
                editTakeoutMenuActivity.setContentList(foodMenuIQ.getFoodMenuItemList());
            }
        }
    }
}

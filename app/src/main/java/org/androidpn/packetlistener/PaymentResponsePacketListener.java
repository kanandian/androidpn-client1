package org.androidpn.packetlistener;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import org.androidpn.IQ.PaymentResponseIQ;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.OrderSuccessActivity;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

public class PaymentResponsePacketListener implements PacketListener {


    private final XmppManager xmppManager;

    public PaymentResponsePacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        if (packet instanceof PaymentResponseIQ) {
            PaymentResponseIQ paymentResponseIQ = (PaymentResponseIQ) packet;

            if (paymentResponseIQ.getErrCode() == 0) {
                Intent intent = new Intent(ActivityHolder.getInstance().getCurrentActivity(), OrderSuccessActivity.class);
                ActivityHolder.getInstance().getCurrentActivity().startActivity(intent);
            } else {
                Toast.makeText(ActivityHolder.getInstance().getCurrentActivity(), paymentResponseIQ.getErrMsg(), Toast.LENGTH_LONG).show();
            }

        }
    }
}

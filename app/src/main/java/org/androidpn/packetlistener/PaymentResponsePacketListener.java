package org.androidpn.packetlistener;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import org.androidpn.IQ.PaymentResponseIQ;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.AddBussinessActivity;
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

            Activity activity = ActivityHolder.getInstance().getCurrentActivity();

            if (paymentResponseIQ.getErrCode() == 0) {
                if (activity instanceof AddBussinessActivity) {
                    activity.finish();
                    return;
                }
                Intent intent = new Intent(activity, OrderSuccessActivity.class);
                activity.startActivity(intent);
            } else {
                Toast.makeText(activity, paymentResponseIQ.getErrMsg(), Toast.LENGTH_LONG).show();
            }

        }
    }
}

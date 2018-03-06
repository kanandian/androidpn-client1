package org.androidpn.packetlistener;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import org.androidpn.IQ.LoginResponseIQ;
import org.androidpn.client.XmppManager;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by pro1 on 18/3/2.
 */

public class LoginReponsePacketListener implements PacketListener {

    private XmppManager xmppManager;

    public LoginReponsePacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        if (packet instanceof LoginResponseIQ) {
            LoginResponseIQ loginResponseIQ = (LoginResponseIQ) packet;

            if (loginResponseIQ.isError()) {
                Toast.makeText(ActivityHolder.getInstance().getCurrentActivity(), loginResponseIQ.getMessage(), Toast.LENGTH_LONG);
            } else {
                String userName = loginResponseIQ.getUserName();
                String password = loginResponseIQ.getPassword();
                String name = loginResponseIQ.getName();
                String mobile = loginResponseIQ.getMobile();

                UserInfoHolder userInfoHolder = UserInfoHolder.getInstance();

                //设置UserInfoHolder中的数据
                userInfoHolder.setUserName(userName);
                userInfoHolder.setPassword(password);
                userInfoHolder.setName(name);
                userInfoHolder.setMobile(mobile);
                userInfoHolder.setAuth(true);

                ActivityHolder.getInstance().closeActivity();
            }
        }
    }
}

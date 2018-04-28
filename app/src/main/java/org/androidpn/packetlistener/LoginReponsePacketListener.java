package org.androidpn.packetlistener;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.androidpn.IQ.LoginResponseIQ;
import org.androidpn.client.Constants;
import org.androidpn.client.XmppManager;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;

import java.lang.reflect.Field;

/**
 * Created by pro1 on 18/3/2.
 */

public class LoginReponsePacketListener implements PacketListener {

    private XmppManager xmppManager;
    private SharedPreferences sharedPrefs;

    public LoginReponsePacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
        sharedPrefs = ActivityHolder.getInstance().getCurrentActivity().getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
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


                //设置SharePerfrence中的数据
                SharedPreferences.Editor editor = sharedPrefs.edit();

                editor.putString(Constants.XMPP_USERNAME, userName);
                editor.putString(Constants.XMPP_PASSWORD, password);

                editor.commit();

                //修改xmppmanager中的数据
                xmppManager.setUsername(userName);
                xmppManager.setPassword(password);

                //修改xmppconnection中的数据
                UserInfoHolder.getInstance().setUserToConnection(userName);

                ActivityHolder.getInstance().closeActivity();

                ActivityHolder.getInstance().refreshAllFrameAcrivity();
            }
        }
    }

}

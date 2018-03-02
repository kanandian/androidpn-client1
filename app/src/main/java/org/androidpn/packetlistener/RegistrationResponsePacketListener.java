package org.androidpn.packetlistener;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.androidpn.IQ.RegistrationResponseIQ;
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

public class RegistrationResponsePacketListener implements PacketListener {

    private XmppManager xmppManager;
    private SharedPreferences sharedPrefs;

    public RegistrationResponsePacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
        sharedPrefs = ActivityHolder.getInstance().getCurrentActivity().getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    @Override
    public void processPacket(Packet packet) {
        Log.d("qzf", "processPacket: registration listener");

        if(packet instanceof RegistrationResponseIQ) {
            RegistrationResponseIQ registrationResponseIQ = (RegistrationResponseIQ) packet;

            String userName = registrationResponseIQ.getUserName();
            String password = registrationResponseIQ.getPassword();
            String name = registrationResponseIQ.getName();
            String mobile = registrationResponseIQ.getMobile();

            SharedPreferences.Editor editor = sharedPrefs.edit();

            editor.putString(Constants.XMPP_USERNAME, userName);
            editor.putString(Constants.XMPP_PASSWORD, password);

            //修改xmppmanager中的数据
            xmppManager.setUsername(userName);
            xmppManager.setPassword(password);

            //修改xmppconnection中的数据
            setUserToConnection(userName);


            //设置UserInfoHolder中的数据
            UserInfoHolder.getInstance().setUserName(userName);
            UserInfoHolder.getInstance().setPassword(password);
            UserInfoHolder.getInstance().setName(name);
            UserInfoHolder.getInstance().setMobile(mobile);

        } else {

        }



    }

    private void setUserToConnection(String userName) {
        XMPPConnection connection = ActivityHolder.getInstance().getConnection();
        String user = connection.getUser();

        String[] userContents = user.split("[@]");
        String newUser = userName+"@"+userContents[1];

        Class<?> clazz = connection.getClass();
        try {
            Field userField = clazz.getDeclaredField("user");
            userField.setAccessible(true);
            userField.set(connection, newUser);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}

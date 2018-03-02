package org.androidpn.packetlistener;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

        if(true) {
            SharedPreferences.Editor editor = sharedPrefs.edit();

            String userName = UserInfoHolder.getInstance().getUserName();
            String password = UserInfoHolder.getInstance().getPassword();

            editor.putString(Constants.XMPP_USERNAME, userName);
            editor.putString(Constants.XMPP_PASSWORD, password);

            xmppManager.setUsername(userName);
            xmppManager.setPassword(password);

            setUserToConnection(userName);
        } else {
            String userName = xmppManager.getUsername();
            String password = xmppManager.getPassword();

            UserInfoHolder.getInstance().setUserName(userName);
            UserInfoHolder.getInstance().setPassword(password);
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

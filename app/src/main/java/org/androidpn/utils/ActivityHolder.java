package org.androidpn.utils;

import android.app.Activity;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by pro1 on 18/1/31.
 */

public class ActivityHolder {

    private static ActivityHolder activityManager = null;

    private Activity currentActivity;

    private XMPPConnection connection;

    private ActivityHolder() {

    }

    public static ActivityHolder getInstance () {
        if (activityManager == null) {
            synchronized (ActivityHolder.class) {
                if(activityManager == null) {
                    activityManager = new ActivityHolder();
                }
            }
        }
        return activityManager;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public XMPPConnection getConnection() {
        return connection;
    }

    public void setConnection(XMPPConnection connection) {
        this.connection = connection;
    }

    public void sendPacket(Packet packet) {
        if(connection == null) {





        } else {
            connection.sendPacket(packet);
        }
    }
}

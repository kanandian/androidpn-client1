package org.androidpn.utils;

import android.app.Activity;
import android.content.Intent;

import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.BaseActivity;
import org.androidpn.demoapp.FrameActivity;
import org.androidpn.demoapp.NavigationActivity;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro1 on 18/1/31.
 */

public class ActivityHolder {

    private static ActivityHolder activityManager = null;

    private Activity currentActivity;

    private XMPPConnection connection;

    private XmppManager xmppManager;

    private List<BaseActivity> frameActivityList;

    private ActivityHolder() {
        frameActivityList = new ArrayList<BaseActivity>();
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

    public void closeActivity() {
        Activity activity = getCurrentActivity();
        if (activity instanceof FrameActivity) {

        } else {
            activity.finish();
        }
    }

    public void addFrameActivity(BaseActivity activity) {
        this.frameActivityList.add(activity);
    }

    public void refreshAllFrameAcrivity() {
        for (BaseActivity activity : this.frameActivityList) {
            activity.refresh();
        }
    }

    public XMPPConnection getConnection() {
        return connection;
    }

    public XmppManager getXmppManager() {
        return xmppManager;
    }

    public void setXmppManager(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
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


    public void startNaviActivity(Location location) {
        Intent intent = new Intent(this.getCurrentActivity(), NavigationActivity.class);
        intent.putExtra("location", location);
        this.getCurrentActivity().startActivity(intent);
    }

}

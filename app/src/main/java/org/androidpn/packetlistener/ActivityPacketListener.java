package org.androidpn.packetlistener;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.androidpn.IQ.ActivityInquiryIQ;
import org.androidpn.client.LogUtil;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.FrameActivity;
import org.androidpn.demoapp.MainActivity;
import org.androidpn.demoapp.SearchActivity;
import org.androidpn.demoapp.ShopListActivity;
import org.androidpn.info.ShopInfo;
import org.androidpn.model.Bussiness;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro1 on 18/2/6.
 */

public class ActivityPacketListener implements PacketListener {

    private static final String LOGTAG = LogUtil
            .makeLogTag(ActivityPacketListener.class);

    private final XmppManager xmppManager;

    public ActivityPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        if(packet instanceof ActivityInquiryIQ) {
            ActivityInquiryIQ activityInquiryIQ = (ActivityInquiryIQ) packet;
//            for(Bussiness bussiness : activityInquiryIQ.getBussinessList()) {
//                Log.d(LOGTAG, bussiness.getBusinessName());
//            }
            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if(activity instanceof ShopListActivity) {
                ShopListActivity shopListActivity = (ShopListActivity) activity;

                List<ShopInfo> shopInfoList = bussinessListToShopInfoList(activityInquiryIQ.getBussinessList());

                shopListActivity.setContentList(shopInfoList);
            } else if (activity instanceof FrameActivity) {
                FrameActivity frameActivity = (FrameActivity) activity;

                SearchActivity searchActivity = (SearchActivity) frameActivity.getLocalActivityManager().getActivity("search");

                List<ShopInfo> shopList = bussinessListToShopInfoList(activityInquiryIQ.getBussinessList());

                searchActivity.setContentList(shopList);
                Log.d("qzf:", "processPacket: SearchActivity");

            }
        }
    }

    private List<ShopInfo> bussinessListToShopInfoList(List<Bussiness> bussinessList) {
        List<ShopInfo> shopInfoList = new ArrayList<ShopInfo>();

        for(Bussiness bussiness : bussinessList) {
            shopInfoList.add(bussiness.toShopInfo());
        }

        return shopInfoList;

    }
}

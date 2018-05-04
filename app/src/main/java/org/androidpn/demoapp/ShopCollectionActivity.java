package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

public class ShopCollectionActivity extends ShopListActivity {

    @Override
    public void sendInquiryIQ() {
//        super.sendInquiryIQ();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.GET);

        inquiryIQ.setTarget("collection");
        inquiryIQ.setTitle(UserInfoHolder.getInstance().getUserName());

        Log.d("collection", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }
}

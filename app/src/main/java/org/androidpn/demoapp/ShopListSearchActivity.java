package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.utils.ActivityHolder;

public class ShopListSearchActivity extends ShopListActivity {

    private InquiryIQ inquiryIQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        inquiryIQ = (InquiryIQ) intent.getSerializableExtra("inquiryIQ");
    }

    @Override
    public void sendInquiryIQ() {
//        super.sendInquiryIQ();
        Log.d("qzf", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }
}

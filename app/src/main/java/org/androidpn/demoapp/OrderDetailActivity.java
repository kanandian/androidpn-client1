package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

public class OrderDetailActivity extends BaseActivity {

    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        this.orderId = intent.getStringExtra("orderId");


    }

    @Override
    public void sendInquiryIQ() {
        super.sendInquiryIQ();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.GET);

        inquiryIQ.setTarget("orderdetail");
        inquiryIQ.setTitle(orderId);

        Log.d("orderdetail", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }
}

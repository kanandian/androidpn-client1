package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

public class TakeoutOrderActivity extends TakeoutListActivity {

    private String bussinessId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_takeouts);

    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();

        this.bussinessId = intent.getStringExtra("bussinessId");

    }

    @Override
    public void sendInquiryIQ() {
//        super.sendInquiryIQ();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.GET);

        inquiryIQ.setTarget("takeout");
        inquiryIQ.setTitle("bussiness:"+ bussinessId);

        Log.d("takeoutlsit", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }
}

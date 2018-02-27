package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.model.Bussiness;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

/**
 * Created by pro1 on 18/2/8.
 */

public class BussinessInfoActivity extends BaseActivity {

    long bussinessId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bussiness_info);

        Intent intent = getIntent();
        bussinessId = intent.getLongExtra("bussinessId", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ActivityHolder.getInstance().setCurrentActivity(BussinessInfoActivity.this);

        sendInquiryIQ();

    }

    public void setBussiness(Bussiness bussiness) {

    }

    public void sendInquiryIQ() {
        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setTarget("bussiness");
        inquiryIQ.setTitle(String.valueOf(bussinessId));
        inquiryIQ.setType(IQ.Type.GET);
        Log.d("qzf's log", "sendInquiryIQ: "+inquiryIQ.toXML());
        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }


}

package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.TakeoutBussinessListAdapter;
import org.androidpn.adapter.TakeoutListAdapter;
import org.androidpn.model.TakeoutOrder;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class TakeoutOrderActivity extends TakeoutListActivity {

    private static final int UPDATE_UI = 0;

    private String bussinessId;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                TakeoutBussinessListAdapter adapter = new TakeoutBussinessListAdapter(TakeoutOrderActivity.this, R.layout.item_takeout_bussiness, getTakeoutOrders());
                getTakeoutList().setAdapter(adapter);
            }
        }
    };

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

    @Override
    public void setContentList(Object contentList) {
        super.setContentList(contentList);

        if (contentList instanceof List) {
            setTakeoutOrders((List<TakeoutOrder>) contentList);
            Message msg = new Message();
            msg.what = UPDATE_UI;
            handler.sendMessage(msg);
        }
    }
}

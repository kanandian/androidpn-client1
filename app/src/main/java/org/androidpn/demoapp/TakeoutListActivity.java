package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.ShopAdapter;
import org.androidpn.adapter.TakeoutListAdapter;
import org.androidpn.model.TakeoutOrder;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class TakeoutListActivity extends BaseActivity {

    private static final int UPDATE_UI = 0;

    private ListView takeoutList;
    private TakeoutListAdapter adapter;

    private List<TakeoutOrder> takeoutOrders;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                adapter = new TakeoutListAdapter(TakeoutListActivity.this, R.layout.item_takeout, takeoutOrders);
                takeoutList.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeout_list);

        initData();
    }

    public void initData() {
        takeoutList = (ListView) findViewById(R.id.list_takeout_orer);


        takeoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TakeoutOrder takeoutOrder = takeoutOrders.get(i);

                Intent intent = new Intent(TakeoutListActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId", String.valueOf(takeoutOrder.getOrderId()));
                startActivity(intent);
            }
        });
    }

    public void setTakeoutOrders(List<TakeoutOrder> takeoutOrders) {
        this.takeoutOrders = takeoutOrders;
    }

    public List<TakeoutOrder> getTakeoutOrders() {
        return takeoutOrders;
    }

    public ListView getTakeoutList() {
        return takeoutList;
    }

    @Override
    public void sendInquiryIQ() {
        super.sendInquiryIQ();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.GET);

        inquiryIQ.setTarget("takeout");
        inquiryIQ.setTitle("all:"+ UserInfoHolder.getInstance().getUserName());

        Log.d("takeoutlsit", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }

    @Override
    public void setContentList(Object contentList) {
        super.setContentList(contentList);

        if (contentList instanceof List) {
            this.takeoutOrders = (List<TakeoutOrder>) contentList;
            Message msg = new Message();
            msg.what = UPDATE_UI;
            handler.sendMessage(msg);
        }
    }

}

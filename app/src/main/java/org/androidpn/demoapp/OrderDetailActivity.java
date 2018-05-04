package org.androidpn.demoapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.IQ.OrderDetailIQ;
import org.androidpn.adapter.ShopAdapter;
import org.androidpn.adapter.TakeoutListAdapter;
import org.androidpn.adapter.TakeoutOrderAdapter;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

public class OrderDetailActivity extends BaseActivity {

    private String orderId;

    private OrderDetailIQ orderDetailIQ;

    private ImageView backBtn;

    private ListView orderList;

    private TakeoutOrderAdapter adapter;

    private TextView addressText;
    private TextView noteText;
    private TextView mobileText;

    private final int UPDATE_UI = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                adapter = new TakeoutOrderAdapter(OrderDetailActivity.this, R.layout.item_takeout_order, orderDetailIQ.getFoodOrderItemList());
                orderList.setAdapter(adapter);

                addressText.setText(orderDetailIQ.getAddress());
                noteText.setText(orderDetailIQ.getNote());
                mobileText.setText(orderDetailIQ.getMobile());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        this.orderId = intent.getStringExtra("orderId");

        backBtn = (ImageView) findViewById(R.id.Login_back);

        orderList = (ListView) findViewById(R.id.list_takeout_orer);

        addressText = (TextView) findViewById(R.id.text_address);
        noteText = (TextView) findViewById(R.id.text_note);
        mobileText = (TextView) findViewById(R.id.text_mobile);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetailActivity.this.finish();
            }
        });
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

    @Override
    public void setContentList(Object contentList) {
        super.setContentList(contentList);

        if (contentList instanceof OrderDetailIQ) {
            this.orderDetailIQ = (OrderDetailIQ) contentList;

            Message msg = new Message();
            msg.what = UPDATE_UI;
            handler.sendMessage(msg);
        }
    }
}

package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.CommentAdapter;
import org.androidpn.adapter.EditTakeoutMenuAdapter;
import org.androidpn.model.FoodMenuItem;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.SideslipListView;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class EditTakeoutMenuActivity extends BaseActivity {

    private static final int UPDATE_UI = 0;
    private String bussinessId;

    private SideslipListView sideslipListView;
    private Button addButton;

    List<FoodMenuItem> foodMenuItemList;

    private EditTakeoutMenuAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                adapter = new EditTakeoutMenuAdapter(EditTakeoutMenuActivity.this, sideslipListView, foodMenuItemList);
                sideslipListView.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);


        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        this.bussinessId = intent.getStringExtra("bussinessId");

        sideslipListView = (SideslipListView) findViewById(R.id.list_takeout_order);
        addButton = (Button) findViewById(R.id.btn_add_food);
    }

    @Override
    public void sendInquiryIQ() {
        super.sendInquiryIQ();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.GET);
        inquiryIQ.setTarget("menu_food");
        inquiryIQ.setTitle(bussinessId);

        Log.d("qzf:", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }

    @Override
    public void setContentList(Object contentList) {
        super.setContentList(contentList);

        if (contentList instanceof List) {
            this.foodMenuItemList = (List<FoodMenuItem>) contentList;

            Message msg = new Message();
            msg.what = UPDATE_UI;
            handler.sendMessage(msg);

        }
    }
}

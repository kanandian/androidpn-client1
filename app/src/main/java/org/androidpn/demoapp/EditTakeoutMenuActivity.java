package org.androidpn.demoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.IQ.ManageTakeoutMenuItemIQ;
import org.androidpn.adapter.CommentAdapter;
import org.androidpn.adapter.EditTakeoutMenuAdapter;
import org.androidpn.model.FoodMenuItem;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.SideslipListView;
import org.jivesoftware.smack.packet.IQ;

import java.util.ArrayList;
import java.util.List;

public class EditTakeoutMenuActivity extends BaseActivity {

    private static final int UPDATE_UI = 0;
    private String bussinessId;

    private SideslipListView sideslipListView;
    private Button addButton;

    List<FoodMenuItem> foodMenuItemList;

    private EditTakeoutMenuAdapter adapter;

    private List<Runnable> runnableList;

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

        this.runnableList = new ArrayList<Runnable>();

        sideslipListView = (SideslipListView) findViewById(R.id.list_takeout_order);
        addButton = (Button) findViewById(R.id.btn_add_food);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout item = new LinearLayout(EditTakeoutMenuActivity.this);
                String inflater = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater vi = (LayoutInflater)EditTakeoutMenuActivity.this.getSystemService(inflater);
                vi.inflate(R.layout.dialog_input_menu, item, true);

                final EditText foodNameEdit = (EditText) item.findViewById(R.id.edit_food_name);
                final EditText priceEdit = (EditText) item.findViewById(R.id.edit_price);

                AlertDialog.Builder builder = new AlertDialog.Builder(EditTakeoutMenuActivity.this)
                        .setTitle("输入")
                        .setView(item)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String foodName = foodNameEdit.getText().toString();
                                String price = priceEdit.getText().toString();

                                ManageTakeoutMenuItemIQ manageTakeoutMenuItemIQ = new ManageTakeoutMenuItemIQ();
                                manageTakeoutMenuItemIQ.setType(IQ.Type.SET);

                                manageTakeoutMenuItemIQ.setTarget("add");
                                manageTakeoutMenuItemIQ.setBussinessId(bussinessId);
                                manageTakeoutMenuItemIQ.setFoodName(foodName);
                                manageTakeoutMenuItemIQ.setPrice(price);

                                Log.d("addmenuitem", "onClick: "+manageTakeoutMenuItemIQ.toXML());

                                ActivityHolder.getInstance().sendPacket(manageTakeoutMenuItemIQ);

                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    public String getBussinessId() {
        return bussinessId;
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

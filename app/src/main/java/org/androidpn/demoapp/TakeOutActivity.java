package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.FoodMenuAdapter;
import org.androidpn.info.ShopInfo;
import org.androidpn.model.FoodMenuItem;
import org.androidpn.model.FoodOrderItem;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TakeOutActivity extends BaseActivity {

    private static final int UPDATE_UI = 1;

    private ShopInfo shopInfo;
    private double totalPrice = 0;

    private List<FoodMenuItem> foodMenuItemList;
    private FoodMenuAdapter adapter;

    private ListView menuList;

    private TextView totalPriceText;

    private Button accountButton;

    private HashMap<Integer, Integer> orderMap = new HashMap<Integer, Integer>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                adapter = new FoodMenuAdapter(TakeOutActivity.this, R.layout.item_menu_food, foodMenuItemList);
                menuList.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food);

        initData();

    }

    @Override
    public void sendInquiryIQ() {
        super.sendInquiryIQ();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.GET);
        inquiryIQ.setTarget("menu_food");
        inquiryIQ.setTitle(shopInfo.getSid());

        Log.d("qzf:", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }


    private void initData() {
        Intent intent = getIntent();
        shopInfo = (ShopInfo) intent.getSerializableExtra("shopInfo");

        menuList = (ListView) findViewById(R.id.list_menu_food);
        totalPriceText = (TextView) findViewById(R.id.text_total_price);

        accountButton = (Button) findViewById(R.id.btn_account);


        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FoodOrderItem> foodOrderItemList = new ArrayList<FoodOrderItem>();

                for (int position : orderMap.keySet()) {
                    FoodMenuItem foodMenuItem = foodMenuItemList.get(position);
                    FoodOrderItem foodOrderItem = new FoodOrderItem(foodMenuItem, orderMap.get(position));
                    foodOrderItemList.add(foodOrderItem);
                }

                Intent intent1 = new Intent(TakeOutActivity.this, TakeoutOrderDetailActivity.class);
                intent1.putExtra("orderList", (Serializable) foodOrderItemList);
                intent1.putExtra("totalPrice", String.valueOf(totalPrice));
                intent1.putExtra("shopInfo", shopInfo);

                startActivity(intent1);
            }
        });
    }


    public void addFood(int position) {
        if (orderMap.containsKey(position)) {
            orderMap.put(position, orderMap.get(position)+1);
        } else {
            orderMap.put(position, 1);
        }

        calculatePrice();
    }

    public void subFood(int position) {
        int count = orderMap.get(position);
        if (count <= 1) {
            orderMap.remove(position);
        } else {
            orderMap.put(position, count-1);
        }

        calculatePrice();
    }

    public void calculatePrice() {
        double totalPrice = 0;
        for (int position : orderMap.keySet()) {
            FoodMenuItem foodMenuItem = foodMenuItemList.get(position);
            totalPrice += foodMenuItem.getPrice()*orderMap.get(position);
        }

        this.totalPrice = totalPrice;
        totalPriceText.setText("共计："+totalPrice+"元");
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

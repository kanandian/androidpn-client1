package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.androidpn.model.FoodOrderItem;

import java.util.List;

public class TakeoutOrderDetailActivity extends BaseActivity {

    private ListView orderList;
    private TextView addressText;
    private TextView noteText;

    private TextView totalPriceText;
    private Button paymentButton;

    private List<FoodOrderItem> foodOrderItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeout_order);

        initData();

    }

    private void initData() {
        Intent intent = getIntent();

        this.foodOrderItemList = (List<FoodOrderItem>) intent.getSerializableExtra("orderList");

        orderList = (ListView) findViewById(R.id.list_takeout_orer);
        addressText = (TextView) findViewById(R.id.text_address);
        noteText = (TextView) findViewById(R.id.text_note);

        totalPriceText = (TextView) findViewById(R.id.text_total_price);
        paymentButton = (Button) findViewById(R.id.btn_payment);
    }
}

package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.PaymentIQ;
import org.androidpn.IQ.TakeoutOrderIQ;
import org.androidpn.adapter.TakeoutOrderAdapter;
import org.androidpn.info.ShopInfo;
import org.androidpn.model.FoodOrderItem;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class TakeoutOrderDetailActivity extends BaseActivity {

    private ListView orderList;
    private EditText addressEdit;
    private EditText noteEdit;
    private EditText mobileEdit;

    private TextView totalPriceText;
    private Button paymentButton;

    private List<FoodOrderItem> foodOrderItemList;
    private String totalPrice;
    private ShopInfo shopInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeout_order);

        initData();

    }

    private void initData() {
        Intent intent = getIntent();

        this.foodOrderItemList = (List<FoodOrderItem>) intent.getSerializableExtra("orderList");
        this.totalPrice = intent.getStringExtra("totalPrice");
        this.shopInfo = (ShopInfo) intent.getSerializableExtra("shopInfo");

        orderList = (ListView) findViewById(R.id.list_takeout_orer);
        addressEdit = (EditText) findViewById(R.id.edit_address);
        noteEdit = (EditText) findViewById(R.id.edit_note);
        mobileEdit = (EditText) findViewById(R.id.edit_mobile);


        totalPriceText = (TextView) findViewById(R.id.text_total_price);
        paymentButton = (Button) findViewById(R.id.btn_payment);

        TakeoutOrderAdapter adapter = new TakeoutOrderAdapter(TakeoutOrderDetailActivity.this, R.layout.item_takeout_order, this.foodOrderItemList);
        orderList.setAdapter(adapter);

        totalPriceText.setText("应付："+this.totalPrice+"元");

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkInfo();
                payment();
            }
        });
    }

//    private void checkInfo() {
//
//    }

    private void payment() {
//        PaymentIQ paymentIQ = new PaymentIQ();
//        paymentIQ.setType(IQ.Type.SET);
//
//        paymentIQ.setBussinessId(shopInfo.getSid());
//        paymentIQ.setFromUserName(UserInfoHolder.getInstance().getUserName());
//        paymentIQ.setToUserName(shopInfo.getSholder());
//
//        paymentIQ.setPrice(totalPrice);
//
//        Log.d("qzf", "payment: "+paymentIQ.toXML());
//
//        ActivityHolder.getInstance().sendPacket(paymentIQ);

        String address = addressEdit.getText().toString();
        String note = noteEdit.getText().toString();
        String mobile = mobileEdit.getText().toString();

        if (address == null || "".equals(address)) {
            Toast.makeText(TakeoutOrderDetailActivity.this, "配送地址不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (mobile == null || "".equals(mobile)) {
            Toast.makeText(TakeoutOrderDetailActivity.this, "联系电话不能为空", Toast.LENGTH_LONG).show();
            return;
        }


        TakeoutOrderIQ takeoutOrderIQ = new TakeoutOrderIQ();
        takeoutOrderIQ.setType(IQ.Type.SET);

        takeoutOrderIQ.setFoodOrderItemList(this.foodOrderItemList);
        takeoutOrderIQ.setAddress(address);
        takeoutOrderIQ.setNote(note);
        takeoutOrderIQ.setMobile(mobile);
        takeoutOrderIQ.setTotalPrice(totalPrice);
        takeoutOrderIQ.setBussinessId(shopInfo.getSid());
        takeoutOrderIQ.setBussinessName(shopInfo.getSname());

        takeoutOrderIQ.setFromUserName(UserInfoHolder.getInstance().getUserName());
        takeoutOrderIQ.setToUserName(shopInfo.getSholder());

        Log.d("takeoutorderpayment", "payment: "+takeoutOrderIQ.toXML());

        ActivityHolder.getInstance().sendPacket(takeoutOrderIQ);

    }
}

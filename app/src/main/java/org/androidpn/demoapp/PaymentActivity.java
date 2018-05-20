package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.IQ.PaymentIQ;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

/**
 * Created by macpro on 2018/4/3.
 */

public class PaymentActivity extends BaseActivity {

    private EditText amountEdit;

    private Button paymentBtn;

    private String toUserName;
    private String bussinessId;
    private String bussinessName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        toUserName = intent.getStringExtra("toUserName");
        bussinessId = intent.getStringExtra("bussinessId");
        bussinessName = intent.getStringExtra("bussinessName");

        amountEdit = (EditText) findViewById(R.id.edit_amount);
        paymentBtn = (Button) findViewById(R.id.btn_payment);

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPaymentIQ();
            }
        });
    }

    public void sendPaymentIQ() {
        String amount = amountEdit.getText().toString();

        String fromUserName = UserInfoHolder.getInstance().getUserName();

        PaymentIQ paymentIQ = new PaymentIQ();
        paymentIQ.setType(IQ.Type.SET);

        paymentIQ.setFromUserName(fromUserName);
        paymentIQ.setToUserName(toUserName);
        paymentIQ.setPrice(amount);
        paymentIQ.setBussinessId(bussinessId);
        paymentIQ.setBussinessName(bussinessName);

        Log.d("qzf's log", "sendPaymentIQ: "+paymentIQ.toXML());

        ActivityHolder.getInstance().sendPacket(paymentIQ);
    }
}

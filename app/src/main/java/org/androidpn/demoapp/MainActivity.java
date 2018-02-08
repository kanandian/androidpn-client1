package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.model.Bussiness;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

/**
 * Created by pro1 on 18/1/30.
 */

public class MainActivity extends Activity {

    Button naviButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        naviButton = (Button) findViewById(R.id.btn_navi);

        naviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

        // Start the service
//        ServiceManager serviceManager = new ServiceManager(this);
//        serviceManager.setNotificationIcon(R.drawable.notification);
//        serviceManager.startService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityHolder.getInstance().setCurrentActivity(MainActivity.this);

        sendInquiryIQ();
    }

    public void setBussinessList(List<Bussiness> bussinessList) {

    }

    private void sendInquiryIQ () {
        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setTarget("activity");
        inquiryIQ.setTitle("main");
        inquiryIQ.setType(IQ.Type.GET);
        Log.d("qzf's log", "sendInquiryIQ: "+inquiryIQ.toXML());
        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }
}

package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.BussinessAdapter;
import org.androidpn.model.Bussiness;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

/**
 * Created by pro1 on 18/1/30.
 */

public class MainActivity extends Activity {


    private static final int UPDATE_UI = 0;

    private ListView listView;
    private BussinessAdapter bussinessAdapter;

    private List<Bussiness> bussinessList;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == UPDATE_UI){
                bussinessAdapter = new BussinessAdapter(MainActivity.this, R.layout.item_bussiness, bussinessList);
                listView.setAdapter(bussinessAdapter);
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        listView = (ListView) findViewById(R.id.list_bussinesses);



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
        this.bussinessList = bussinessList;
        Message msg = new Message();
        msg.what = UPDATE_UI;
        handler.sendMessage(msg);
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

package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.MyFragmentPagerAdapter;
import org.androidpn.model.Bussiness;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

/**
 * Created by pro1 on 18/1/30.
 */

public class MainActivity extends AppCompatActivity {

    private Button naviButton;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab tab1;
    private TabLayout.Tab tab2;
    private TabLayout.Tab tab3;
    private TabLayout.Tab tab4;




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

    private void initView() {
        //使用适配器将ViewPager与Fragment绑定在一起
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //指定Tab位置
        tab1 = tabLayout.getTabAt(0);
        tab2 = tabLayout.getTabAt(1);
        tab3 = tabLayout.getTabAt(2);
        tab4 = tabLayout.getTabAt(3);

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

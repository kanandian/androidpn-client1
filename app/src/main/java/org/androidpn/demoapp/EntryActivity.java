package org.androidpn.demoapp;

import org.androidpn.client.ServiceManager;
import org.androidpn.utils.ActivityHolder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class EntryActivity extends Activity {

    public static final int TO_OTHER_ACTIVITY = 0;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what == TO_OTHER_ACTIVITY){
                Activity activity = (Activity) msg.obj;
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//		Button inquiryButton = (Button) findViewById(R.id.button_inquiry);
//		inquiryButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(MainActivity.this, InformationInquiryActivity.class);
//				startActivity(intent);
//			}
//		});
//
//		Button questionConsaltButton = (Button) findViewById(R.id.button_question_consalt);
//		questionConsaltButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(MainActivity.this, ProblemConsaltActivity.class);
//				startActivity(intent);
//			}
//		});
//
//		Button contactUsButton = (Button) findViewById(R.id.button_contact_us);
//		contactUsButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				Intent intent = new Intent(MainActivity.this, ContactUsActivity.class);
//				startActivity(intent);
//			}
//		});
//
//		Button notificationHistoryButton = (Button) findViewById(R.id.button_notification_history);
//		notificationHistoryButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				Intent intent = new Intent(MainActivity.this, NotificationHistoryActivity.class);
//				startActivity(intent);
//			}
//		});

        ServiceManager serviceManager = new ServiceManager(this);
        serviceManager.setNotificationIcon(R.drawable.notification);
        serviceManager.startService();

        new StartActivityThread(EntryActivity.this).start();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        ActivityHolder.getInstance().setCurrentActivity(EntryActivity.this);
    }


    class StartActivityThread extends Thread {

        private Activity activity;

        public StartActivityThread(Activity activity){
            this.activity = activity;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(ActivityHolder.getInstance().getConnection() == null || !ActivityHolder.getInstance().getConnection().isAuthenticated()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            Message msg = new Message();
            msg.what = TO_OTHER_ACTIVITY;
            msg.obj = activity;
            handler.sendMessage(msg);
        }
    }

}

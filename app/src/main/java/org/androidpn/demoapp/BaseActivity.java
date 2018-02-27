package org.androidpn.demoapp;

import android.app.Activity;

import org.androidpn.utils.ActivityHolder;

/**
 * Created by pro1 on 18/2/27.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();

        ActivityHolder.getInstance().setCurrentActivity(this);

        sendInquiryIQ();
    }

    public void sendInquiryIQ() {

    }

    public void setContentList(Object contentList) {

    }
}

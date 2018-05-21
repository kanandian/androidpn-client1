package org.androidpn.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.utils.ActivityHolder;

/**
 * Created by pro1 on 18/2/27.
 */

public class BaseActivity extends Activity {

    public static final int UPDATE_UI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

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

    public void refresh() {

    }

    public void updateForResponse(ResultModelIQ resultModelIQ) {

    }
}

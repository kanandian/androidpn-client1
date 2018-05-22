package org.androidpn.demoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.utils.ActivityHolder;

public class BaseAppCompatActivity extends AppCompatActivity {

    public final static int UPDATE_UI = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.IQ.UpdateBussinessIQ;
import org.androidpn.IQ.UpdateUserInfoIQ;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

public class UpdateBussinessInfoActivity extends BaseActivity {

    private String bussinessId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        bussinessId = intent.getStringExtra("bussinessId");


    }

    private void sendUpdateBussinessInfoIQ() {
        UpdateBussinessIQ updateBussinessIQ = new UpdateBussinessIQ();
        updateBussinessIQ.setType(IQ.Type.SET);

        updateBussinessIQ.setBussinessId(bussinessId);

        Log.d("qzf", "sendUpdateBussinessInfoIQ: "+updateBussinessIQ.toXML());
        ActivityHolder.getInstance().sendPacket(updateBussinessIQ);
    }

    @Override
    public void updateForResponse(ResultModelIQ resultModelIQ) {
        super.updateForResponse(resultModelIQ);

        UpdateBussinessInfoActivity.this.finish();
    }
}

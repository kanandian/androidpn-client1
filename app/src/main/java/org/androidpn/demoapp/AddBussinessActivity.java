package org.androidpn.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidpn.IQ.AddBussinessIQ;
import org.androidpn.utils.ActivityHolder;

public class AddBussinessActivity extends BaseActivity {

    private String[] classfications = {};
    private String[] tags = {};

    private TextView addButton;

    private ArrayAdapter<String> classficationAdapter;
    private ArrayAdapter<String> tagAdapter;

    private Spinner classficationSpinner;
    private Spinner tagSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addbussiness);

        initData();
        setListeners();
    }

    private void initData() {
        classficationAdapter = new ArrayAdapter<String>(AddBussinessActivity.this, R.layout.spinnner_item_selected, classfications);
        tagAdapter  = new ArrayAdapter<String>(AddBussinessActivity.this, R.layout.spinnner_item_selected, tags);

        classficationSpinner = (Spinner) findViewById(R.id.classification);
        tagSpinner = (Spinner) findViewById(R.id.tag);

        classficationSpinner.setAdapter(classficationAdapter);
        tagSpinner.setAdapter(tagAdapter);

        addButton = (TextView) findViewById(R.id.btn_add_bussiness);

    }

    private void setListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bussinessName = "";
                String imageURL = "";
                String classification = "";
                String tag = "";
                String location = "";
                String mobile = "";
                String des = "";

                AddBussinessIQ addBussinessIQ = new AddBussinessIQ();
                addBussinessIQ.setBusinessName(bussinessName);
                addBussinessIQ.setImageURL(imageURL);
                addBussinessIQ.setClassification(classification);
                addBussinessIQ.setTag(tag);
                addBussinessIQ.setLocation(location);
                addBussinessIQ.setMobile(mobile);
                addBussinessIQ.setDes(des);

                Log.d("qzf's", "onClick: sendPacket:" + addBussinessIQ.toXML());

                ActivityHolder.getInstance().sendPacket(addBussinessIQ);
            }
        });
    }


}

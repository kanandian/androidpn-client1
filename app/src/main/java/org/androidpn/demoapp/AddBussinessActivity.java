package org.androidpn.demoapp;

import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidpn.IQ.AddBussinessIQ;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.TagsHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

public class AddBussinessActivity extends BaseActivity {

    private String[] classfications = {"美食", "景点", "酒店", "酒吧", "电影", "外卖"};
    private String[] tags = {};

    private EditText bussinessNameEdit;
    private EditText mobileEdit;
    private EditText desEdit;

    private TextView addButton;

    private ArrayAdapter<String> classficationAdapter;
    private ArrayAdapter<String> tagAdapter;

    private Spinner classficationSpinner;
    private Spinner tagSpinner;

    private String classification;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addbussiness);

        initData();
        setListeners();
    }

    private void initData() {
        classficationAdapter = new ArrayAdapter<String>(AddBussinessActivity.this, R.layout.spinnner_item_selected, classfications);
//        tagAdapter  = new ArrayAdapter<String>(AddBussinessActivity.this, R.layout.spinnner_item_selected, tags);

        classficationSpinner = (Spinner) findViewById(R.id.classification);
        tagSpinner = (Spinner) findViewById(R.id.tag);

        classficationSpinner.setAdapter(classficationAdapter);
//        tagSpinner.setAdapter(tagAdapter);

        bussinessNameEdit = (EditText) findViewById(R.id.bussiness_name);
        mobileEdit = (EditText) findViewById(R.id.edit_mobile);
        desEdit = (EditText) findViewById(R.id.edit_des);

        addButton = (TextView) findViewById(R.id.btn_add_bussiness);


        classficationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classification = classfications[i];
                tags = TagsHolder.getTags(classification);

                tagAdapter  = new ArrayAdapter<String>(AddBussinessActivity.this, R.layout.spinnner_item_selected, tags);
                tagSpinner.setAdapter(tagAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tag = tags[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bussinessName = bussinessNameEdit.getText().toString();
                String imageURL = "";
                String location = "杭州:120,30";
                String mobile = mobileEdit.getText().toString();
                String des = desEdit.getText().toString();

                AddBussinessIQ addBussinessIQ = new AddBussinessIQ();
                addBussinessIQ.setType(IQ.Type.SET);

                addBussinessIQ.setBusinessName(bussinessName);
                addBussinessIQ.setImageURL(imageURL);
                addBussinessIQ.setClassification(classification);
                addBussinessIQ.setTag(tag);
                addBussinessIQ.setLocation(location);
                addBussinessIQ.setMobile(mobile);
                addBussinessIQ.setDes(des);
                addBussinessIQ.setHolder(UserInfoHolder.getInstance().getUserName());

                Log.d("qzf's", "onClick: sendPacket:" + addBussinessIQ.toXML());

                ActivityHolder.getInstance().sendPacket(addBussinessIQ);
            }
        });
    }


}

package org.androidpn.demoapp;

import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.androidpn.IQ.AddBussinessIQ;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.DateFormatUtil;
import org.androidpn.utils.Location;
import org.androidpn.utils.LocationHolder;
import org.androidpn.utils.TagsHolder;
import org.androidpn.utils.TimeOfDay;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

import java.sql.Time;

public class AddBussinessActivity extends BaseAppCompatActivity {

    private String[] classfications = {"美食", "景点", "酒店", "酒吧", "电影", "外卖"};
//    private String[] tags = {};

    private EditText bussinessNameEdit;
    private EditText mobileEdit;
    private EditText desEdit;
    private EditText tagEdit;
    private EditText featuresEdit;

    private TextView addButton;

    private TextView locationText;

    private TextView startTime;
    private TextView endTime;

    private ArrayAdapter<String> classficationAdapter;
//    private ArrayAdapter<String> tagAdapter;

    private Spinner classficationSpinner;
//    private Spinner tagSpinner;

    private String classification;
//    private String tag;

//    private TimePicker timePicker1;
//    private TimePicker timePicker2;

    private Button locateButton;

    private ImageView backBtn;

    private Location location;

//    private TimeOfDay fromTime;
//    private TimeOfDay toTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addbussiness);

        initData();
        setListeners();
    }

    private void initData() {
//        fromTime = new TimeOfDay();
//        toTime = new TimeOfDay();

        classficationAdapter = new ArrayAdapter<String>(AddBussinessActivity.this, R.layout.spinnner_item_selected, classfications);
//        tagAdapter  = new ArrayAdapter<String>(AddBussinessActivity.this, R.layout.spinnner_item_selected, tags);

        classficationSpinner = (Spinner) findViewById(R.id.classification);
//        tagSpinner = (Spinner) findViewById(R.id.tag);

        classficationSpinner.setAdapter(classficationAdapter);
//        tagSpinner.setAdapter(tagAdapter);

        bussinessNameEdit = (EditText) findViewById(R.id.bussiness_name);
        mobileEdit = (EditText) findViewById(R.id.edit_mobile);
        desEdit = (EditText) findViewById(R.id.edit_des);
        tagEdit = (EditText) findViewById(R.id.tag);
        featuresEdit = (EditText) findViewById(R.id.bussiness_features);

//        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
//        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);

        locationText = (TextView) findViewById(R.id.text_location);

        addButton = (TextView) findViewById(R.id.btn_add_bussiness);

        startTime = (TextView) findViewById(R.id.start_time);
        endTime = (TextView) findViewById(R.id.end_time);

        backBtn = (ImageView) findViewById(R.id.btn_back);

        locateButton = (Button) findViewById(R.id.btn_locate);

        classficationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classification = classfications[i];
//                tags = TagsHolder.getTags(classification);

//                tagAdapter  = new ArrayAdapter<String>(AddBussinessActivity.this, R.layout.spinnner_item_selected, tags);
//                tagSpinner.setAdapter(tagAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
//                fromTime.setHour(hour);
//                fromTime.setMinute(minute);
//            }
//        });
//
//        timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
//                toTime.setHour(hour);
//                toTime.setMinute(minute);
//            }
//        });

//        tagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                tag = tags[i];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTimePickerDialog(startTime);
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTimePickerDialog(endTime);
            }
        });



        locateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = LocationHolder.getInstance().getLocation();
                locationText.setText(location.getAddress());
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBussinessActivity.this.finish();
            }
        });

    }

    private void setListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bussinessName = bussinessNameEdit.getText().toString();
                String imageURL = "";
                String locationStr = location.toString();
                String mobile = mobileEdit.getText().toString();
                String des = desEdit.getText().toString();
                String tag = tagEdit.getText().toString();
                String feature = featuresEdit.getText().toString();
                String startTimeStr = startTime.getText().toString();
                String endTimeStr = endTime.getText().toString();

                AddBussinessIQ addBussinessIQ = new AddBussinessIQ();
                addBussinessIQ.setType(IQ.Type.SET);

                addBussinessIQ.setBusinessName(bussinessName);
                addBussinessIQ.setImageURL(imageURL);
                addBussinessIQ.setClassification(classification);
                addBussinessIQ.setTag(tag);
                addBussinessIQ.setFeature(feature);
                addBussinessIQ.setLocation(locationStr);
                addBussinessIQ.setMobile(mobile);
                addBussinessIQ.setDes(des);
                addBussinessIQ.setHolder(UserInfoHolder.getInstance().getUserName());
                addBussinessIQ.setFromTime(startTimeStr);
                addBussinessIQ.setToTime(endTimeStr);

                Log.d("qzf's", "onClick: sendPacket:" + addBussinessIQ.toXML());

                ActivityHolder.getInstance().sendPacket(addBussinessIQ);
            }
        });
    }

    private void createTimePickerDialog(final TextView textView) {
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        textView.setText(DateFormatUtil.getHourAndMinute(millseconds));
                    }
                })
                .setCancelStringId("Cancel")
                .setSureStringId("Sure")
                .setTitleStringId("TimePicker")
//                .setYearText("Year")
//                .setMonthText("Month")
//                .setDayText("Day")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getSupportFragmentManager(), "时间选择");
    }


}

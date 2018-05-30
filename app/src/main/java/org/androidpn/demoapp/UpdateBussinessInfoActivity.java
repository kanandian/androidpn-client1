package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.IQ.UpdateBussinessIQ;
import org.androidpn.IQ.UpdateUserInfoIQ;
import org.androidpn.info.ShopInfo;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.DateFormatUtil;
import org.jivesoftware.smack.packet.IQ;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UpdateBussinessInfoActivity extends BaseAppCompatActivity {

    @Bind(R.id.bussiness_features)
    EditText bussinessFeature;
    @Bind(R.id.start_time)
    TextView startTimeText;
    @Bind(R.id.end_time)
    TextView endTimeText;
    @Bind(R.id.edit_mobile)
    EditText mobileEdit;
    @Bind(R.id.edit_des)
    EditText desEdit;

    @Bind(R.id.btn_update_bussiness)
    TextView updateButton;

    private ShopInfo shopInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bussiness);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        shopInfo = (ShopInfo) intent.getSerializableExtra("shopInfo");

        bussinessFeature.setText(shopInfo.getFeature());
        startTimeText.setText(shopInfo.getStartTime());
        endTimeText.setText(shopInfo.getEndTime());
        mobileEdit.setText(shopInfo.getStel());
        desEdit.setText(shopInfo.getSdetails());

        startTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTimePickerDialog(startTimeText);
            }
        });

        endTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTimePickerDialog(endTimeText);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUpdateBussinessInfoIQ();
            }
        });
    }

    private void sendUpdateBussinessInfoIQ() {
        String feature = bussinessFeature.getText().toString();
        String startTime = startTimeText.getText().toString();
        String endTime = endTimeText.getText().toString();
        String mobile = mobileEdit.getText().toString();
        String des = desEdit.getText().toString();

        UpdateBussinessIQ updateBussinessIQ = new UpdateBussinessIQ();
        updateBussinessIQ.setType(IQ.Type.SET);

        updateBussinessIQ.setBussinessId(shopInfo.getSid());
        updateBussinessIQ.setFeature(feature);
        updateBussinessIQ.setFromTime(startTime);
        updateBussinessIQ.setToTime(endTime);
        updateBussinessIQ.setMobile(mobile);
        updateBussinessIQ.setDes(des);

        Log.d("qzf", "sendUpdateBussinessInfoIQ: "+updateBussinessIQ.toXML());
        ActivityHolder.getInstance().sendPacket(updateBussinessIQ);
    }

    @Override
    public void updateForResponse(ResultModelIQ resultModelIQ) {
        super.updateForResponse(resultModelIQ);

        UpdateBussinessInfoActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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

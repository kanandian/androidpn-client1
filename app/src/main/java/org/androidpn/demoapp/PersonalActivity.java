package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.IQ.UpdateUserInfoIQ;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

/**
 * Created by pro1 on 18/2/19.
 */

public class PersonalActivity extends BaseActivity {

    private EditText nameEdit;
    private EditText mobileEdit;

    private TextView updateButton;
    private TextView logoutButton;
    private TextView updateImageButton;

    private String name;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal);

        initData();

    }

    private void initData() {
        nameEdit = (EditText) findViewById(R.id.edit_name);
        mobileEdit = (EditText) findViewById(R.id.edit_mobile);

        updateButton = (TextView) findViewById(R.id.btn_update_info);
        logoutButton = (TextView) findViewById(R.id.btn_logout);
        updateImageButton = (TextView) findViewById(R.id.btn_update_image);

        nameEdit.setText(UserInfoHolder.getInstance().getName());
        mobileEdit.setText(UserInfoHolder.getInstance().getMobile());



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEdit.getText().toString();
                mobile = mobileEdit.getText().toString();

                UpdateUserInfoIQ updateUserInfoIQ = new UpdateUserInfoIQ();
                updateUserInfoIQ.setType(IQ.Type.SET);

                updateUserInfoIQ.setUserName(UserInfoHolder.getInstance().getUserName());
                updateUserInfoIQ.setName(name);
                updateUserInfoIQ.setMobile(mobile);

                Log.d("updateuserinfo", "onClick: "+updateUserInfoIQ.toXML());

                ActivityHolder.getInstance().sendPacket(updateUserInfoIQ);

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoHolder.getInstance().setAuth(false);
                UserInfoHolder.getInstance().setImageURL("");
                ActivityHolder.getInstance().closeActivity();
                ActivityHolder.getInstance().refreshAllFrameAcrivity();
            }
        });

        updateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivity.this, UploadImageActivity.class);
                intent.putExtra("imageName", UserInfoHolder.getInstance().getUserName());
                intent.putExtra("target", "user");
                startActivity(intent);
            }
        });
    }

    @Override
    public void updateForResponse(ResultModelIQ resultModelIQ) {
        super.updateForResponse(resultModelIQ);

        if (resultModelIQ.getErrCode() == 0) {
            UserInfoHolder.getInstance().setName(name);
            UserInfoHolder.getInstance().setMobile(mobile);

            PersonalActivity.this.finish();
        } else {
            Toast.makeText(PersonalActivity.this, resultModelIQ.getErrMsg(), Toast.LENGTH_LONG).show();
        }
    }
}

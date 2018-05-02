package org.androidpn.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;

/**
 * Created by pro1 on 18/2/19.
 */

public class PersonalActivity extends BaseActivity {

    private TextView logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal);

        initData();

    }

    private void initData() {
        logoutButton = (TextView) findViewById(R.id.btn_logout);



        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoHolder.getInstance().setAuth(false);
                ActivityHolder.getInstance().closeActivity();
                ActivityHolder.getInstance().refreshAllFrameAcrivity();
            }
        });
    }
}

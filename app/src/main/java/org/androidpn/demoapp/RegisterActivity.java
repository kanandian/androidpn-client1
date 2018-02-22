package org.androidpn.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidpn.IQ.RegisterIQ;
import org.androidpn.utils.ActivityHolder;

/**
 * Created by pro1 on 18/2/20.
 */

public class RegisterActivity extends Activity {

    private EditText usernameEdit;
    private EditText passwordEdit;
    private EditText cpasswordEdit;
    private EditText idcardEdit;
    private EditText nameEdit;
    private EditText sexEdit;
    private EditText mobileEdit;
    private EditText emailEdit;

    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        usernameEdit = (EditText) findViewById(R.id.edit_username);
        passwordEdit = (EditText) findViewById(R.id.edit_password);
        cpasswordEdit = (EditText) findViewById(R.id.edit_check_password);
        idcardEdit = (EditText) findViewById(R.id.edit_idcard);
        nameEdit = (EditText) findViewById(R.id.edit_name);
        sexEdit = (EditText) findViewById(R.id.edit_sex);
        mobileEdit = (EditText) findViewById(R.id.edit_mobile);
        emailEdit = (EditText) findViewById(R.id.edit_email);

        registerButton = (Button) findViewById(R.id.button_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "send", Toast.LENGTH_SHORT).show();
                sendRegisterIQ();
            }
        });

    }

    private void sendRegisterIQ() {
        String userName = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String cpassword = cpasswordEdit.getText().toString();
        String idCard = idcardEdit.getText().toString();
        String name = nameEdit.getText().toString();
        String sex = sexEdit.getText().toString();
        String mobile = mobileEdit.getText().toString();
        String email = emailEdit.getText().toString();

        if(!password.equals(cpassword)) {
            Toast.makeText(RegisterActivity.this, "两次密码输入不一致！", Toast.LENGTH_LONG).show();
        } else {
            RegisterIQ registerIQ = new RegisterIQ();

            registerIQ.setUserName(userName);
            registerIQ.setPassword(password);
            registerIQ.setIdCard(idCard);
            registerIQ.setName(name);
            registerIQ.setSex(sex);
            registerIQ.setMobile(mobile);
            registerIQ.setEmail(email);

            Log.d("Regsiter Packet", "sendRegisterIQ: "+registerIQ.toXML());

            ActivityHolder.getInstance().sendPacket(registerIQ);

        }

    }
}

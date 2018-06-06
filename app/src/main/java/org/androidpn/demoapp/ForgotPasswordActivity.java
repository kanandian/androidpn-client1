package org.androidpn.demoapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.ForgotPasswordIQ;
import org.androidpn.IQ.InquiryIQ;
import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.HandlerUtil;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends BaseActivity {

    @Bind(R.id.edit_mobile)
    EditText mobileEdit;
    @Bind(R.id.edit_vcode)
    EditText vcodeEdit;
    @Bind(R.id.edit_npassword)
    EditText npasswordEdit;
    @Bind(R.id.edit_cpassword)
    EditText cpasswordEdit;
    @Bind(R.id.btn_get_vcode)
    Button getVCodeBtn;
    @Bind(R.id.btn_update_password)
    TextView udpateButton;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                String message = (String) msg.obj;
                Toast.makeText(ActivityHolder.getInstance().getCurrentActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        getVCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVcode();
            }
        });

        udpateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword();
            }
        });

    }

    private void getVcode() {
        String mobile = mobileEdit.getText().toString();
        String userName = UserInfoHolder.getInstance().getUserName();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.SET);

        inquiryIQ.setTarget("sendmessage");
        inquiryIQ.setTitle(mobile);
        inquiryIQ.setUserName(userName);

        Log.d("qzf", "getvcode"+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }

    private void forgotPassword() {
        String mobile = mobileEdit.getText().toString();
        String vcode = vcodeEdit.getText().toString();
        String password = npasswordEdit.getText().toString();
        String cpassword = cpasswordEdit.getText().toString();

        if (vcode == null || "".equals(vcode)) {
            Toast.makeText(ForgotPasswordActivity.this, "请填写验证码", Toast.LENGTH_LONG).show();
            return;
        }
        if (password == null || "".equals(password) || cpassword == null || "".equals(cpassword)) {
            Toast.makeText(ForgotPasswordActivity.this, "请填写密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (!password.equals(cpassword)) {
            Toast.makeText(ForgotPasswordActivity.this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
            return;
        }


        ForgotPasswordIQ forgotPasswordIQ = new ForgotPasswordIQ();
        forgotPasswordIQ.setType(IQ.Type.SET);

        forgotPasswordIQ.setMobile(mobile);
        forgotPasswordIQ.setVcode(vcode);
        forgotPasswordIQ.setPassword(password);

        Log.d("qzf", "forgotPassword: "+forgotPasswordIQ.toXML());

        ActivityHolder.getInstance().sendPacket(forgotPasswordIQ);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void updateForResponse(ResultModelIQ resultModelIQ) {
        super.updateForResponse(resultModelIQ);
        if (resultModelIQ.getErrCode() == 0) {
            if ("sendmessage".equals(resultModelIQ.getAction())) {
                Message msg = new Message();
                msg.what = UPDATE_UI;
                msg.obj = "短信发送成功";
                handler.sendMessage(msg);
            } else {
                ForgotPasswordActivity.this.finish();
            }
        } else {
//            HandlerUtil.getInstance().showToast(resultModelIQ.getErrMsg());
            Message msg = new Message();
            msg.what = UPDATE_UI;
            msg.obj = resultModelIQ.getErrMsg();
            handler.sendMessage(msg);
        }
    }
}

package org.androidpn.demoapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.IQ.UpdatePasswordIQ;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.HandlerUtil;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UpdatePasswordActivity extends BaseActivity {

    @Bind(R.id.old_password)
    EditText opasswordEdit;
    @Bind(R.id.new_password)
    EditText npasswordEdit;
    @Bind(R.id.check_password)
    EditText cpasswordEdit;
    @Bind(R.id.btn_update_password)
    TextView updateBtn;

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
        setContentView(R.layout.activity_update_password);
        ButterKnife.bind(this);

        initData();
    }

    public void initData() {

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                udpatePassword();
            }
        });

    }

    private void udpatePassword() {
        String opassword = opasswordEdit.getText().toString();
        String npassword = npasswordEdit.getText().toString();
        String cpassword = cpasswordEdit.getText().toString();

        if (opassword == null || "".equals(opassword)) {
            Toast.makeText(UpdatePasswordActivity.this, "请输入初始密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (npassword == null || "".equals(npassword)) {
            Toast.makeText(UpdatePasswordActivity.this, "请输入新密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (cpassword == null || "".equals(cpassword)) {
            Toast.makeText(UpdatePasswordActivity.this, "请输入确认密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (!npassword.equals(cpassword)) {
            Toast.makeText(UpdatePasswordActivity.this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
            return;
        }

        UpdatePasswordIQ updatePasswordIQ = new UpdatePasswordIQ();
        updatePasswordIQ.setType(IQ.Type.SET);

        updatePasswordIQ.setUserName(UserInfoHolder.getInstance().getUserName());
        updatePasswordIQ.setOpassword(opassword);
        updatePasswordIQ.setNpassword(npassword);

        Log.d("qzf", "udpatePassword: "+updatePasswordIQ.toXML());

        ActivityHolder.getInstance().sendPacket(updatePasswordIQ);


    }

    @Override
    public void updateForResponse(ResultModelIQ resultModelIQ) {
        super.updateForResponse(resultModelIQ);

        if (resultModelIQ.getErrCode() == 0) {
            UpdatePasswordActivity.this.finish();
        } else {
//            Toast.makeText(UpdatePasswordActivity.this, resultModelIQ.getErrMsg(), Toast.LENGTH_LONG).show();
//            HandlerUtil.getInstance().showToast(resultModelIQ.getErrMsg());
            Message msg = new Message();
            msg.what = UPDATE_UI;
            msg.obj = resultModelIQ.getErrMsg();
            handler.sendMessage(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

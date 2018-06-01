package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.IQ.RegisterIQ;
import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.client.Constants;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.HandlerUtil;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

/**
 * ע��ģ��
 * */
public class RegistrationActivity extends BaseActivity {

	private ImageView mRegistration_back;
	private EditText mRegistration_name, mRegistration_username,
			mRegistration_password, mRegistration_password2, mRegistration_mobile, vcodeEdit;
	private TextView mRegistration_OK;

	private SharedPreferences sharedPrefs;

	private Button sendMessageButton;

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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_registration);
		initView();
	}

	private void initView() {
		mRegistration_back = (ImageView) findViewById(R.id.Registration_back);
		mRegistration_username = (EditText) findViewById(R.id.Registration_username);
		mRegistration_name = (EditText) findViewById(R.id.Registration_name);
		mRegistration_password = (EditText) findViewById(R.id.Registration_password);
		mRegistration_password2 = (EditText) findViewById(R.id.Registration_password2);
		mRegistration_mobile = (EditText) findViewById(R.id.Registration_mobile);

		mRegistration_OK = (TextView) findViewById(R.id.Registration_OK);
		MyOnClickLietener myonclick = new MyOnClickLietener();
		mRegistration_back.setOnClickListener(myonclick);
		mRegistration_OK.setOnClickListener(myonclick);

		vcodeEdit = (EditText) findViewById(R.id.edit_vcode);
		sendMessageButton = (Button) findViewById(R.id.btn_get_vcode);


		sendMessageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String mobile = mRegistration_mobile.getText().toString();
				String userName = UserInfoHolder.getInstance().getUserName();

				InquiryIQ inquiryIQ = new InquiryIQ();
				inquiryIQ.setType(IQ.Type.SET);

				inquiryIQ.setTarget("sendmessage");
				inquiryIQ.setTitle(mobile);
				inquiryIQ.setUserName(userName);

				Log.d("qzf", "getvcode"+inquiryIQ.toXML());

				ActivityHolder.getInstance().sendPacket(inquiryIQ);
			}
		});

		sharedPrefs = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,
				Context.MODE_PRIVATE);
	}

	private class MyOnClickLietener implements View.OnClickListener {
		public void onClick(View arg0) {
			int mID = arg0.getId();
			if (mID == R.id.Registration_back) {
				RegistrationActivity.this.finish();
			}
			if (mID == R.id.Registration_OK) {
//				Toast.makeText(RegistrationActivity.this, "ע�ᰴť������", 1).show();
				sendRegisterIQ();
			}
		}
	}

	private void sendRegisterIQ() {
		String userName = mRegistration_username.getText().toString();
		String password = mRegistration_password.getText().toString();
		String cpassword = mRegistration_password2.getText().toString();
		String name = mRegistration_name.getText().toString();
		String mobile = mRegistration_mobile.getText().toString();
		String vcode = vcodeEdit.getText().toString();

		String localName = sharedPrefs.getString(Constants.XMPP_USERNAME, "");
		String localPassword = sharedPrefs.getString(Constants.XMPP_PASSWORD, "");

		if(!password.equals(cpassword)) {
			Toast.makeText(RegistrationActivity.this, "两次密码输入不一致！", Toast.LENGTH_LONG).show();
		} else {
			RegisterIQ registerIQ = new RegisterIQ();

			registerIQ.setUserName(userName);
			registerIQ.setPassword(password);
			registerIQ.setName(name);
			registerIQ.setMobile(mobile);

			registerIQ.setLocalName(localName);
			registerIQ.setLocalPassword(localPassword);

			registerIQ.setVcode(vcode);

			registerIQ.setType(IQ.Type.SET);

			Log.d("Regsiter Packet", "sendRegisterIQ: "+registerIQ.toXML());

			ActivityHolder.getInstance().sendPacket(registerIQ);
//			UserInfoHolder.getInstance().setUserName(userName);
//			UserInfoHolder.getInstance().setPassword(password);

		}

	}

	@Override
	public void updateForResponse(ResultModelIQ resultModelIQ) {
		super.updateForResponse(resultModelIQ);


		if (resultModelIQ.getErrCode() == 0) {
			if ("registration".equals(resultModelIQ.getAction())) {
				RegistrationActivity.this.finish();
			}
		} else {
//			Toast.makeText(RegistrationActivity.this, resultModelIQ.getErrMsg(), Toast.LENGTH_LONG).show();
//			HandlerUtil.getInstance().showToast(resultModelIQ.getErrMsg());
			Message msg = new Message();
			msg.what = UPDATE_UI;
			msg.obj = resultModelIQ.getErrMsg();
			handler.sendMessage(msg);
		}
	}
}

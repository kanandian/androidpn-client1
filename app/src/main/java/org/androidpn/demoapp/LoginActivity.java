package org.androidpn.demoapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.LoginIQ;
import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.adapter.CommentAdapter;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.MessageFormatUtil;
import org.jivesoftware.smack.packet.IQ;

/**
 * ��¼ģ��
 * </BR> </BR> By����ɬ </BR> ��ϵ���ߣ�QQ 534429149
 * */

public class LoginActivity extends BaseActivity {

	private final static int UPDATE_UI = 0;

	private ImageView mLogin_back;
	private EditText mLogin_user, mLogin_password;
	private TextView mLogin_OK, mLogin_wangjimima, mLogin_zhuce;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if(msg.what == UPDATE_UI) {
				Object obj = msg.obj;
				if (obj instanceof ResultModelIQ) {
					ResultModelIQ resultModelIQ = (ResultModelIQ) obj;
					if (resultModelIQ.getErrCode() == 1) {
						AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this, R.style.AlertDialog);
						dialog.setTitle("警告");
						dialog.setMessage(resultModelIQ.getErrMsg());
						dialog.setCancelable(false);
						dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {

							}
						});
						dialog.show();
					} else if (resultModelIQ.getErrCode() == 0) {
						LoginActivity.this.finish();
					}
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		mLogin_back = (ImageView) findViewById(R.id.Login_back);
		mLogin_user = (EditText) findViewById(R.id.Login_user);
		mLogin_password = (EditText) findViewById(R.id.Login_password);
		mLogin_OK = (TextView) findViewById(R.id.Login_OK);
		mLogin_wangjimima = (TextView) findViewById(R.id.Login_wangjimima);
		mLogin_zhuce = (TextView) findViewById(R.id.Login_zhuce);
		MyOnClickLietener myonclick = new MyOnClickLietener();
		mLogin_back.setOnClickListener(myonclick);
		mLogin_OK.setOnClickListener(myonclick);
		mLogin_wangjimima.setOnClickListener(myonclick);
		mLogin_zhuce.setOnClickListener(myonclick);
	}

	private class MyOnClickLietener implements View.OnClickListener {
		public void onClick(View arg0) {
			int mID = arg0.getId();
			if (mID == R.id.Login_back) {
				LoginActivity.this.finish();
			}
			if (mID == R.id.Login_OK) {
				String userName = mLogin_user.getText().toString().trim();
				String password = mLogin_password.getText().toString().trim();

				sendLoginIQ(userName, password);
			}
			if (mID == R.id.Login_wangjimima) {
				Toast.makeText(LoginActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
			}
			if (mID == R.id.Login_zhuce) {
				Intent intent = new Intent(LoginActivity.this,
						RegistrationActivity.class);
				startActivity(intent);
			}
		}
	}

	public void sendLoginIQ(String userName, String password) {
		LoginIQ loginIQ = new LoginIQ();

		loginIQ.setUserName(userName);
		loginIQ.setPassword(password);

		loginIQ.setType(IQ.Type.GET);

		Log.d("qzf", "sendLoginIQ: "+loginIQ.toXML());

		ActivityHolder.getInstance().sendPacket(loginIQ);
	}


	@Override
	public void updateForResponse(ResultModelIQ resultModelIQ) {
		super.updateForResponse(resultModelIQ);

		Message msg = new Message();
		msg.what = UPDATE_UI;
		msg.obj = resultModelIQ;
		handler.sendMessage(msg);
	}
}

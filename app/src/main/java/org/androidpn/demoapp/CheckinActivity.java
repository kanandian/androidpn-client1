package org.androidpn.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.androidpn.adapter.SignAdapter;
import org.androidpn.info.SignInfo;
import org.androidpn.model.Model;
import org.androidpn.myview.MyScrollListView;
import org.androidpn.net.ThreadPoolUtils;
import org.androidpn.thread.HttpGetThread;
import org.androidpn.utils.MyJson;

import java.util.ArrayList;
import java.util.List;

/**
 * ǩ��ģ��
 * </BR> </BR> By����ɬ </BR> ��ϵ���ߣ�QQ 534429149
 * */
public class CheckinActivity extends BaseActivity {

	private LinearLayout mCheckin_search;
	private LinearLayout mLayout;
	private ListView left;
	private ListView right;
	private MyScrollListView mScroll;
	private SignAdapter signAdapter1 = null;
	private SignAdapter signAdapter2 = null;
	private List<SignInfo> leftlist = new ArrayList<SignInfo>();
	private List<SignInfo> rightlist = new ArrayList<SignInfo>();
	private List<SignInfo> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
		initView();
	}

	private void initView() {
		mCheckin_search = (LinearLayout) findViewById(R.id.Checkin_search);
		MyOnclickListener mOnclickListener = new MyOnclickListener();
		mCheckin_search.setOnClickListener(mOnclickListener);
		mLayout = (LinearLayout) findViewById(R.id.Cheickin_Group);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		lp.weight = 1;
		left = new ListView(CheckinActivity.this);
		right = new ListView(CheckinActivity.this);
		left.setLayoutParams(lp);
		right.setLayoutParams(lp);
		signAdapter1 = new SignAdapter(leftlist, CheckinActivity.this);
		signAdapter2 = new SignAdapter(rightlist, CheckinActivity.this);
		left.setAdapter(signAdapter1);
		right.setAdapter(signAdapter2);
		ThreadPoolUtils.execute(new HttpGetThread(hand, Model.SELECTSIGNURL));
	}

	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
		}

	}

	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);

			if (msg.what == 200) {
				String value = (String) msg.obj;
				if(value != null){
					
					list = new MyJson().getSignList(value);
					for (int i = 0; i < list.size(); i++) {
						if (i % 2 == 0) {
							leftlist.add(list.get(i));
							continue;
						} else {
							rightlist.add(list.get(i));
							continue;
						}
					}
				}
				signAdapter1.notifyDataSetChanged();
				signAdapter2.notifyDataSetChanged();
				mScroll = new MyScrollListView(CheckinActivity.this, left,
						right);
				mLayout.addView(mScroll);
			}
		};
	};
}

package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.adapter.ShopAdapter;
import org.androidpn.info.ShopInfo;
import org.androidpn.model.Model;
import org.androidpn.net.ThreadPoolUtils;
import org.androidpn.utils.MyJson;

import java.util.ArrayList;
import java.util.List;

/**
 * �Ź�ģ��
 * */
public class TuanActivity extends BaseActivity {

	// ������linearlayout��Ϊ��ť
	private LinearLayout mTuan_search;
	private TextView mTuan_mytuan_txt;
	private ImageView mTuan_button1;
	private int listOrGridFlag = 0;
	private TextView mTuan_title_textbtn1, mTuan_title_textbtn2,
			mTuan_title_textbtn3;

	private ListView mListView;
	private MyJson myJson = new MyJson();
	private List<ShopInfo> list = new ArrayList<ShopInfo>();
	private ShopAdapter mAdapter = null;
	private Button ListBottem = null;
	private int mStart = 1;
	private int mEnd = 5;
	private String url = null;
	private boolean flag = true;
	private boolean listBottemFlag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tuan);
		initView();
	}

	private void initView() {
		mTuan_search = (LinearLayout) findViewById(R.id.Tuan_search);
		mTuan_mytuan_txt = (TextView) findViewById(R.id.Tuan_mytuan_txt);
		mTuan_button1 = (ImageView) findViewById(R.id.Tuan_button1);
		mTuan_title_textbtn1 = (TextView) findViewById(R.id.Tuan_title_textbtn1);
		mTuan_title_textbtn2 = (TextView) findViewById(R.id.Tuan_title_textbtn2);
		mTuan_title_textbtn3 = (TextView) findViewById(R.id.Tuan_title_textbtn3);
		mListView = (ListView) findViewById(R.id.ShopListView);
		MyOnClickListener myclicklistener = new MyOnClickListener();
		mTuan_search.setOnClickListener(myclicklistener);
		mTuan_mytuan_txt.setOnClickListener(myclicklistener);
		mTuan_button1.setOnClickListener(myclicklistener);
		mTuan_title_textbtn1.setOnClickListener(myclicklistener);
		mTuan_title_textbtn2.setOnClickListener(myclicklistener);
		mTuan_title_textbtn3.setOnClickListener(myclicklistener);
		
		//--------------------
		mAdapter = new ShopAdapter(list, TuanActivity.this);
		ListBottem = new Button(TuanActivity.this);
		mListView.addFooterView(ListBottem, null, false);
		ListBottem.setVisibility(View.GONE);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new MainListOnItemClickListener());
		// ƴ���ַ�������
		url = Model.SHOPURL + "start=" + mStart + "&end=" + mEnd;

	}

	private class MyOnClickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			if (mID == R.id.Tuan_button1) {
				if (listOrGridFlag == 0) {
					mTuan_button1
							.setImageResource(R.drawable.ic_action_list_mode);
					listOrGridFlag = 1;
				} else if (listOrGridFlag == 1) {
					mTuan_button1
							.setImageResource(R.drawable.ic_action_image_mode);
					listOrGridFlag = 0;
				}
			}
		}

	}
	

	private class MainListOnItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
			Intent intent = new Intent(TuanActivity.this, TuanDetailsActivity.class);
			Bundle bund = new Bundle();
			bund.putSerializable("ShopInfo",list.get(arg2));
			intent.putExtra("value",bund);
			startActivity(intent);
		}
	}

}

package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.androidpn.utils.ActivityHolder;

/**
 * ����ģ��
 * */

public class SearchActivity extends BaseActivity {

	// ������linearlayout��Ϊ��ť
	private LinearLayout mSearch_city, mSearch_search;
	// gridview��ʽlinearlayout��Ϊ��ť
	private LinearLayout mSearch_food, mSearch_outing, mSearch_hotel,
			mSearch_pub, mSearch_more, mSearch_chinsesnack;
	// listview��ʽlinearlayout��Ϊ��ť
	private LinearLayout mSearch_list_huiyuanka, mSearch_list_souquancheng,
			mSearch_list_paihangbang, mSearch_list_youhuiquan;
	// ����ʶ��ť
	private ImageView mSearch_button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initView();
	}

	private void initView() {
		// ������linearlayout��Ϊ��ť
		mSearch_city = (LinearLayout) findViewById(R.id.Search_city);
		mSearch_search = (LinearLayout) findViewById(R.id.Search_search);
		mSearch_button1 = (ImageView) findViewById(R.id.Search_button1);
		// gridview��ʽlinearlayout��Ϊ��ť
		mSearch_food = (LinearLayout) findViewById(R.id.Search_food);
		mSearch_outing = (LinearLayout) findViewById(R.id.Search_outing);
		mSearch_hotel = (LinearLayout) findViewById(R.id.Search_hotel);
		mSearch_pub = (LinearLayout) findViewById(R.id.Search_pub);
		mSearch_more = (LinearLayout) findViewById(R.id.Search_more);
		mSearch_chinsesnack = (LinearLayout) findViewById(R.id.Search_chinsesnack);
		// listview��ʽlinearlayout��Ϊ��ť
		mSearch_list_huiyuanka = (LinearLayout) findViewById(R.id.Search_list_huiyuanka);
		mSearch_list_souquancheng = (LinearLayout) findViewById(R.id.Search_list_souquancheng);
		mSearch_list_paihangbang = (LinearLayout) findViewById(R.id.Search_list_paihangbang);
		mSearch_list_youhuiquan = (LinearLayout) findViewById(R.id.Search_list_youhuiquan);
		MyOnclickListener mOnclickListener = new MyOnclickListener();
		mSearch_city.setOnClickListener(mOnclickListener);
		mSearch_search.setOnClickListener(mOnclickListener);
		mSearch_button1.setOnClickListener(mOnclickListener);
		mSearch_food.setOnClickListener(mOnclickListener);
		mSearch_outing.setOnClickListener(mOnclickListener);
		mSearch_hotel.setOnClickListener(mOnclickListener);
		mSearch_pub.setOnClickListener(mOnclickListener);
		mSearch_more.setOnClickListener(mOnclickListener);
		mSearch_chinsesnack.setOnClickListener(mOnclickListener);
		mSearch_list_huiyuanka.setOnClickListener(mOnclickListener);
		mSearch_list_souquancheng.setOnClickListener(mOnclickListener);
		mSearch_list_paihangbang.setOnClickListener(mOnclickListener);
		mSearch_list_youhuiquan.setOnClickListener(mOnclickListener);

	}

	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			switch (mID) {
			case R.id.Search_food:
				Intent intent = new Intent(SearchActivity.this,
						ShopListActivity.class);
				intent.putExtra("title", "美食");
				SearchActivity.this.startActivity(intent);
				break;
			case R.id.Search_outing:
				Intent intent1 = new Intent(SearchActivity.this,
						ShopListActivity.class);
				intent1.putExtra("title", "景点");
				SearchActivity.this.startActivity(intent1);
				break;
			case R.id.Search_hotel:
				Intent intent2 = new Intent(SearchActivity.this,
						ShopListActivity.class);
				intent2.putExtra("title", "酒店");
				SearchActivity.this.startActivity(intent2);
				break;
			case R.id.Search_pub:
				Intent intent3 = new Intent(SearchActivity.this,
						ShopListActivity.class);
				intent3.putExtra("title", "酒吧");
				SearchActivity.this.startActivity(intent3);
				break;
			case R.id.Search_chinsesnack:
				Intent intent4 = new Intent(SearchActivity.this,
						ShopListActivity.class);
				intent4.putExtra("title", "小吃");
				SearchActivity.this.startActivity(intent4);
				break;
			case R.id.Search_more:
				Intent intent5 = new Intent(SearchActivity.this,
						SearchMoreActivity.class);
				SearchActivity.this.startActivity(intent5);
				break;
			case R.id.Search_list_souquancheng:
				Intent intent6 = new Intent(SearchActivity.this,
						SearchTheCity.class);
				SearchActivity.this.startActivity(intent6);
				break;
			case R.id.Search_list_paihangbang:
				Intent intent7 = new Intent(SearchActivity.this,
						RankingList.class);
				SearchActivity.this.startActivity(intent7);
				break;
//			case R.id.Search_list_huiyuanka:
//				Intent intent8 = new Intent(SearchActivity.this,
//						TuanDetailsActivity.class);
//				SearchActivity.this.startActivity(intent8);
//				break;
			}
		}

	}
}

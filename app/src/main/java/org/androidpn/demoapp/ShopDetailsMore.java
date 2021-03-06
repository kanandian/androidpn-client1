package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidpn.info.ShopInfo;

/**
 * ��������-������Ϣģ��
 * */
public class ShopDetailsMore extends BaseActivity {

	private ShopInfo info = null;
	private TextView mShop_details_more_title;
	private ImageView mShoplist_back;

	private TextView bussinessHourText;
	private TextView bussinessFeaturesText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shop_details_more);
		Intent intent = getIntent();
		Bundle bund = intent.getBundleExtra("value");
		info = (ShopInfo) bund.getSerializable("ShopInfo");
		initView();
		initModel();
	}

	private void initView() {
//		mShop_details_more_time = (TextView) findViewById(R.id.Shop_details_more_time);
		mShop_details_more_title = (TextView) findViewById(R.id.Shop_details_more_title);
		mShoplist_back = (ImageView) findViewById(R.id.Shoplist_back);
		mShoplist_back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ShopDetailsMore.this.finish();
			}
		});

		bussinessHourText = (TextView) findViewById(R.id.bussiness_hour);
		bussinessFeaturesText = (TextView) findViewById(R.id.bussiness_features);

	}

	private void initModel() {
		mShop_details_more_title.setText(info.getSname());
//		mShop_details_more_time.setText(info.getStime());

		bussinessHourText.setText(info.getStartTime()+"-"+info.getEndTime());
		bussinessFeaturesText.setText(info.getFeature());
	}
}

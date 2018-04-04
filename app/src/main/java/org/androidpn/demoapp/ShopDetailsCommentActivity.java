package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.info.ShopInfo;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

/**
 * ��������-����ģ��
 * */
public class ShopDetailsCommentActivity extends BaseActivity {

	private ShopInfo info = null;
	private TextView mShop_details_more_title;
	private ImageView mShoplist_back;
	private RatingBar mshop_dianping_ratingbar;
	private EditText mshop_dianping_edittext1, mshop_dianping_edittext2;
	private TextView mshop_dianping_text1, mshop_dianping_OK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shop_detailsdianping);
		Intent intent = getIntent();
		Bundle bund = intent.getBundleExtra("value");
		info = (ShopInfo) bund.getSerializable("ShopInfo");
		initView();
		initModel();
	}

	private void initView() {
		mShop_details_more_title = (TextView) findViewById(R.id.Shop_details_more_title);
		mShoplist_back = (ImageView) findViewById(R.id.Shoplist_back);
		mShoplist_back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ShopDetailsCommentActivity.this.finish();
			}
		});

		mshop_dianping_ratingbar = (RatingBar) findViewById(R.id.shop_dianping_ratingbar);
		mshop_dianping_edittext1 = (EditText) findViewById(R.id.shop_dianping_edittext1);
		mshop_dianping_edittext2 = (EditText) findViewById(R.id.shop_dianping_edittext2);
		mshop_dianping_text1 = (TextView) findViewById(R.id.shop_dianping_text1);
		mshop_dianping_OK = (TextView) findViewById(R.id.shop_dianping_OK);
		mshop_dianping_OK.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Toast.makeText(ShopDetailsCommentActivity.this, "评论已添加", Toast.LENGTH_SHORT).show();

				sendCommentIQ();
			}
		});
		mshop_dianping_ratingbar.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					event.setAction(MotionEvent.ACTION_MOVE);
				}
				return false;
			}
		});
	}

	private void sendCommentIQ() {
		String bussinessId = info.getSid();
		String commentContent = mshop_dianping_edittext1.getText().toString();

		InquiryIQ commentIQ = new InquiryIQ();
		commentIQ.setTarget("comment");
		commentIQ.setTitle(bussinessId);
		commentIQ.setContent(commentContent);
		commentIQ.setUserName(UserInfoHolder.getInstance().getUserName());
		commentIQ.setType(IQ.Type.SET);

		Log.d("qzf's log", "sendInquiryIQ: "+commentIQ.toXML());

		ActivityHolder.getInstance().sendPacket(commentIQ);
	}

	private void initModel() {
		mShop_details_more_title.setText(info.getSname());
	}
}

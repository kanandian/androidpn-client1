package org.androidpn.demoapp;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.info.CommentsInfo;
import org.androidpn.info.FoodInfo;
import org.androidpn.info.ShopInfo;
import org.androidpn.info.SignInfo;
import org.androidpn.model.Model;
import org.androidpn.net.ThreadPoolUtils;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.Location;
import org.androidpn.utils.MyJson;
import org.androidpn.utils.NetworkImageUtil;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.ArrayList;

/**
 * ��������ģ��
 * */
public class ShopDetailsActivity extends BaseActivity {

	private ShopInfo info = null;
//	private LoadImg loadImg;
//	private HttpGetThread http = null;
	private MyJson myJson = new MyJson();
	private ArrayList<SignInfo> SignList;
	private ArrayList<CommentsInfo> CommentsList;
	private ArrayList<FoodInfo> FoodList;
	// top�͵��̵�����
	private ImageView mShop_details_back, mShop_details_share,
			mShop_details_off, mShop_details_star;

	private NetworkImageView mShop_details_photo;
	private TextView mShop_details_name, mShop_details_money;
	// ������ĵ���ʽ��ť
	private LinearLayout mShop_details_bottom_img1, mShop_details_bottom_img2,
			mShop_details_bottom_img3, mShop_details_bottom_img4, mShop_details_bottom_img5;
	// �����������������
	private RelativeLayout mshop_details_address, mshop_details_phone,
			mshop_details_ding, mshop_details_card, mshop_details_quan,
			mshop_details_tuan;
	private TextView mshop_details_address_txt, mshop_details_phone_txt,
			mshop_details_card_txt, mshop_details_quan_txt,
			mshop_details_tuan_txt;
	private ImageView mshop_details_ding_hui, mshop_details_ding_jiang;
	// �����Ƽ���layout
	private RelativeLayout mshop_details_tuijian;
	private TextView mshop_details_tuijian_txt;
	// ������layout
	private RelativeLayout mshop_details_dianping;
	private TextView mshop_dianping_top, mshop_details_dianping_name,
			mshop_details_dianping_txt, mshop_details_dianping_time;
	private ImageView mshop_details_dianping_star;
	// ǩ�����Ե�layout
	private RelativeLayout mshop_details_qiandaoqiang;
	private TextView mshop_qiandaoqiang_top, mshop_details_qiandaoqiang_txt,
			mshop_details_qiandaoqiang_time, mshop_details_qiandaoqiang_cishu;
	private ImageView mshop_details_qiandaoqiang_img;
	// ������Ϣ��layout
	private RelativeLayout mshop_details_qita;
	// ��ҵ긽����layout
	private TextView mshop_fujin_meishi, mshop_fujin_jingdian,
			mshop_fujin_jiudian, mshop_fujin_quanbu;
	// �����ֵ��layout
	private RelativeLayout mshop_details_fendian;
	private TextView mshop_details_fendians_txt;
	// ������ҵ��layout
	private RelativeLayout mshop_details_kanguo;
	// ����popupWindow
	private View parent;
	private PopupWindow popupWindow;

	private boolean isCollected = false;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if(msg.what == UPDATE_UI) {
				if (isCollected) {
					mShop_details_off.setImageResource(R.drawable.star_1_pressed);
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shop_details);
		// ��ʼ��ͼƬ�첽������
//		loadImg = new LoadImg(ShopDetailsActivity.this);
		// ��ȡ���б��д��ݹ���������
		Intent intent = getIntent();
		Bundle bund = intent.getBundleExtra("value");
		info = (ShopInfo) bund.getSerializable("ShopInfo");
		initData();
		initView();
		// ������������
		String endParames = Model.SHOPDETAILURL + "shopid=" + info.getSid();
//		http = new HttpGetThread(hand, endParames);
//		ThreadPoolUtils.execute(http);
	}

	public void initData() {

	}

	private void initView() {
		MyOnClickListener myOnClickListener = new MyOnClickListener();
		// ����ؼ�
		mShop_details_back = (ImageView) findViewById(R.id.Shop_details_back);
		mShop_details_share = (ImageView) findViewById(R.id.Shop_details_share);
		mShop_details_off = (ImageView) findViewById(R.id.Shop_details_off);
		// ������ĵ���ʽ��ť
		mShop_details_bottom_img1 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img1);
		mShop_details_bottom_img2 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img2);
		mShop_details_bottom_img3 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img3);
		mShop_details_bottom_img4 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img4);
		mShop_details_bottom_img5 = (LinearLayout) findViewById(R.id.Shop_details_bottom_img5);
		// ������Ϣ�ؼ�
		mShop_details_name = (TextView) findViewById(R.id.Shop_details_name);
		mShop_details_photo = (NetworkImageView) findViewById(R.id.Shop_details_photo);
		mShop_details_star = (ImageView) findViewById(R.id.Shop_details_star);
		mShop_details_money = (TextView) findViewById(R.id.Shop_details_money);
		// ������Ϣ����ĵ�ַ���绰����������ȯ���ſؼ�
		mshop_details_address = (RelativeLayout) findViewById(R.id.shop_details_address);
		mshop_details_phone = (RelativeLayout) findViewById(R.id.shop_details_phone);
		mshop_details_ding = (RelativeLayout) findViewById(R.id.shop_details_ding);
		mshop_details_card = (RelativeLayout) findViewById(R.id.shop_details_card);
		mshop_details_quan = (RelativeLayout) findViewById(R.id.shop_details_quan);
		mshop_details_tuan = (RelativeLayout) findViewById(R.id.shop_details_tuan);
		mshop_details_address_txt = (TextView) findViewById(R.id.shop_details_address_txt);
		mshop_details_phone_txt = (TextView) findViewById(R.id.shop_details_phone_txt);
		mshop_details_card_txt = (TextView) findViewById(R.id.shop_details_card_txt);
		mshop_details_quan_txt = (TextView) findViewById(R.id.shop_details_quan_txt);
		mshop_details_tuan_txt = (TextView) findViewById(R.id.shop_details_tuan_txt);
		mshop_details_ding_hui = (ImageView) findViewById(R.id.shop_details_ding_hui);
		mshop_details_ding_jiang = (ImageView) findViewById(R.id.shop_details_ding_jiang);
		// �����Ƽ���Ϣ�Ŀؼ�����
		mshop_details_tuijian = (RelativeLayout) findViewById(R.id.shop_details_tuijian);
		mshop_details_tuijian_txt = (TextView) findViewById(R.id.shop_details_tuijian_txt);
		// ����
		mshop_details_dianping = (RelativeLayout) findViewById(R.id.shop_details_dianping);
		mshop_dianping_top = (TextView) findViewById(R.id.shop_dianping_top);
		mshop_details_dianping_name = (TextView) findViewById(R.id.shop_details_dianping_name);
		mshop_details_dianping_star = (ImageView) findViewById(R.id.shop_details_dianping_star);
		mshop_details_dianping_txt = (TextView) findViewById(R.id.shop_details_dianping_txt);
		mshop_details_dianping_time = (TextView) findViewById(R.id.shop_details_dianping_time);
		// ǩ������
		mshop_details_qiandaoqiang = (RelativeLayout) findViewById(R.id.shop_details_qiandaoqiang);
		mshop_qiandaoqiang_top = (TextView) findViewById(R.id.shop_qiandaoqiang_top);
		mshop_details_qiandaoqiang_txt = (TextView) findViewById(R.id.shop_details_qiandaoqiang_txt);
		mshop_details_qiandaoqiang_time = (TextView) findViewById(R.id.shop_details_qiandaoqiang_time);
		mshop_details_qiandaoqiang_cishu = (TextView) findViewById(R.id.shop_details_qiandaoqiang_cishu);
		mshop_details_qiandaoqiang_img = (ImageView) findViewById(R.id.shop_details_qiandaoqiang_img);
		// ������Ϣ
		mshop_details_qita = (RelativeLayout) findViewById(R.id.shop_details_qita);
		// ����ҵ긽��
		mshop_fujin_meishi = (TextView) findViewById(R.id.shop_fujin_meishi);
		mshop_fujin_jingdian = (TextView) findViewById(R.id.shop_fujin_jingdian);
		mshop_fujin_jiudian = (TextView) findViewById(R.id.shop_fujin_jiudian);
		mshop_fujin_quanbu = (TextView) findViewById(R.id.shop_fujin_quanbu);
		// �鿴�����ֵ�
		mshop_details_fendian = (RelativeLayout) findViewById(R.id.shop_details_fendian);
		mshop_details_fendians_txt = (TextView) findViewById(R.id.shop_details_fendians_txt);
		// ������ҵ���˻�����
		mshop_details_kanguo = (RelativeLayout) findViewById(R.id.shop_details_kanguo);

		// ���ؼ����ü���
		mShop_details_back.setOnClickListener(myOnClickListener);
		mShop_details_share.setOnClickListener(myOnClickListener);
		mShop_details_off.setOnClickListener(myOnClickListener);
		mShop_details_bottom_img1.setOnClickListener(myOnClickListener);
		mShop_details_bottom_img2.setOnClickListener(myOnClickListener);
		mShop_details_bottom_img3.setOnClickListener(myOnClickListener);
		mShop_details_bottom_img4.setOnClickListener(myOnClickListener);
		mShop_details_photo.setOnClickListener(myOnClickListener);
		mshop_details_address.setOnClickListener(myOnClickListener);
		mshop_details_phone.setOnClickListener(myOnClickListener);
		mshop_details_ding.setOnClickListener(myOnClickListener);
		mshop_details_card.setOnClickListener(myOnClickListener);
		mshop_details_quan.setOnClickListener(myOnClickListener);
		mshop_details_tuan.setOnClickListener(myOnClickListener);
		mshop_details_tuijian.setOnClickListener(myOnClickListener);
		mshop_details_dianping.setOnClickListener(myOnClickListener);
		mshop_details_qiandaoqiang.setOnClickListener(myOnClickListener);
		mshop_details_qita.setOnClickListener(myOnClickListener);
		mshop_fujin_meishi.setOnClickListener(myOnClickListener);
		mshop_fujin_jingdian.setOnClickListener(myOnClickListener);
		mshop_fujin_jiudian.setOnClickListener(myOnClickListener);
		mshop_fujin_quanbu.setOnClickListener(myOnClickListener);
		mshop_details_fendian.setOnClickListener(myOnClickListener);
		mshop_details_kanguo.setOnClickListener(myOnClickListener);


		mshop_details_address.setOnClickListener(myOnClickListener);

		if ("外卖".equals(info.getStype())) {
			mShop_details_bottom_img4.setVisibility(View.GONE);
			mShop_details_bottom_img5.setVisibility(View.VISIBLE);

			mShop_details_bottom_img5.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(ShopDetailsActivity.this, TakeOutActivity.class);
					intent.putExtra("shopInfo", info);
					startActivity(intent);
				}
			});
		}


		// ������̵�ͼƬ�ķ���
		addImg();
		// �ж��Ƿ�Ҫ��ʾ������ȯ�����Ŀؼ�����
		xianshitqdk();
	}

	@Override
	public void sendInquiryIQ() {
		super.sendInquiryIQ();

		InquiryIQ inquiryIQ = new InquiryIQ();
		inquiryIQ.setType(IQ.Type.GET);

		inquiryIQ.setTarget("bussiness");
		inquiryIQ.setTitle(info.getSid());
		inquiryIQ.setUserName(UserInfoHolder.getInstance().getUserName());

		Log.d("qzf", "sendInquiryIQ: "+inquiryIQ.toXML());

		ActivityHolder.getInstance().sendPacket(inquiryIQ);


	}

	@Override
	public void updateForResponse(ResultModelIQ resultModelIQ) {
		super.updateForResponse(resultModelIQ);

		if (resultModelIQ.getErrCode() == 2) {
			Message msg = new Message();
			msg.what = UPDATE_UI;
			this.isCollected = true;
			handler.sendMessage(msg);
		}
	}

	// �ؼ��ļ����¼�
	private class MyOnClickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();

			if (mID == R.id.shop_details_address) {
				Location location = new Location();

				location.setAddress(info.getSaddress());
				location.setLongitude(info.getLongitude());
				location.setLatitude(info.getLatitude());

				ActivityHolder.getInstance().startNaviActivity(location);
			}

			if (mID == R.id.shop_details_phone) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:"+info.getStel()));

				startActivity(intent);
			}

			if (mID == R.id.Shop_details_bottom_img2) {
				if (!UserInfoHolder.getInstance().isAuth()) {
					Toast.makeText(ShopDetailsActivity.this, "用户未登录，请先登录" ,Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(ShopDetailsActivity.this, PaymentActivity.class);
					intent.putExtra("toUserName", info.getSholder());
					intent.putExtra("bussinessId", info.getSid());
					intent.putExtra("bussinessName", info.getSname());
					startActivity(intent);
				}
			}

			if (mID == R.id.Shop_details_off) {
				if (!UserInfoHolder.getInstance().isAuth()) {
					Toast.makeText(ShopDetailsActivity.this, "用户未登录，请先登录", Toast.LENGTH_SHORT).show();
					return;
				}
				InquiryIQ inquiryIQ = new InquiryIQ();
				inquiryIQ.setType(IQ.Type.SET);

				inquiryIQ.setTarget("collection");
				inquiryIQ.setTitle(UserInfoHolder.getInstance().getUserName());
				inquiryIQ.setContent(info.getSid());


				if (isCollected) {
					mShop_details_off.setImageResource(R.drawable.ic_action_favorite_off);
					isCollected = false;
					inquiryIQ.setStatus(0);
				} else {
					mShop_details_off.setImageResource(R.drawable.star_1_pressed);
					isCollected = true;
					inquiryIQ.setStatus(1);
				}

				Log.d("collection ", "onClick: "+inquiryIQ.toXML());

				ActivityHolder.getInstance().sendPacket(inquiryIQ);
			}

			if (mID == R.id.Shop_details_back) {
				ShopDetailsActivity.this.finish();
			}
			if (mID == R.id.shop_details_qita) {
				Intent intent = new Intent(ShopDetailsActivity.this,
						ShopDetailsMore.class);
				Bundle bund = new Bundle();
				bund.putSerializable("ShopInfo", info);
				intent.putExtra("value", bund);
				startActivity(intent);
			}
			if (mID == R.id.Shop_details_bottom_img1) {
//				Intent intent = new Intent(ShopDetailsActivity.this,
//						ShopDetailsCheckinActivity.class);
//				Bundle bund = new Bundle();
//				bund.putSerializable("ShopInfo", info);
//				intent.putExtra("value", bund);
//				startActivity(intent);
				Intent intent = new Intent(ShopDetailsActivity.this, ShopCommentsActivity.class);
				intent.putExtra("bussinessId", info.getSid());

				startActivity(intent);

			}
			if (mID == R.id.Shop_details_bottom_img2) {
				
			}
			if (mID == R.id.Shop_details_bottom_img3) {
				Intent intent = new Intent(ShopDetailsActivity.this,
						ShopDetailsCommentActivity.class);
				Bundle bund = new Bundle();
				bund.putSerializable("ShopInfo", info);
				intent.putExtra("value", bund);
				startActivity(intent);
			}
			if (mID == R.id.Shop_details_bottom_img4) {
//				creatPopupWindow();
				Intent intent = new Intent(ShopDetailsActivity.this, ChatActivity.class);
				startActivity(intent);
			}
			if(mID == R.id.shop_details_tuan){
				Intent intent = new Intent(ShopDetailsActivity.this,
						TuanDetailsActivity.class);
				Bundle bund = new Bundle();
				bund.putSerializable("ShopInfo", info);
				intent.putExtra("value", bund);
				startActivity(intent);
			}

		}
	}

	private void creatPopupWindow() {
		Builder builder = new Builder(ShopDetailsActivity.this);
		builder.setTitle("��������");
		final String[] items = new String[] { "�̻��ѹر�", "��ͼλ�ô���", "�̻���Ϣ����",
				"�̻��ظ�", "����" };
		DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == DialogInterface.BUTTON_POSITIVE) {
					arg0.cancel();
				}
//				switch (arg1) {
//				case 0:
//					Toast.makeText(ShopDetailsActivity.this,
//							"������Ϣ0:" + items[arg1], 1).show();
//					break;
//				case 1:
//					Toast.makeText(ShopDetailsActivity.this,
//							"������Ϣ1:" + items[arg1], 1).show();
//					break;
//				case 2:
//					Toast.makeText(ShopDetailsActivity.this,
//							"������Ϣ2:" + items[arg1], 1).show();
//					break;
//				case 3:
//					Toast.makeText(ShopDetailsActivity.this,
//							"������Ϣ3:" + items[arg1], 1).show();
//					break;
//				case 4:
//					Toast.makeText(ShopDetailsActivity.this,
//							"������Ϣ4:" + items[arg1], 1).show();
//					break;
//				}
			}
		};
		builder.setItems(items, dialog);
		builder.setPositiveButton("ȡ��", dialog);
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	// ���ͼƬ����
	private void addImg() {
		NetworkImageUtil.requestImage(mShop_details_photo, info.getIname());
	}

	// �̷߳�����Ϣ
	Handler hand = new Handler() {

		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
//				Toast.makeText(ShopDetailsActivity.this, "�Ҳ�����ַ", 1).show();
			} else if (msg.what == 100) {
//				Toast.makeText(ShopDetailsActivity.this, "����ʧ��", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				Log.e("result", "result:" + result);
				if (result != null) {
					// ��������
					myJson.getShopDetail(result, new MyJson.DetailCallBack() {

						@Override
						public void getList(
								ArrayList<SignInfo> SignList,
								ArrayList<org.androidpn.info.CommentsInfo> CommentsList,
								ArrayList<FoodInfo> FoodList) {
							// ��ȡ�����ص�����
							ShopDetailsActivity.this.SignList = SignList;
							ShopDetailsActivity.this.CommentsList = CommentsList;
							ShopDetailsActivity.this.FoodList = FoodList;
							// ��ʾ����
							if (ShopDetailsActivity.this.SignList.size() > 0) {
								mshop_details_qiandaoqiang
										.setVisibility(View.VISIBLE);
								SignInfo signinfo = SignList.get(SignList
										.size() - 1);
								mshop_qiandaoqiang_top.setText("ǩ������ǽ(��"
										+ SignList.size() + "�ˣ�");
								mshop_details_qiandaoqiang_txt.setText(signinfo
										.getName());
								mshop_details_qiandaoqiang_time
										.setText(signinfo.getSigntime());
								mshop_details_qiandaoqiang_cishu.setText("��ǩ��"
										+ signinfo.getSignlevel() + "��");
							}
							if (ShopDetailsActivity.this.CommentsList.size() > 0) {
								mshop_details_dianping
										.setVisibility(View.VISIBLE);
								CommentsInfo commentsinfo = CommentsList
										.get(CommentsList.size() - 1);
								mshop_dianping_top.setText("����(��"
										+ CommentsList.size() + ")����");
								mshop_details_dianping_name
										.setText(commentsinfo.getName());
								mshop_details_dianping_txt.setText(commentsinfo
										.getComments());
								mshop_details_dianping_time
										.setText(commentsinfo.getTime());
								int slevel = Integer.valueOf(commentsinfo
										.getClevel());
								switch (slevel) {
								case 0:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star0);
									break;
								case 1:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star1);
									break;
								case 2:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star2);
									break;
								case 3:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star3);
									break;
								case 4:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star4);
									break;
								case 5:
									mshop_details_dianping_star
											.setImageResource(R.drawable.star5);
									break;
								}
							}
							if (ShopDetailsActivity.this.FoodList.size() > 0) {
								mshop_details_tuijian
										.setVisibility(View.VISIBLE);
								String foodtext = "";// �����Ƽ�ʳ��
								for (int i = 0; i < FoodList.size(); i++) {
									foodtext += FoodList.get(i).getFoodname()
											+ "  ";
								}
								mshop_details_tuijian_txt.setText(foodtext);
							}
							// ��������Ƽ���ʳ��

							// ��ӵ�����Ϣ

						}
					});
				}
			}
		};

	};

	public ShopInfo getInfo() {
		return info;
	}

	public void setInfo(ShopInfo info) {
		this.info = info;
	}

	// ֪��֧����ȯ����Ȼ���ж��Ƿ���ʾ�Լ���ʵ�޸�Ҫ��ʾ������
	private void xianshitqdk() {
		mShop_details_name.setText(info.getSname());
		mShop_details_money.setText(info.getSmoney());
		mshop_details_address_txt.setText(info.getSaddress());
		mshop_details_phone_txt.setText(info.getStel());
		mShop_details_star.setImageResource(info.getStarImageResource());

//		int slevel = Integer.valueOf(info.getSlevel());
//		switch (slevel) {
//		case 0:
//			mShop_details_star.setImageResource(R.drawable.star0);
//			break;
//		case 1:
//			mShop_details_star.setImageResource(R.drawable.star1);
//			break;
//		case 2:
//			mShop_details_star.setImageResource(R.drawable.star2);
//			break;
//		case 3:
//			mShop_details_star.setImageResource(R.drawable.star3);
//			break;
//		case 4:
//			mShop_details_star.setImageResource(R.drawable.star4);
//			break;
//		case 5:
//			mShop_details_star.setImageResource(R.drawable.star5);
//			break;
//		}

//		if (info.getSflag_tuan().equals("1")) {
//			mshop_details_tuan.setVisibility(View.VISIBLE);
//			mshop_details_tuan_txt.setText("����255Ԫ��16�Ŵ���ȯ1��");
//		}
//		if (info.getSflag_quan().equals("1")) {
//			mshop_details_quan.setVisibility(View.VISIBLE);
//			mshop_details_quan_txt.setText("�ĺ�Ժ��ʳ�����500�����м�...");
//		}
//		if (info.getSflag_ding().equals("1")) {
//			mshop_details_ding.setVisibility(View.VISIBLE);
//			mshop_details_ding_hui.setVisibility(View.VISIBLE);
//			mshop_details_ding_jiang.setVisibility(View.VISIBLE);
//		}
//		if (info.getSflag_ka().equals("1")) {
//			mshop_details_card.setVisibility(View.VISIBLE);
//			mshop_details_card_txt.setText("��Աר��9.5�ۣ��������");
//		}
	}

}

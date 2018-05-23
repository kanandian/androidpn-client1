package org.androidpn.demoapp;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.IQ.LoginIQ;
import org.androidpn.enity.MessageInfo;
import org.androidpn.entity.ChatMessage;
import org.androidpn.model.Contact;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.Constants;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * �����ܽ���
 * </BR> </BR> By����ɬ </BR> ��ϵ���ߣ�QQ 534429149
 * */
public class FrameActivity extends ActivityGroup {

	private LinearLayout mMyBottemSearchBtn, mMyBottemCheckinBtn, mMyBottemMyBtn;
	private ImageView mMyBottemSearchImg, mMyBottemMyImg, mMyBottemCheckinImg;
	private TextView mMyBottemSearchTxt, mMyBottemCheckinTxt,
			mMyBottemMyTxt;
	private List<View> list = new ArrayList<View>();// �൱������Դ
	private View view = null;
	private View view1 = null;
	private View view2 = null;
	private View view3 = null;
	private View view4 = null;
	private ViewPager mViewPager;
	private PagerAdapter pagerAdapter = null;// ����Դ��viewpager֮�������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frame);
		ActivityHolder.getInstance().setCurrentActivity(FrameActivity.this);
		initView();

//		DataSupport.deleteAll(Contact.class);
//		DataSupport.deleteAll(ChatMessage.class);

		ActivityHolder.getInstance().getConnection().getChatManager().addChatListener(new ChatManagerListener() {
			@Override
			public void chatCreated(Chat chat, boolean b) {
				chat.addMessageListener(new MessageListener() {
					@Override
					public void processMessage(Chat chat, Message message) {
//						Toast.makeText(ActivityHolder.getInstance().getCurrentActivity(), message.getBody(), Toast.LENGTH_LONG).show();
						String fromUserName = message.getFrom().split("[@]")[0];
						String userName = message.getTo().split("[@]")[0];

						ChatMessage chatMessage = new ChatMessage();
						chatMessage.setContent(message.getBody());
						chatMessage.setCreateTime(new Date().getTime());
						chatMessage.setFromUserName(fromUserName);
						chatMessage.setUserName(userName);
						chatMessage.setFromJID(message.getFrom());
						chatMessage.setTag(1);
						chatMessage.setTarget(fromUserName);

						chatMessage.save();

						Contact contact = null;
					 	List<Contact> contacts = DataSupport.where("fromUserName = ?", fromUserName).find(Contact.class);
					 	if (contacts == null || contacts.isEmpty()) {
					 		contact = new Contact();
					 		contact.setCreateTime(new Date().getTime());
					 		contact.setFromUserName(fromUserName);
					 		contact.setUserName(userName);
					 		contact.setFromJID(message.getFrom());

					 		contact.save();
						} else {
					 		contact = contacts.get(0);
							for (Contact contact1 : contacts) {
								if (contact1.getUserName().equals(UserInfoHolder.getInstance().getUserName())) {
									contact = contact1;
									break;
								}
							}
							if (!contact.getUserName().equals(UserInfoHolder.getInstance().getUserName())) {
								String fromUserName1 = contact.getFromUserName();
								String imageURL = contact.getImageURL();
								contact = new Contact();
								contact.setFromUserName(fromUserName1);
								contact.setUserName(UserInfoHolder.getInstance().getUserName());
								contact.setCreateTime(new Date().getTime());
								contact.setImageURL(imageURL);

								contact.save();
							}
						}

						contact.setUnReadCount(contact.getUnReadCount()+1);

					 	if (contact.getImageURL() == null || "".equals(contact.getImageURL())) {
					 		InquiryIQ inquiryIQ = new InquiryIQ();
					 		inquiryIQ.setType(IQ.Type.GET);

					 		inquiryIQ.setTarget("image");
					 		inquiryIQ.setTitle(contact.getFromUserName());

							Log.d("qzf", "processMessage: "+inquiryIQ.toXML());

							ActivityHolder.getInstance().sendPacket(inquiryIQ);
						}


						Activity activity = ActivityHolder.getInstance().getCurrentActivity();
					 	if (activity instanceof ChatActivity) {
					 		ChatActivity chatActivity = (ChatActivity) activity;
							MessageInfo messageInfo = new MessageInfo();
							messageInfo.setContent(message.getBody());
							messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
							messageInfo.setHeader(contact.getImageURL());

							chatActivity.addChatMessage(messageInfo);
						} else if (activity instanceof FrameActivity) {
					 		FrameActivity frameActivity = (FrameActivity) activity;
							MyMessageActivity myMessageActivity = (MyMessageActivity) frameActivity.getLocalActivityManager().getActivity("sign");
							myMessageActivity.updateImage();
						}

					}
				});
			}
		});
		sendLoginIQ();
	}

	// ��ʼ���ؼ�
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.FramePager);
		// ������linearlayoutΪ��ť���õĿؼ�
		mMyBottemSearchBtn = (LinearLayout) findViewById(R.id.MyBottemSearchBtn);
//		mMyBottemTuanBtn = (LinearLayout) findViewById(R.id.MyBottemTuanBtn);
		mMyBottemCheckinBtn = (LinearLayout) findViewById(R.id.MyBottemCheckinBtn);
		mMyBottemMyBtn = (LinearLayout) findViewById(R.id.MyBottemMyBtn);
//		mMyBottemMoreBtn = (LinearLayout) findViewById(R.id.MyBottemMoreBtn);
		// ����linearlayout�е�imageview
		mMyBottemSearchImg = (ImageView) findViewById(R.id.MyBottemSearchImg);
//		mMyBottemTuanImg = (ImageView) findViewById(R.id.MyBottemTuanImg);
		mMyBottemCheckinImg = (ImageView) findViewById(R.id.MyBottemCheckinImg);
		mMyBottemMyImg = (ImageView) findViewById(R.id.MyBottemMyImg);
//		mMyBottemMoreImg = (ImageView) findViewById(R.id.MyBottemMoreImg);
//		 ����linearlayout�е�textview
		mMyBottemSearchTxt = (TextView) findViewById(R.id.MyBottemSearchTxt);
//		mMyBottemTuanTxt = (TextView) findViewById(R.id.MyBottemTuanTxt);
		mMyBottemCheckinTxt = (TextView) findViewById(R.id.MyBottemCheckinTxt);
		mMyBottemMyTxt = (TextView) findViewById(R.id.MyBottemMyTxt);
//		mMyBottemMoreTxt = (TextView) findViewById(R.id.MyBottemMoreTxt);
		createView();
		// дһ���ڲ���pageradapter
		pagerAdapter = new PagerAdapter() {
			// �ж��ٴ���ӵ�view��֮ǰ��view �Ƿ���ͬһ��view
			// arg0����ӵ�view��arg1֮ǰ��
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			// ��������Դ����
			@Override
			public int getCount() {
				return list.size();
			}

			// ���ٱ������ߵ�view
			/**
			 * ViewGroup ���������ǵ�viewpager �൱��activitygroup���е�view������ ���֮ǰ���Ƴ���
			 * position �ڼ������� Object ���Ƴ���view
			 * */
			@Override
			public void destroyItem(ViewGroup container, int position,
                                    Object object) {
				// �Ƴ�view
				container.removeView(list.get(position));
			}

			/**
			 * instantiateItem viewpagerҪ��ʵ��view ViewGroup viewpager position
			 * �ڼ�������
			 * */
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// ��ȡview��ӵ��������У�������
				View v = list.get(position);
				container.addView(v);
				return v;
			}
		};
		// �������ǵ�adapter
		mViewPager.setAdapter(pagerAdapter);

		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		mMyBottemSearchBtn.setOnClickListener(mytouchlistener);
//		mMyBottemTuanBtn.setOnClickListener(mytouchlistener);
		mMyBottemCheckinBtn.setOnClickListener(mytouchlistener);
		mMyBottemMyBtn.setOnClickListener(mytouchlistener);
//		mMyBottemMoreBtn.setOnClickListener(mytouchlistener);

		// ����viewpager�����л�����,����viewpager�л��ڼ��������Լ�������
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			// arg0 ��ȡ viewpager����Ľ����л����ڼ�����
			@Override
			public void onPageSelected(int arg0) {
				// �������ť��ʽ
				initBottemBtn();
				// ���ն�Ӧ��view��tag���жϵ����л����ĸ����档
				// ���Ķ�Ӧ��button״̬
				int flag = (Integer) list.get((arg0)).getTag();
				if (flag == 0) {
					mMyBottemSearchImg
							.setImageResource(R.drawable.main_index_search_pressed);
					mMyBottemSearchTxt.setTextColor(Color.parseColor("#FF8C00"));
//				} else if (flag == 1) {
//					mMyBottemTuanImg
//							.setImageResource(R.drawable.main_index_tuan_pressed);
//					mMyBottemTuanTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 2) {
					mMyBottemCheckinImg
							.setImageResource(R.drawable.main_index_checkin_pressed);
					mMyBottemCheckinTxt.setTextColor(Color
							.parseColor("#FF8C00"));
				} else if (flag == 3) {
					mMyBottemMyImg
							.setImageResource(R.drawable.main_index_my_pressed);
					mMyBottemMyTxt.setTextColor(Color.parseColor("#FF8C00"));
//				} else if (flag == 4) {
//					mMyBottemMoreImg
//							.setImageResource(R.drawable.main_index_more_pressed);
//					mMyBottemMoreTxt.setTextColor(Color.parseColor("#FF8C00"));
				}
			}

			/**
			 * ����ҳ�滬���� arg0 ��ʾ��ǰ������view arg1 ��ʾ�����İٷֱ� arg2 ��ʾ�����ľ���
			 * */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			/**
			 * ��������״̬ arg0 ��ʾ���ǵĻ���״̬ 0:Ĭ��״̬ 1:����״̬ 2:����ֹͣ
			 * */
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	public void sendLoginIQ() {
		LoginIQ loginIQ = new LoginIQ();

		loginIQ.setUserName(UserInfoHolder.getInstance().getUserName());
		loginIQ.setPassword(UserInfoHolder.getInstance().getPassword());

		loginIQ.setType(IQ.Type.GET);

		Log.d("qzf", "sendLoginIQ: "+loginIQ.toXML());

		ActivityHolder.getInstance().sendPacket(loginIQ);
	}

	// ��viewpager����Ҫ��ʾ��viewʵ�������������Ұ���ص�view��ӵ�һ��list����
	private void createView() {
		view = this
				.getLocalActivityManager()
				.startActivity("search",
						new Intent(FrameActivity.this, SearchActivity.class))
				.getDecorView();
		// ������������button����ʽ�ı�־
		view.setTag(0);
		list.add(view);
		view1 = FrameActivity.this
				.getLocalActivityManager()
				.startActivity("tuan",
						new Intent(FrameActivity.this, TuanActivity.class))
				.getDecorView();
		view1.setTag(1);
		list.add(view1);
		view2 = FrameActivity.this
				.getLocalActivityManager()
				.startActivity("sign",
						new Intent(FrameActivity.this, MyMessageActivity.class))
				.getDecorView();
		view2.setTag(2);
		list.add(view2);
		view3 = FrameActivity.this
				.getLocalActivityManager()
				.startActivity("my",
						new Intent(FrameActivity.this, MyActivity.class))
				.getDecorView();
		view3.setTag(3);
		list.add(view3);
		view4 = FrameActivity.this
				.getLocalActivityManager()
				.startActivity("more",
						new Intent(FrameActivity.this, MoreActivity.class))
				.getDecorView();
		view4.setTag(4);
		list.add(view4);
	}

	@Override
	protected void onResume() {
		super.onResume();

		ActivityHolder.getInstance().setCurrentActivity(FrameActivity.this);

		ActivityHolder.getInstance().refreshAllFrameAcrivity();

		InquiryIQ inquiryIQ = new InquiryIQ();

		inquiryIQ.setTarget("activity");
		inquiryIQ.setTitle("perference:"+ UserInfoHolder.getInstance().getUserName());
		inquiryIQ.setType(IQ.Type.GET);

		Log.d("qzf's", "sendInquiryIQ: "+inquiryIQ.toXML());

		ActivityHolder.getInstance().sendPacket(inquiryIQ);
	}

	/**
	 * ��linearlayout��Ϊ��ť�ļ����¼�
	 * */
	private class MyBtnOnclick implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			int mBtnid = arg0.getId();
			switch (mBtnid) {
			case R.id.MyBottemSearchBtn:
				// //�������ǵ�viewpager��ת�Ǹ�����0������������ǵ�list���,�൱��list������±�
				mViewPager.setCurrentItem(0);
				initBottemBtn();
				mMyBottemSearchImg
						.setImageResource(R.drawable.main_index_search_pressed);
				mMyBottemSearchTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
//			case R.id.MyBottemTuanBtn:
//				mViewPager.setCurrentItem(1);
//				initBottemBtn();
//				mMyBottemTuanImg
//						.setImageResource(R.drawable.main_index_tuan_pressed);
//				mMyBottemTuanTxt.setTextColor(Color.parseColor("#FF8C00"));
//				break;
			case R.id.MyBottemCheckinBtn:
				mViewPager.setCurrentItem(2);
				initBottemBtn();
				mMyBottemCheckinImg
						.setImageResource(R.drawable.main_index_checkin_pressed);
				mMyBottemCheckinTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			case R.id.MyBottemMyBtn:
				mViewPager.setCurrentItem(3);
				initBottemBtn();
				mMyBottemMyImg
						.setImageResource(R.drawable.main_index_my_pressed);
				mMyBottemMyTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
//			case R.id.MyBottemMoreBtn:
//				mViewPager.setCurrentItem(4);
//				initBottemBtn();
//				mMyBottemMoreImg
//						.setImageResource(R.drawable.main_index_more_pressed);
//				mMyBottemMoreTxt.setTextColor(Color.parseColor("#FF8C00"));
//				break;
			}

		}

	}

	/**
	 * ��ʼ���ؼ�����ɫ
	 * */
	private void initBottemBtn() {
		mMyBottemSearchImg.setImageResource(R.drawable.search_bottem_search);
//		mMyBottemTuanImg.setImageResource(R.drawable.search_bottem_tuan);
		mMyBottemCheckinImg.setImageResource(R.drawable.search_bottem_checkin);
		mMyBottemMyImg.setImageResource(R.drawable.search_bottem_my);
//		mMyBottemMoreImg.setImageResource(R.drawable.search_bottem_more);
		mMyBottemSearchTxt.setTextColor(getResources().getColor(
				R.color.search_bottem_textcolor));
//		mMyBottemTuanTxt.setTextColor(getResources().getColor(
//				R.color.search_bottem_textcolor));
		mMyBottemCheckinTxt.setTextColor(getResources().getColor(
				R.color.search_bottem_textcolor));
		mMyBottemMyTxt.setTextColor(getResources().getColor(
				R.color.search_bottem_textcolor));
//		mMyBottemMoreTxt.setTextColor(getResources().getColor(
//				R.color.search_bottem_textcolor));
	}

	/**
	 * ���ذ�ť�ļ���������ѯ���û��Ƿ��˳�����
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(FrameActivity.this);
				builder.setTitle("��ʾ");
				builder.setMessage("��ȷ��Ҫ�˳���");
				builder.setIcon(R.drawable.ic_launcher);

				DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						if (arg1 == DialogInterface.BUTTON_POSITIVE) {
							arg0.cancel();
						} else if (arg1 == DialogInterface.BUTTON_NEGATIVE) {
							FrameActivity.this.finish();
						}
					}
				};
				builder.setPositiveButton("ȡ��", dialog);
				builder.setNegativeButton("ȷ��", dialog);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		}
		return false;
	}
}

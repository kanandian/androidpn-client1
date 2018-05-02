package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.SearchMainAdapter;
import org.androidpn.adapter.SearchMoreAdapter;
import org.androidpn.adapter.ShopAdapter;
import org.androidpn.info.ShopInfo;
import org.androidpn.model.Model;
import org.androidpn.net.MyGet;
import org.androidpn.net.ThreadPoolUtils;
import org.androidpn.thread.HttpGetThread;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.MyJson;
import org.jivesoftware.smack.packet.IQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �����б�ģ��
 * */
public class MyBussinessesActivity extends BaseActivity {

    private static final int UPDATE_UI = 0;


    private ListView mListView;
    private ImageView mShoplist_back;
    private LinearLayout mShoplist_shanghuleixing;
    private List<ShopInfo> list = new ArrayList<ShopInfo>();
    private ShopAdapter mAdapter = null;
    private List<Map<String, Object>> mainList1;
    private List<Map<String, Object>> mainList2;

    private String title;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                mAdapter = new ShopAdapter(list, MyBussinessesActivity.this);
                mListView.setAdapter(mAdapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_bussinesses);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");

        initView();
    }

    private void initView() {
        mShoplist_back = (ImageView) findViewById(R.id.Shoplist_back);
        mShoplist_shanghuleixing = (LinearLayout) findViewById(R.id.Shoplist_shanghuleixing);
        mListView = (ListView) findViewById(R.id.ShopListView);

        MyOnclickListener mOnclickListener = new MyOnclickListener();
        mShoplist_back.setOnClickListener(mOnclickListener);
        mShoplist_shanghuleixing.setOnClickListener(mOnclickListener);
        // -----------------------------------------------------------------
        initModel1();
        initModel2();
        // -----------------------------------------------------------------
        mAdapter = new ShopAdapter(list, MyBussinessesActivity.this);

        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShopInfo shopInfo = list.get(i);

                if ("外卖".equals(shopInfo.getStype())) {
//                    Intent intent = new Intent(MyBussinessesActivity.this, EditTakeoutMenuActivity.class);
//                    intent.putExtra("bussinessId", shopInfo.getSid());
//                    startActivity(intent);

                    Intent intent = new Intent(MyBussinessesActivity.this, TakeoutOrderActivity.class);
                    intent.putExtra("bussinessId", shopInfo.getSid());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void sendInquiryIQ() {
        super.sendInquiryIQ();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setTarget("holder");
        inquiryIQ.setTitle(title);
        inquiryIQ.setType(IQ.Type.GET);

        Log.d("qzf", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }

    @Override
    public void setContentList(Object contentList) {
        super.setContentList(contentList);

        if(contentList instanceof List) {
            this.list = (List<ShopInfo>) contentList;
            Message msg = new Message();
            msg.what = UPDATE_UI;
            handler.sendMessage(msg);
        }

    }

    private class MyOnclickListener implements View.OnClickListener {
        public void onClick(View v) {
            int mID = v.getId();

            if (mID == R.id.Shoplist_back) {
                MyBussinessesActivity.this.finish();
            }
        }
    }

    private void initModel1() {
        mainList1 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < Model.SHOPLIST_PLACE.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("txt", Model.SHOPLIST_PLACE[i]);
            mainList1.add(map);
        }
    }

    private void initModel2() {
        mainList2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < Model.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", Model.LISTVIEWIMG[i]);
            map.put("txt", Model.LISTVIEWTXT[i]);
            mainList2.add(map);
        }
    }

}

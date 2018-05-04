package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.CommentAdapter;
import org.androidpn.adapter.ShopAdapter;
import org.androidpn.model.Comment;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macpro on 2018/4/4.
 */

public class ShopCommentsActivity extends BaseActivity {

    private static int UPDATE_UI = 1;

    private List<Comment> commentList = new ArrayList<Comment>();

    private ImageView backBtn;

    private ListView listComments;
    private CommentAdapter adapter;
    private String bussinessId;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                adapter = new CommentAdapter(ShopCommentsActivity.this, R.layout.list_comments, commentList);
                listComments.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcomment);

        Intent intent = getIntent();
        bussinessId = intent.getStringExtra("bussinessId");

        listComments = (ListView) findViewById(R.id.list_comments);

        backBtn = (ImageView) findViewById(R.id.Login_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopCommentsActivity.this.finish();
            }
        });

    }

    @Override
    public void sendInquiryIQ() {
        super.sendInquiryIQ();

        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setTarget("comment");
        inquiryIQ.setTitle(bussinessId);
        inquiryIQ.setType(IQ.Type.GET);

        Log.d("qzf's log", "sendInquiryIQ: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);

    }

    @Override
    public void setContentList(Object contentList) {
        super.setContentList(contentList);

        if (contentList instanceof List) {
            this.commentList = (List<Comment>) contentList;

            Message msg = new Message();
            msg.what = UPDATE_UI;
            handler.sendMessage(msg);
        }

    }
}

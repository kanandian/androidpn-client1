package org.androidpn.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.adapter.ShopAdapter;
import org.androidpn.model.Comment;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macpro on 2018/4/4.
 */

public class ShopCommentActivity extends BaseActivity {

    private static int UPDATE_UI = 1;

    private List<Comment> commentList = new ArrayList<Comment>();

    private ListView listComments;
    private String bussinessId;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcomment);

        listComments = (ListView) findViewById(R.id.list_comments);

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

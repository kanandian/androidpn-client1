package org.androidpn.packetlistener;

import android.app.Activity;
import android.util.Log;

import org.androidpn.IQ.ActivityInquiryIQ;
import org.androidpn.IQ.CommentsIQ;
import org.androidpn.client.XmppManager;
import org.androidpn.demoapp.ShopCommentsActivity;
import org.androidpn.demoapp.ShopListActivity;
import org.androidpn.info.ShopInfo;
import org.androidpn.model.Bussiness;
import org.androidpn.model.Comment;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macpro on 2018/4/4.
 */

public class CommentsPacketListener implements PacketListener {

    private final XmppManager xmppManager;

    public CommentsPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        if(packet instanceof CommentsIQ) {
            CommentsIQ commentsIQ = (CommentsIQ) packet;
            Activity activity = ActivityHolder.getInstance().getCurrentActivity();
            if(activity instanceof ShopCommentsActivity) {
                ShopCommentsActivity shopCommentActivity = (ShopCommentsActivity) activity;

                List<Comment> commentList = commentsIQ.getCommentList();

                shopCommentActivity.setContentList(commentList);
            }
        }
    }
}

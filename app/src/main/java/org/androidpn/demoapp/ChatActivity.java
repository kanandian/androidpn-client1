package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;

import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class ChatActivity extends BaseActivity {

    private ChatManager chatManager;
    private Chat chat;

    private String toJID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    private void initData() {
        Intent intent = getIntent();
        toJID = intent.getStringExtra("JID");

        chatManager = ActivityHolder.getInstance().getConnection().getChatManager();
        chat = chatManager.createChat(toJID, null);
    }

    public void sendMessage(String content) throws XMPPException {
        Message message = new Message();
        message.setBody(content);
        chat.sendMessage(message);
    }
}

package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidpn.adapter.ContactAdapter;
import org.androidpn.enity.MessageInfo;
import org.androidpn.entity.ChatMessage;
import org.androidpn.model.Contact;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MyMessageActivity extends BaseActivity {

    private ListView listView;
    private ContactAdapter adapter;

    private List<Contact> contactList;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                loadMessage();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);

        initData();
        loadMessage();

        ActivityHolder.getInstance().addFrameActivity(MyMessageActivity.this);

    }

    private void initData() {
        listView = (ListView) findViewById(R.id.list_message);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactList.get(i);
                Intent intent = new Intent(MyMessageActivity.this, ChatActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });
    }

    @Override
    public void refresh() {
        super.refresh();
        updateImage();
    }

    public void updateImage() {
        Message msg = new Message();
        msg.what = UPDATE_UI;
        handler.sendMessage(msg);
    }

    public void loadMessage() {
        if (UserInfoHolder.getInstance().isAuth()) {
            String userName = UserInfoHolder.getInstance().getUserName();
            contactList = DataSupport.where("userName = ?", userName).order("createTime desc").find(Contact.class);

            adapter = new ContactAdapter(MyMessageActivity.this, contactList);
            listView.setAdapter(adapter);

        } else {



        }
    }
}

package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidpn.adapter.ContactAdapter;
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
        loadMessage();
    }

    public void loadMessage() {
        if (UserInfoHolder.getInstance().isAuth()) {
            String userName = UserInfoHolder.getInstance().getUserName();
            contactList = DataSupport.where("userName = ?", userName).find(Contact.class);

            adapter = new ContactAdapter(MyMessageActivity.this, contactList);
            listView.setAdapter(adapter);

        } else {



        }
    }
}

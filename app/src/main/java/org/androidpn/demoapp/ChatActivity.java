package org.androidpn.demoapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;

import org.androidpn.adapter.ChatAdapter;
import org.androidpn.adapter.CommentAdapter;
import org.androidpn.adapter.CommonFragmentPagerAdapter;
import org.androidpn.client.XmppManager;
import org.androidpn.enity.FullImageInfo;
import org.androidpn.enity.MessageInfo;
import org.androidpn.entity.ChatMessage;
import org.androidpn.fragment.ChatEmotionFragment;
import org.androidpn.fragment.ChatFunctionFragment;
import org.androidpn.model.Contact;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.Constants;
import org.androidpn.utils.GlobalOnItemClickManagerUtils;
import org.androidpn.utils.MediaManager;
import org.androidpn.utils.UserInfoHolder;
import org.androidpn.widget.EmotionInputDetector;
import org.androidpn.widget.NoScrollViewPager;
import org.androidpn.widget.StateButton;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatActivity extends BaseAppCompatActivity {

    private final static int UPDATE_IMAGE = 1;

    @Bind(R.id.chat_list)
    EasyRecyclerView chatList;
//    @Bind(R.id.emotion_voice)
//    ImageView emotionVoice;
    @Bind(R.id.edit_text)
    EditText editText;
    @Bind(R.id.btn_send_message)
    Button sendButton;
//    @Bind(R.id.voice_text)
//    TextView voiceText;
//    @Bind(R.id.emotion_button)
//    ImageView emotionButton;
//    @Bind(R.id.emotion_add)
//    ImageView emotionAdd;
//    @Bind(R.id.emotion_send)
//    StateButton emotionSend;
//    @Bind(R.id.viewpager)
//    NoScrollViewPager viewpager;
//    @Bind(R.id.emotion_layout)
//    RelativeLayout emotionLayout;

//    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
    private List<MessageInfo> messageInfos;
    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;

    private ChatManager chatManager;
    private Chat chat;

    private Contact contact;
    private String toJID;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                MessageInfo messageInfo = (MessageInfo) msg.obj;
                messageInfo.setHeader(messageInfo.getHeader().replace("localhost", ActivityHolder.getInstance().getConnection().getHost()));
                messageInfos.add(messageInfo);
                chatAdapter.add(messageInfo);
                chatList.scrollToPosition(chatAdapter.getCount() - 1);
            } else if (msg.what == UPDATE_IMAGE) {
                LoadData();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
//        EventBus.getDefault().register(this);
        initData();
        initWidget();
    }

    private void initData() {
        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");
        toJID = contact.getFromUserName();

        chatManager = ActivityHolder.getInstance().getConnection().getChatManager();
        chat = chatManager.createChat(toJID, null);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content  = editText.getText().toString();
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setContent(content);
                messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
                messageInfo.setHeader(UserInfoHolder.getInstance().getImageURL().replace("localhost", ActivityHolder.getInstance().getConnection().getHost()));
                messageInfos.add(messageInfo);
                chatAdapter.add(messageInfo);
                chatList.scrollToPosition(chatAdapter.getCount() - 1);

                try {
                    sendMessage(content);
                } catch (XMPPException e) {
                    messageInfo.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
                    chatAdapter.notifyDataSetChanged();
                    e.printStackTrace();
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setTag(0);
                chatMessage.setUserName(UserInfoHolder.getInstance().getUserName());
                chatMessage.setFromUserName(UserInfoHolder.getInstance().getUserName());
                chatMessage.setCreateTime(new Date().getTime());
                chatMessage.setContent(content);
                chatMessage.setTarget(contact.getFromUserName());

                chatMessage.save();


                Contact updateContact = new Contact();
                updateContact.setLastUnRead(content);
                updateContact.setCreateTime(new Date().getTime());
                updateContact.updateAll("fromUserName = ? and userName = ?", contact.getFromUserName(), UserInfoHolder.getInstance().getUserName());

                editText.setText("");
            }
        });
    }

    public void sendMessage(String content) throws XMPPException {
        Message message = new Message();
        message.setBody(content);
        chat.sendMessage(message);
    }

    private void initWidget() {
//        View layout = getLayoutInflater().inflate(R.layout.include_reply_layout, null);
//        emotionVoice = (ImageView) layout.findViewById(R.id.emotion_voice);
//        editText = (EditText) layout.findViewById(R.id.edit_text);
//        voiceText = (TextView) layout.findViewById(R.id.voice_text);
//        emotionButton = (ImageView) layout.findViewById(R.id.emotion_button);
//        emotionAdd = (ImageView) layout.findViewById(R.id.emotion_add);
//        emotionSend = (StateButton) layout.findViewById(R.id.emotion_send);
//        emotionLayout = (RelativeLayout) layout.findViewById(R.id.emotion_layout);
//        viewpager = (NoScrollViewPager) layout.findViewById(R.id.viewpager);

        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
//        viewpager.setAdapter(adapter);
//        viewpager.setCurrentItem(0);

//        mDetector = EmotionInputDetector.with(this)
//                .setEmotionView(emotionLayout)
//                .setViewPager(viewpager)
//                .bindToContent(chatList)
//                .bindToEditText(editText)
//                .bindToEmotionButton(emotionButton)
//                .bindToAddButton(emotionAdd)
//                .bindToSendButton(emotionSend)
//                .bindToVoiceButton(emotionVoice)
//                .bindToVoiceText(voiceText)
//                .build();

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);

        chatAdapter = new ChatAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
//                        mDetector.hideEmotionLayout(false);
//                        mDetector.hideSoftInput();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        chatAdapter.addItemClickListener(itemClickListener);
        LoadData();
    }

    /**
     * item点击事件
     */
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {
            Toast.makeText(ChatActivity.this, "onHeaderClick", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImageClick(View view, int position) {
            int location[] = new int[2];
            view.getLocationOnScreen(location);
            FullImageInfo fullImageInfo = new FullImageInfo();
            fullImageInfo.setLocationX(location[0]);
            fullImageInfo.setLocationY(location[1]);
            fullImageInfo.setWidth(view.getWidth());
            fullImageInfo.setHeight(view.getHeight());
            fullImageInfo.setImageUrl(messageInfos.get(position).getImageUrl());
//            EventBus.getDefault().postSticky(fullImageInfo);
            startActivity(new Intent(ChatActivity.this, FullImageActivity.class));
            overridePendingTransition(0, 0);
        }

        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.mipmap.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.mipmap.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }
    };

    /**
     * 构造聊天数据
     */
    public void LoadData() {
        messageInfos = new ArrayList<>();

        List<ChatMessage> chatMessages = DataSupport.where("target = ? and userName = ?", toJID, UserInfoHolder.getInstance().getUserName()).find(ChatMessage.class);


        for (ChatMessage chatMessage : chatMessages) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setContent(chatMessage.getContent());

            if (chatMessage.getTag() == 1) {
                if (contact.getImageURL() != null) {
                    messageInfo.setHeader(contact.getImageURL().replace("localhost", ActivityHolder.getInstance().getConnection().getHost()));
                }
                messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
            } else if (chatMessage.getTag() == 0) {
                messageInfo.setHeader(UserInfoHolder.getInstance().getImageURL().replace("localhost", ActivityHolder.getInstance().getConnection().getHost()));
                messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
            }
            messageInfos.add(messageInfo);
        }


//        MessageInfo messageInfo = new MessageInfo();
//        messageInfo.setContent("你好，欢迎使用Rance的聊天界面框架");
//        messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//        messageInfo.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
//        messageInfos.add(messageInfo);
//
//        MessageInfo messageInfo1 = new MessageInfo();
//        messageInfo1.setFilepath("http://www.trueme.net/bb_midi/welcome.wav");
//        messageInfo1.setVoiceTime(3000);
//        messageInfo1.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
//        messageInfo1.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//        messageInfo1.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
//        messageInfos.add(messageInfo1);
//
//        MessageInfo messageInfo2 = new MessageInfo();
//        messageInfo2.setImageUrl("http://img4.imgtn.bdimg.com/it/u=1800788429,176707229&fm=21&gp=0.jpg");
//        messageInfo2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//        messageInfo2.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
//        messageInfos.add(messageInfo2);
//
//        MessageInfo messageInfo3 = new MessageInfo();
//        messageInfo3.setContent("[微笑][色][色][色]");
//        messageInfo3.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
//        messageInfo3.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
//        messageInfo3.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
//        messageInfos.add(messageInfo3);
        chatAdapter.clear();
        chatAdapter.addAll(messageInfos);

        chatList.scrollToPosition(chatAdapter.getCount() - 1);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void MessageEventBus(final MessageInfo messageInfo) {
//        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
//        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
//        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
//        messageInfos.add(messageInfo);
//        chatAdapter.add(messageInfo);
//        chatList.scrollToPosition(chatAdapter.getCount() - 1);
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//                chatAdapter.notifyDataSetChanged();
//            }
//        }, 2000);
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                MessageInfo message = new MessageInfo();
//                message.setContent("这是模拟消息回复");
//                message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//                message.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
//                messageInfos.add(message);
//                chatAdapter.add(message);
//                chatList.scrollToPosition(chatAdapter.getCount() - 1);
//            }
//        }, 3000);
//    }

    public void addChatMessage(MessageInfo messageInfo) {

        android.os.Message msg = new android.os.Message();
        msg.what = UPDATE_UI;
        msg.obj = messageInfo;
        handler.sendMessage(msg);
    }

    public void updateImage(String imageURL) {
        contact.setImageURL(imageURL);
        android.os.Message msg = new android.os.Message();
        msg.what = UPDATE_IMAGE;
        handler.sendMessage(msg);
    }

    @Override
    public void onBackPressed() {
//        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
//        EventBus.getDefault().removeStickyEvent(this);
//        EventBus.getDefault().unregister(this);
    }

    public String getToJID() {
        return toJID;
    }
}

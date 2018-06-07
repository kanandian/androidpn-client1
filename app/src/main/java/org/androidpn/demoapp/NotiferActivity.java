package org.androidpn.demoapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.IQ.NotiferIQ;
import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.info.ShopInfo;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotiferActivity extends BaseActivity {

    @Bind(R.id.title)
    EditText titleEdit;
    @Bind(R.id.message)
    EditText messageEdit;

    @Bind(R.id.btn_notifer)
    TextView notiferButton;

    private ShopInfo shopInfo;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == UPDATE_UI) {
                String message = (String) msg.obj;
                Toast.makeText(ActivityHolder.getInstance().getCurrentActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifer);

        ButterKnife.bind(this);

        initData();
    }

    public void initData() {

        shopInfo = (ShopInfo) getIntent().getSerializableExtra("shopInfo");

        notiferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifer();
            }
        });
    }

    public void notifer() {
        String title = titleEdit.getText().toString();
        String message = messageEdit.getText().toString();

        if (title == null || "".equals(title)) {
            Toast.makeText(NotiferActivity.this, "请输入标题", Toast.LENGTH_LONG).show();
            return;
        }
        if (message == null || "".equals(message)) {
            Toast.makeText(NotiferActivity.this, "请输入内容", Toast.LENGTH_LONG).show();
            return;
        }

        NotiferIQ notiferIQ = new NotiferIQ();
        notiferIQ.setType(IQ.Type.SET);

        notiferIQ.setBussinessId(shopInfo.getSid());
        notiferIQ.setUserName(UserInfoHolder.getInstance().getUserName());
        notiferIQ.setTitle(title);
        notiferIQ.setMessage(message);

        Log.d("qzf", "notifer: "+notiferIQ.toXML());

        ActivityHolder.getInstance().sendPacket(notiferIQ);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @Override
    public void updateForResponse(ResultModelIQ resultModelIQ) {
        super.updateForResponse(resultModelIQ);

        if (resultModelIQ.getErrCode() == 0) {
            NotiferActivity.this.finish();
        } else {
            Message msg = new Message();
            msg.what = UPDATE_UI;
            msg.obj = resultModelIQ.getErrMsg();
            handler.sendMessage(msg);
        }
    }
}

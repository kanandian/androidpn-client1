package org.androidpn.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class HandlerUtil {

    public static final int TOAST_SHOW = 0;

    private static HandlerUtil handlerUtil = null;

    private Handler handler;

    private HandlerUtil() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(msg.what == TOAST_SHOW) {
                    String message = (String) msg.obj;
                    Toast.makeText(ActivityHolder.getInstance().getCurrentActivity(), message, Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public static HandlerUtil getInstance() {
        if (handlerUtil == null) {
            synchronized (HandlerUtil.class) {
                if (handlerUtil == null) {
                    handlerUtil = new HandlerUtil();
                }
            }
        }

        return handlerUtil;

    }

    public void showToast(String message) {
        Message msg = new Message();
        msg.what = TOAST_SHOW;
        msg.obj = message;
        handler.sendMessage(msg);
    }

}

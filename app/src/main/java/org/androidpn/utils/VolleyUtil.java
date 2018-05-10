package org.androidpn.utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyUtil {

    private static VolleyUtil volleyUtil = null;

    private RequestQueue requestQueue;

    private VolleyUtil() {
        requestQueue = Volley.newRequestQueue(ActivityHolder.getInstance().getCurrentActivity());
    }

    public static VolleyUtil getInstance() {
        if (volleyUtil == null) {
            synchronized (VolleyUtil.class) {
                if (volleyUtil == null) {
                    volleyUtil = new VolleyUtil();
                }
            }
        }

        return volleyUtil;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}

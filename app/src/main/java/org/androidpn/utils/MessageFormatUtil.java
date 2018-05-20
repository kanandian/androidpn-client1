package org.androidpn.utils;

import android.widget.ImageView;

import org.androidpn.demoapp.R;

public class MessageFormatUtil {

    public static String getOrderStatus(int orderStatus) {
        if (orderStatus == 0) {
            return "未接单";
        } else if (orderStatus == 1) {
            return "已接单";
        } else if (orderStatus == 2) {
            return "正在配送";
        } else if (orderStatus == 3) {
            return "已完成";
        }
        return "商家拒绝接单";
    }

    public static int getStarImage(int star) {
        if (star == 0) {
            return R.drawable.star0;
        } else if (star == 1) {
            return R.drawable.star1;
        } else if (star == 2) {
            return R.drawable.star2;
        } else if (star == 3) {
            return R.drawable.star3;
        } else if (star == 4) {
            return R.drawable.star4;
        } else if (star == 5) {
            return R.drawable.star5;
        }
        return R.drawable.star0;
    }

}

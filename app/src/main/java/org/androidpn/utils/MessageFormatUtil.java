package org.androidpn.utils;

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

}

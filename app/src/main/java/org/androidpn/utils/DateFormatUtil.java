package org.androidpn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getFormatTime(long timeStamp) {
        Date date = new Date(timeStamp);
        return sdf.format(date);
    }
}

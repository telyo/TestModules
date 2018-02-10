package com.telyo.doublerdatepick.DoubleDatePicker.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author Administrator
 * date: 2018/2/5.
 * describe:
 */

public class DateFormatUtils {

    public static long second = 1000;
    public static long min = second * 60;
    public static long hour = min * 60;
    public static long day = hour * 24;
    public static long week = day * 7;
    public static long year = day * 365;

    public static Date getFirstDayForNextMouth(int count) {
        long current = System.currentTimeMillis();
        Date date = new Date(current);
        int month = date.getMonth();
        int year = date.getYear();
        return new Date(year, month + count, 1);
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        long current = System.currentTimeMillis();
        Date currentDate = new Date(current);
        if (sd.format(date).equals(sd.format(currentDate))) {
            return "今天";
        }
        return sdf.format(date);
    }

 

    public static int betweenDate(Date begin, Date end) {
        return (int) (end.getTime() / day - begin.getTime() / day);
    }

}

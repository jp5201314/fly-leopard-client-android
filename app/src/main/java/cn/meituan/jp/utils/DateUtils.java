package cn.meituan.jp.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cj on 2016/10/13.
 */
public class DateUtils {

    //时间戳转化为StingDate
    public static String timestampToDate(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = new Long(timestamp);
        String date = format.format(time);
        return date;
    }

    //时间戳转化为StringDate
    public static String timestampToDate(long timestamp, String format) {
        if (null == format) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        Long time = timestamp;
        return simpleDateFormat.format(time);
    }

    //Date或者String转化为时间戳
    public static long dateToTimestamp(String time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long dateTime = date.getTime();
        return dateTime;
    }


    //Date或者String转化为时间戳
    public static long dateToTimestamp(String time, String format) {
        if (null == format) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date != null ? date.getTime() : 0;
    }

    //时间格式转换
    public static String dateFormatConvert(String time, String format) {
        if (time == null) {
            return "";
        }
        long timestamp = dateToTimestamp(time);
        Long aLong = new Long(timestamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String date = simpleDateFormat.format(aLong);
        return date;
    }

    //获取今日0点的时间戳
    public static long getZeroTimestamp(){
        //获取今天00：00时间戳的方法
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long timeInMillis = calendar.getTimeInMillis();
        return timeInMillis;
    }

    public  static  String getYesterdayTime(){
        long yesterdayTimeMills = System.currentTimeMillis() - 24*60*60*1000;
        String s = timestampToDate(yesterdayTimeMills);
        return s;
    }
}

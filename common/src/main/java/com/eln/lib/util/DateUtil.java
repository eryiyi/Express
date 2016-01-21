package com.eln.lib.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: pj
 * Date: 14-3-7
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {
    public static final String TO_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String TO_SHORT_YYYY_MM_DD_HH_MM = "yyyy/MM/dd HH:mm";
    public static final String TO_SHORTpYYYYpMMpDD_HH_MM = "yyyy.MM.dd HH:mm";
    public static final String TO_HH_MM_SS = "HH:mm:ss";
    public static final String FROM_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FROM_HHMMSS = "HHmmss";

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("GMT");
        calendar.setTimeZone(tz);
        long unixTime = calendar.getTimeInMillis();
        long unixTimeGMT = unixTime;
        SimpleDateFormat timeFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date = new Date(unixTimeGMT);
        String getTime = timeFormat.format(date);
        return getTime;
    }

    public static String getAPMCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("GMT");
        calendar.setTimeZone(tz);
        long unixTime = calendar.getTimeInMillis();
        long unixTimeGMT = unixTime;
        SimpleDateFormat timeFormat = new SimpleDateFormat(
                "aa HH:mm");
        Date date = new Date(unixTimeGMT);
        String getTime = timeFormat.format(date);
        return getTime;
    }

    public static String formatDate(String parttern, String dateString) {
        if(dateString == null || "".equals(dateString)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FROM_YYYYMMDDHHMMSS);
        try {
            Date date = sdf.parse(dateString);
            return format(parttern, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatTime(String parttern, String timeString) {
        if(timeString == null || "".equals(timeString)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FROM_HHMMSS);
        try {
            Date date = sdf.parse(timeString);
            return format(parttern, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long toSecond(String timeString) {
        if(timeString == null) {
            return 0;
        }
        String[] timeSplits = timeString.split(":");
        if(timeSplits.length < 3) {
            return 0;
        }

        int hour = Integer.valueOf(timeSplits[0]);
        int min = Integer.valueOf(timeSplits[1]);
        int sec = Integer.valueOf(timeSplits[2]);
        long millsecond = hour * 60 * 60 + min * 60 + sec;
        return millsecond;
    }

    public static String format(String parttern, Date date) {
        DateFormat format = new SimpleDateFormat(parttern);
        return format.format(date);
    }

    /**
     * 转换为时间
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatTime(long mss) {
        long hours = mss / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":"
                + String.format("%02d", seconds);
    }

    /**
     * 转换为时间
     * @param mss 要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatTime2(long mss) {
        long hours = mss / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        String sHours = hours > 0 ? hours + "小时" :"";
        String sMinutes = minutes > 0 ? minutes + "分" :"";
        String sSeconds = seconds > 0 ? seconds + "秒" :"";

        return sHours + sMinutes + sSeconds;
    }

    /**
     * 比较时间大小
     * @param date1
     * @param date2
     * @return
     */
    public static int compare_date(String date1, String date2)
    {
        if(date1.length()<11){
            date1+=" 00:00:00";
        }if(date2.length()<11){
        date1+=" 00:00:00";
    }
        int result = 0;
        try {
            if (DateUtil.toDate(date1).after(DateUtil.toDate(date2))) {

                result = 1;
            } else if (DateUtil.toDate(date1).before(DateUtil.toDate(date2))) {

                result = -1;
            } else {
                result = 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;

    }

    /**
     * 把String类型的值变为Date类型
     */
    public static Date toDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat(TO_YYYY_MM_DD_HH_MM_SS);
        Date date = null;
        try {
            date = sdf.parse(value);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;

    }

    public static void main(String[] strs) {
//        System.out.println(formatDate(TO_YYYY_MM_DD_HH_MM_SS, "20140101111111"));
//        System.out.println(formatTime(1000*60*60));
        System.out.println("date getTime = " + getAPMCurrentTime());
    }
}

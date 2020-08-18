package com.huantai.qhytims.tools;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wk on 2018/10/16.
 */

public class TimeUtil {

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String transferLongToDate(Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    public static boolean timeCompareMore(String date1, String date2){
        //格式化时间
        SimpleDateFormat CurrentTime= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {

            Date beginTime=CurrentTime.parse(date1);
            Date endTime=CurrentTime.parse(date2);
            //判断是否大于两天
            if((beginTime.getTime() - endTime.getTime())>0) {
                return true;
            }else{
                return false;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    public static long getTimeDistance(String beginDateStr, String endDateStr) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = sdf.parse(beginDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(beginDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, fromCalendar.getMinimum(Calendar.HOUR_OF_DAY));
        fromCalendar.set(Calendar.MINUTE, fromCalendar.getMinimum(Calendar.MINUTE));
        fromCalendar.set(Calendar.SECOND, fromCalendar.getMinimum(Calendar.SECOND));
        fromCalendar.set(Calendar.MILLISECOND, fromCalendar.getMinimum(Calendar.MILLISECOND));

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, fromCalendar.getMinimum(Calendar.HOUR_OF_DAY));
        toCalendar.set(Calendar.MINUTE, fromCalendar.getMinimum(Calendar.MINUTE));
        toCalendar.set(Calendar.SECOND, fromCalendar.getMinimum(Calendar.SECOND));
        toCalendar.set(Calendar.MILLISECOND, fromCalendar.getMinimum(Calendar.MILLISECOND));

        long dayDistance = (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / ((1000 * 60 * 60 *24));
//        dayDistance = Math.abs(dayDistance);
        return dayDistance;
    }
    public static long stringTimeToLong(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt2 = null;
        try {
            dt2 = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long lTime = dt2.getTime() / 1000;
        return lTime;
    }

    public static String getRightTime(String str) {

        if (!TextUtils.isEmpty(str)) {
            if (str.equals("0001-01-01T00:00:00")) {
                str = "";
            } else {
                str = str.replace("T", " ");
            }
        }

        return str;
    }
}

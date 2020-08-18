package com.huantai.qhytims.tools;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.huantai.qhytims.R;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    public interface SelecteTimeCallBack {
        void callBack();
    }
    public static String addDay(String dayStr, int dayNum){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(dayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.DATE, dayNum);
        Date date1 = cal.getTime();
        return df.format(date1);
    }
    public static String getDayStr(String dayStr){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(dayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");

        return sdf.format(nowDate);
    }

    /**
     *
     * @param mStr yyyy-MM
     * @return
     */
    public static Date mStrToDate(String mStr){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        Date nowDate = null;
        try {
            nowDate = df.parse(mStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowDate;
    }
    /**
     *
     * @param mStr yyyy-MM-dd
     * @return
     */
    public static Date dStrToDate(String mStr){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(mStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowDate;
    }
    public static String addMonth(String monthStr, int dayMonth){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        Date nowDate = null;
        try {
            nowDate = df.parse(monthStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.MONTH, dayMonth);
        Date date1 = cal.getTime();
        return df.format(date1);
    }

    public static String addMonthUtil(String monthStr, int dayMonth){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(monthStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.MONTH, dayMonth);
        Date date1 = cal.getTime();
        return df.format(date1);
    }
    public static long getTimeDistance(String begin, String end) {
        Date beginDate = null, endDate = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            beginDate = df.parse(begin);
            endDate = df.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(beginDate);
//        fromCalendar.set(Calendar.HOUR_OF_DAY, fromCalendar.getMinimum(Calendar.HOUR_OF_DAY));
//        fromCalendar.set(Calendar.MINUTE, fromCalendar.getMinimum(Calendar.MINUTE));
//        fromCalendar.set(Calendar.SECOND, fromCalendar.getMinimum(Calendar.SECOND));
//        fromCalendar.set(Calendar.MILLISECOND, fromCalendar.getMinimum(Calendar.MILLISECOND));

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
//        toCalendar.set(Calendar.HOUR_OF_DAY, fromCalendar.getMinimum(Calendar.HOUR_OF_DAY));
//        toCalendar.set(Calendar.MINUTE, fromCalendar.getMinimum(Calendar.MINUTE));
//        toCalendar.set(Calendar.SECOND, fromCalendar.getMinimum(Calendar.SECOND));
//        toCalendar.set(Calendar.MILLISECOND, fromCalendar.getMinimum(Calendar.MILLISECOND));

        long dayDistance = (toCalendar.getTimeInMillis() - fromCalendar.getTimeInMillis()) /(1000*3600*24);
//        dayDistance = Math.abs(dayDistance);

        return dayDistance;
    }


    public static String getFirstDayOfMonth(Date date){//int year,int month
        Calendar cal = Calendar.getInstance();
        //设置年份
//        cal.set(Calendar.YEAR,year);
//        //设置月份
//        cal.set(Calendar.MONTH, month-1);
        cal.setTime(date);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }
    public static String getFirstDayOfBackMonth(){//int year,int month
        Calendar cal = Calendar.getInstance();
        //设置年份
//        cal.set(Calendar.YEAR,year);
//        //设置月份
//        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }
    /**
     * 获得该月最后一天
     * @return
     */
    public static String getLastDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //设置年份
//        cal.set(Calendar.YEAR,year);
//        //设置月份
//        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    public static String getLastDayOfBackMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //设置年份
//        cal.set(Calendar.YEAR,year);
//        //设置月份
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 获取月
     * @return
     */
    public static int getMonth(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.MONTH)+1;
    }
    public static String getYear(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.YEAR)+"";
    }
    public static boolean isGreaterThanMonth(String monthStr){
        int month = getMonth();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        Date nowDate = null;
        try {
            nowDate = df.parse(monthStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int m = cal.get(Calendar.MONTH)+1;
        if(m>=month){
            return true;
        }
        return false;
    }

    public static boolean isGreaterThanDay(String monthStr){
        long num = getTimeDistance(Util.getCurrentDate(),monthStr);
        if(num>=0){
            return true;
        }
        return false;
    }


    /**
     * 根据提供的年月日获取该月份的第一天
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:26:57
     * @param year
     * @param monthOfYear
     * @return
     */
    public static Date getSupportBeginDayofMonth(int year, int monthOfYear) {
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();
        return firstDate;
    }

    /**
     * 根据提供的年月获取该月份的最后一天
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:29:38
     * @param year
     * @param monthOfYear
     * @return
     */
    public static Date getSupportEndDayofMonth(int year, int monthOfYear) {
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();
        return lastDate;
    }
    public static int[] getIntsYMD(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        return new int[]{year,month,day};
    }

    /**
     * 弹出时间选择器设置日期
     *
     * @param context
     * @param date
     */
    public static void selectDate(Context context, final TextView date) {
        // TODO Auto-generated method stub
        Calendar cd = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                int m = monthOfYear + 1;
                String month = null;
                String day = null;

                if (m < 10) {
                    month = "0" + m;
                } else {
                    month = m + "";
                }

                if (dayOfMonth < 10) {
                    day = "0" + dayOfMonth;
                } else {
                    day = dayOfMonth + "";
                }

                date.setText(year + "-" + month + "-" + day);
            }
        }, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH)).show();
    }
    public static void selectDate(Context context, final TextView date, final SelecteTimeCallBack callBack) {
        // TODO Auto-generated method stub
        Calendar cd = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                int m = monthOfYear + 1;
                String month = null;
                String day = null;

                if (m < 10) {
                    month = "0" + m;
                } else {
                    month = m + "";
                }

                if (dayOfMonth < 10) {
                    day = "0" + dayOfMonth;
                } else {
                    day = dayOfMonth + "";
                }
                date.setText(year + "-" + month + "-" + day);
                if (callBack != null) {
                    callBack.callBack();
                }
            }
        }, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 获取一周之前的时间
     *
     * @return
     */
    public static String getWeekAgoTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 获取一月之前的时间
     *
     * @return
     */
    public static String getMonthAgoTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 获取一年之前的时间
     *
     * @return
     */
    public static String getYearAgoTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }


    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 设置时区
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:"+unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }
    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr =  "0" + Integer.toString(i);//"0" +
        else
            retStr = "" + i;
        return retStr;
    }

    public static void selectYM(Context context, final TextView tvTime){
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();

        startDate.set(2016,0,1);
//        endDate.set(2020,11,31);

        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvTime.setText(getTimeYM(date));
            }
        })
                .setType(new boolean[]{true, true, false, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("请选择")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(context.getResources().getColor(R.color.text_black))//标题文字颜色
                .setSubmitColor(context.getResources().getColor(R.color.tx_blue))//确定按钮文字颜色
                .setCancelColor(context.getResources().getColor(R.color.text_normal))//取消按钮文字颜色
                .setTitleBgColor(context.getResources().getColor(R.color.white))//标题背景颜色 Night mode
                .setBgColor(context.getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }


    public static void selectYMD(Context context, final TextView tvTime){
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();

        startDate.set(2016,0,1);
//        endDate.set(2020,11,31);

        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvTime.setText(getTimeYM(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("请选择")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(context.getResources().getColor(R.color.text_black))//标题文字颜色
                .setSubmitColor(context.getResources().getColor(R.color.tx_blue))//确定按钮文字颜色
                .setCancelColor(context.getResources().getColor(R.color.text_normal))//取消按钮文字颜色
                .setTitleBgColor(context.getResources().getColor(R.color.white))//标题背景颜色 Night mode
                .setBgColor(context.getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }
    private static String getTimeYM(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
       return format.format(date);

    }
}

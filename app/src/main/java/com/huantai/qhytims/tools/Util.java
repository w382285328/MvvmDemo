package com.huantai.qhytims.tools;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;


import com.huantai.qhytims.R;
import com.huantai.qhytims.application.MyApplication;
import com.huantai.qhytims.view.CustomDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.TELEPHONY_SERVICE;

public class Util {
    private static ProgressDialog progressDialog;
    public static String getCurrentDate() {
        String time = getDateFormat().format(System.currentTimeMillis());
        return time;
    }
    public static String getCurrentTime() {
        String time = getTimeFormat().format(System.currentTimeMillis());
        return time;
    }

    public static String getTime(long t) {
        String time = getTimeFormat().format(t);
        return time;
    }

    // 获得系统时间
    public static String getSystemDate() {
        String time = getDateFormat().format(System.currentTimeMillis());
        return time;
    }

    /**
     * 时间格式化
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat getTimeFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 设置时区
        return sdf;
    }

    /**
     * 时间格式化
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat getDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 设置时区
        return sdf;
    }

    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat getTimeNoDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));// 设置时区
        return sdf;
    }

    public static boolean isNumber(String value) {

        if (TextUtils.isEmpty(value)) {
            return false;
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(value);
        if (isNum.matches()) {
            return true;
        }

        return false;
    }

    /**
     * 是否联网
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 验证数据 不能有特殊字符 有特殊字符返回false
     */
    public static boolean isNoSpecialData(String str) {
        boolean flag = false;
        if (str.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]+$")) {
            flag = true;
        }
        return flag;
    }





    public static boolean isIdCard(String idCard) {
        String regex = "^\\d{17}([0-9]|X|x)$";
        return Pattern.matches(regex, idCard);
    }

    public static boolean isMobile(String mobile) {

//		String regex = "(\\+\\d+)?1[34578]\\d{9}$";
//		return Pattern.matches(regex, mobile);
        if (!mobile.startsWith("1")) {
            return false;
        }
        if (mobile.length() != 11) {
            return false;
        }
        return true;
    }

    /**
     * 验证数据 不能有特殊字符 有特殊字符返回false
     */
    public static boolean isLetter(String str) {
        boolean flag = false;
        if (str.matches("^[A-Za-z]+$")) {
            flag = true;
        }
        return flag;
    }

    private static Toast toast = null;

    /**
     * Toast 提示
     */
    public static void showTextToast(String msg, Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static void showTextToast(String msg) {
        showTextToast(msg, MyApplication.getInstance().getApplicationContext());
    }

    public static void showTextToast(int msg, Context context) {
        String info = context.getResources().getString(msg);
        if (toast == null) {
            toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
        } else {
            toast.setText(info);
        }
        toast.show();
    }
    /**
     * 显示进度框
     */
    public static ProgressDialog showProgressDialog(String str, Context context) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                return progressDialog;
            }
            // if (progressDialog == null)
            progressDialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.circle_progressbar_style));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(str);
            progressDialog.show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return progressDialog;
    }

    /**
     * 隐藏进度框
     */
    public static void dissmissProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                System.out.println("dismiss dialog");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static Dialog qqDialog;

    public static void showDialog(Context context, String str, DialogInterface.OnClickListener listenleft) {
        qqDialog = new CustomDialog.Builder(context)//(this, AlertDialog.THEME_HOLO_DARK)
                .setTitle("提示")
                .setMessage(str)
                .setPositiveButton("确定", listenleft)
                .create();
        qqDialog.show();
    }

    public static void showDialog(Context context, String str, DialogInterface.OnClickListener listenleft, DialogInterface.OnClickListener rightListen) {
        qqDialog = new CustomDialog.Builder(context)//(this, AlertDialog.THEME_HOLO_DARK)
                .setTitle("提示")
                .setMessage(str)
                .setPositiveButton("确认", listenleft)
                .setNegativeButton("取消", rightListen)
                .create();
        qqDialog.show();
    }


    public static Bitmap getBitmapByName(Context context, String name) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        int resID = context.getResources().getIdentifier(name, "mipmap", appInfo.packageName);
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }


    @SuppressLint("MissingPermission")
    public static String getAndroidId(Context context) {
        String szImei = "";
        try {
            TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            szImei = TelephonyMgr.getDeviceId();
            if (TextUtils.isEmpty(szImei)) {
                szImei = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return szImei;
    }

    private static final String[][] MIME_MapTable = {
            //{后缀名，    MIME类型}
            {".xlsx", "application/vnd.ms-excel"},
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".prop", "text/plain"},
            {".rar", "application/x-rar-compressed"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            //{".xml",    "text/xml"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/zip"},
            {"", "*/*"}
    };

    public static String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0)
            return type;
        /* 获取文件的后缀名 */
        String fileType = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (fileType == null || "".equals(fileType))
            return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (fileType.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }
}

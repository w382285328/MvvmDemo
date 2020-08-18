package com.huantai.qhytims.tools;

import android.content.Context;
import android.os.Environment;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by wk on 2018/10/18.
 */

public class Common {
    public static final String PATH_FOLDER = Environment.getExternalStorageDirectory()+"/2020ERP/";
    public static String getPhotoFilePath(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = sdf.format(System.currentTimeMillis());
        return PATH_FOLDER + time +"PHOTO.jpg";
    }
}

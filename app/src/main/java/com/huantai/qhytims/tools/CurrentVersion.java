package com.huantai.qhytims.tools;

import android.content.Context;
import android.util.Log;

public class CurrentVersion {
	private static final String TAG = "Config";
	public static String appPackName ;
	public static Context context;

	public CurrentVersion(Context context){
		appPackName = context.getPackageName();
		this.context = context;
	}

	public static int getVerCode(){
		int verCode = -1;
		try{
			verCode = context.getPackageManager().getPackageInfo(appPackName, 0).versionCode;
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}
		return verCode;
	}
	public static String getVerName(){
		String verName = "";
		try{
			verName = context.getPackageManager().getPackageInfo(appPackName, 0).versionName;
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}
		return verName;
	}
}

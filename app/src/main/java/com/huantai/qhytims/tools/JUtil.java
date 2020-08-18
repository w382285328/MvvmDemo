package com.huantai.qhytims.tools;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JUtil {
    public static String getJsonString(JSONObject json, String key){
        if(json!=null && json.has(key)){
            try {
                String str = json.getString(key);
                if(isNull(str)){
                    str = "";
                }
                return str;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    public static boolean isNull(String str){
        if(TextUtils.isEmpty(str) || str.equals("null")){
            return true;
        }
        return false;
    }
    public static JSONArray getJsw(JSONObject json, String key){
        if(json!=null && json.has(key)){
            try {
                String str = json.get(key)+"";
                if(isNull(str)){
                    return new JSONArray();
                }else{
                    return json.getJSONArray(key);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new JSONArray();

    }
}

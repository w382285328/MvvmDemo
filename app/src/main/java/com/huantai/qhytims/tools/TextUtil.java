package com.huantai.qhytims.tools;

import android.text.Html;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

public class TextUtil {
    public static String getNotNullString(String value) {
        if (value.equals("null") || value == null) {
            value = "";
        }
        return value;
    }

    public static void setTextString(TextView text, String key, String keyColor, String value, String valueColor) {
        if (TextUtils.isEmpty(key) || key.equals("null")) {
            key = "";
        }
        if (TextUtils.isEmpty(value) || value.equals("null")) {
            value = "";
        }
        text.setText(Html.fromHtml("<font  color=\"" + keyColor + "\">" + key + "&ensp;&ensp;</font><font  color=\"" + valueColor + "\">" + value + "</font>"));
    }

    public static void setTextString(TextView text, String str) {
        if (TextUtils.isEmpty(str) || str.equals("null")) {
            text.setText("");
        } else {
            text.setText(str);
        }
    }

    public static void setEditString(EditText text, String str) {
        if (TextUtils.isEmpty(str) || str.equals("null")) {
            text.setText("");
        } else {
            text.setText(str);
        }
    }

    public static void setItemText(TextView text, String key, String value) {
        TextUtil.setTextString(text, key, "#888888", value, "#333333");
    }

    public static void setItemRedText(TextView text, String key, String value) {
        TextUtil.setTextString(text, key, "#888888", value, "#EA4F39");
    }

    public static void setItemBlueText(TextView text, String key, String value) {
        TextUtil.setTextString(text, key, "#888888", value, "#0AAF89");
    }

    public static void setItemBlackText(TextView text, String key, String value) {
        TextUtil.setTextString(text, key, "#888888", value, "#333333");
    }
}

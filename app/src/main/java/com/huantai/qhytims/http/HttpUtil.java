package com.huantai.qhytims.http;

import android.content.Context;
import android.text.TextUtils;

import com.huantai.qhytims.application.MyApplication;
import com.huantai.qhytims.sharepfs.Sharepf;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Route;


public class HttpUtil {
    /**
     * get
     */
    public static void get(String url, HttpCallBack mHttpCallBack){
        get(url,MyApplication.getInstance().getApplicationContext(),null,mHttpCallBack);
    }
    public static void get(String url, HttpParams params, HttpCallBack mHttpCallBack) {
        get(url, MyApplication.getInstance().getApplicationContext(), params, mHttpCallBack);
    }
    public static void get(String url, Context mContext, HttpParams params,HttpCallBack mHttpCallBack) {

        OkGo.<String>get(getUrl(url))
                .params(params)
                .client(buildBasicAuthClient(mContext))
                .execute(new HttpResponse(mHttpCallBack));
    }

    /**
     * 不需要身份验证
     */
    public static void getNoAuth(String url, HttpParams params, HttpCallBack mHttpCallBack) {
        OkGo.<String>get(url)
                .params(params)
                .execute(new HttpResponse(mHttpCallBack));
    }

    /**
     * post 请求相关
     */
    public static void post(String url, final Context mContext, HttpParams params, final HttpCallBack mHttpCallBack) {
        OkGo.<String>post(getUrl(url))
                .params(params)
                .client(buildBasicAuthClient(mContext))
                .execute(new HttpResponse(mHttpCallBack));
    }
    public static void postNoReplace(String url, final Context mContext, HttpParams params, final HttpCallBack mHttpCallBack) {
        OkGo.<String>post(url)
                .params(params)
//                .client(buildBasicAuthClient(mContext))
                .execute(new HttpResponse(mHttpCallBack));
    }

    public static void postJson(String url, final Context mContext, String json, final HttpCallBack mHttpCallBack) {
        OkGo.<String>post(getUrl(url))
                .upJson(json)
                .client(buildBasicAuthClient(mContext))
                .execute(new HttpResponse(mHttpCallBack));
    }

    public static void post(String url, HttpParams params, final HttpCallBack mHttpCallBack) {
        OkGo.<String>post(getUrl(url))
                .params(params)
                .client(buildBasicAuthClient(MyApplication.getInstance().getApplicationContext()))
                .execute(new HttpResponse(mHttpCallBack));
    }

    public static void postFile(String url, File file, HttpParams params, final HttpCallBack mHttpCallBack) {
//        if(!file.exists()){
//            return;
//        }
        OkGo.<String>post(getUrl(url))
                .upFile(file)
                .params(params)
                .client(buildBasicAuthClient(MyApplication.getInstance().getApplicationContext()))
                .execute(new HttpResponse(mHttpCallBack));
    }

    public static void post(String url, List<String> images, HttpParams params, final HttpCallBack mHttpCallBack) {
        List<File> all = new ArrayList<>();
        if (images != null && images.size() > 0) {
            for (int i = 0; i < images.size(); i++) {
                if (!TextUtils.isEmpty(images.get(i))) {
                    all.add(new File(images.get(i)));
                }

            }
        }

        OkGo.<String>post(getUrl(url)).isMultipart(true)
                .params(params)
                .client(buildBasicAuthClient(MyApplication.getInstance().getApplicationContext()))
                .addFileParams("File", all)
                .execute(new HttpResponse(mHttpCallBack));
    }
    /**
     * 动态修改URL
     * @param str
     * @return
     */
    public static String getUrl(String str) {
//        Sharepf sharepf = new Sharepf(MyApplication.getInstance().getApplicationContext());
//        return sharepf.getBaseIp() + str;
        return str;
    }
    /**
     * 验证
     * @param context
     * @return
     */
    public static OkHttpClient buildBasicAuthClient(Context context) {

        final Sharepf sharepf = new Sharepf(context);

        return new OkHttpClient.Builder().authenticator(new Authenticator() {
            @Nullable
            @Override
            public okhttp3.Request authenticate(Route route, okhttp3.Response response) throws IOException {
                String credential = Credentials.basic(sharepf.readUser().getName(), sharepf.readUser().getPsw());
//                String credential = Credentials.basic("admin", "1");

                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();
    }
}

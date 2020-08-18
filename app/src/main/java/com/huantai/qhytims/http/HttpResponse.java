package com.huantai.qhytims.http;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;

public
class HttpResponse extends StringCallback {
    private HttpCallBack mHttpCallBack;
    private HttpState httpState = new HttpState();


    public HttpResponse(@NonNull HttpCallBack mHttpCallBack) {
        this.mHttpCallBack = mHttpCallBack;
    }

    @Override
    public void onSuccess(Response<String> response) {
        httpState.setState(HttpState.HTTP_AFTER);
        Object object = response.body();
        ReturnValue value = getReturnValue(object.toString());
        httpState.setReturnValue(value);
        mHttpCallBack.onFinish(httpState);
    }
    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        httpState.setState(HttpState.HTTP_ERROR);
        if (response != null) {
            httpState.setErrCode(response.code());
            httpState.setErrMsg(response.getException().getMessage());
        } else {
            httpState.setErrCode(-1);
            httpState.setErrMsg("未知错误！");
        }

        mHttpCallBack.httpErr(httpState);
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        httpState.setState(HttpState.HTTP_BEFORE);
        mHttpCallBack.onBefore(httpState);
    }

    public static ReturnValue getReturnValue(String str) {
        ReturnValue value = new ReturnValue();

        try {
            JSONObject object = new JSONObject(str);
            if (object.has("status")) {
                value.setResult(object.getBoolean("status"));
            }else {
                value.setResult(true);
                value.setJson(str);
            }
            if (object.has("data")) {
                value.setJson(object.getString("data"));
            }
            if (object.has("errmsg")) {
                value.setErrMsg(object.getString("errmsg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            value.setResult(true);
            value.setJson(str);
        }

        return value;
    }
}

package com.huantai.qhytims.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public
class HttpCallVmUtil implements HttpCallBack {
    private @NonNull MutableLiveData<HttpState> liveData;

    public HttpCallVmUtil(@NonNull MutableLiveData<HttpState> liveData) {
        this.liveData = liveData;
    }

    @Override
    public void onBefore(HttpState state) {
        liveData.setValue(state);
    }

    @Override
    public void onFinish(HttpState state) {
        liveData.setValue(state);
    }

    @Override
    public void httpErr(HttpState state) {
        liveData.setValue(state);
    }
}

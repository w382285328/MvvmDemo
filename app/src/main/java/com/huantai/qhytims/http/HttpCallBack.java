package com.huantai.qhytims.http;


public
interface HttpCallBack {
    void onBefore(HttpState state);

    void onFinish(HttpState state);

    void httpErr(HttpState state);
}

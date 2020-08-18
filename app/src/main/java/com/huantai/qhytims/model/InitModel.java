package com.huantai.qhytims.model;

import com.huantai.qhytims.http.HttpCallBack;
import com.huantai.qhytims.http.HttpUtil;
import com.huantai.qhytims.http.URLConfig;

public
class InitModel {
    public void checkAppHttp(HttpCallBack callBack){
        HttpUtil.get(URLConfig.GET_VERSION,callBack);

    }
}

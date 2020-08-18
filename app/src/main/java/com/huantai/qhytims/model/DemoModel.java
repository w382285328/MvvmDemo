package com.huantai.qhytims.model;

import com.huantai.qhytims.http.HttpCallBack;
import com.huantai.qhytims.http.HttpUtil;

public
class DemoModel {
    public void getDemo(HttpCallBack callBack){
        HttpUtil.get("https://www.baidu.com",callBack);
    }
}

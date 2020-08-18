package com.huantai.qhytims.vm;

import com.huantai.qhytims.http.HttpCallVmUtil;
import com.huantai.qhytims.http.HttpState;
import com.huantai.qhytims.model.DemoModel;
import com.huantai.qhytims.model.InitModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public
class InitViewModel extends ViewModel {
    public MutableLiveData<HttpState> checkApphttpState= new MutableLiveData<>();
    InitModel model = new InitModel();
    public void checkAppHttp(){
        model.checkAppHttp(new HttpCallVmUtil(checkApphttpState));
    }

}

package com.huantai.qhytims.vm;

import com.huantai.qhytims.http.HttpCallVmUtil;
import com.huantai.qhytims.http.HttpState;
import com.huantai.qhytims.model.DemoModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public
class DemoViewModel extends ViewModel {
    public MutableLiveData<HttpState> httpState= new MutableLiveData<>();
    DemoModel model = new DemoModel();
    public void getData(){
        model.getDemo(new HttpCallVmUtil(httpState));
    }

}

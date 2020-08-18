package com.huantai.qhytims.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.huantai.qhytims.R;
import com.huantai.qhytims.base.BaseActivity;
import com.huantai.qhytims.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        activityMainBinding   = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(activityMainBinding.getRoot());
        initLayoutGetDataState(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setErrAndNodataLayoutVisable(LOADING);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setErrAndNodataLayoutVisable(DATA_HAS);
            }
        },2000);

    }
}
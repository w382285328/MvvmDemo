package com.huantai.qhytims.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.huantai.qhytims.adapter.DemoAdapter;
import com.huantai.qhytims.base.BaseActivity;
import com.huantai.qhytims.bean.PopSelectInfo;
import com.huantai.qhytims.databinding.ActivityMainBinding;
import com.huantai.qhytims.databinding.DemoBinding;
import com.huantai.qhytims.http.HttpState;
import com.huantai.qhytims.vm.DemoViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class DemoActivity extends BaseActivity {

    DemoBinding build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        build = DemoBinding.inflate(LayoutInflater.from(this));
        setContentView(build.getRoot());
        initTitleBar("测试");
        build.tablayout.addTab(build.tablayout.newTab().setText("ceshi1"));
        build.tablayout.addTab(build.tablayout.newTab().setText("ceshi2"));
        build.tablayout.addTab(build.tablayout.newTab().setText("ceshi3"));

        final DemoViewModel viewModel = new ViewModelProvider(this).get(DemoViewModel.class);
        viewModel.httpState.observe(this, new Observer<HttpState>() {
            @Override
            public void onChanged(HttpState httpState) {
                if (httpState.getState() == HttpState.HTTP_BEFORE) {
                    showProgressDialog("正在加载...");
                } else if (httpState.getState() == HttpState.HTTP_ERROR) {
                    dissmissProgressDialog();
                    showToast(httpState.getErrMsg());
                } else if (httpState.getState() == HttpState.HTTP_AFTER) {
                    dissmissProgressDialog();
                }
            }
        });

        build.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getData();
            }
        });
    }
}
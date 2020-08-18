package com.huantai.qhytims.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.huantai.qhytims.R;
import com.huantai.qhytims.base.BaseActivity;
import com.huantai.qhytims.bean.User;
import com.huantai.qhytims.databinding.ActivityLoginBinding;
import com.huantai.qhytims.tools.Util;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding build;
    int REQUEST_EXTERNAL_STORAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        build = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(build.getRoot());
        init();
        setListen();
        checkPermission();
    }

    private void setListen() {
        build.btnLogin.setOnClickListener(listen);
    }

    private void init() {
        User user = sharepf.readUser();
        if (user != null) {
            build.etPhone.setText(user.getName());
            build.etPsw.setText(user.getPsw());
        }
    }



    private View.OnClickListener listen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    login();
                    break;
            }
        }
    };

    private void login() {
        if(!cansub()){
            return;
        }
    }

    private boolean cansub() {
        if (TextUtils.isEmpty(build.etPhone.getText().toString())) {
            showToast("请输入用户名！");
            return false;
        }

        if (TextUtils.isEmpty(build.etPsw.getText().toString())) {
            showToast("请输入密码！");
            return false;
        }
        return true;
    }
    @Override
    protected void doOnBackPressed() {
        if (System.currentTimeMillis() - backTime < 2 * 1000) {
            exit();
        } else {
            Util.showTextToast("Press the return key again to exit", this);
        }
        backTime = System.currentTimeMillis();
    }
    private long backTime = 0;
    public void exit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {

        }
    }

    private String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };


    /**
     * 检查是否打开读取手机状态的权限
     */
    private void checkPermission() {

        if (!permissionArrayGranted()) {
            requestPermissions(REQUEST_EXTERNAL_STORAGE);
        }
    }

    private void requestPermissions(int request) {
        ActivityCompat.requestPermissions(this, PERMISSIONS, request);
    }


    private boolean permissionArrayGranted() {
        String[] permissionArray = PERMISSIONS;
        boolean granted = true;
        for (String per : permissionArray) {
            if (!permissionGranted(per)) {
                granted = false;
                break;
            }
        }
        return granted;
    }

    private boolean permissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(
                this, permission) == PackageManager.PERMISSION_GRANTED;
    }
}

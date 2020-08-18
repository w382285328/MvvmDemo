package com.huantai.qhytims.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.huantai.qhytims.R;
import com.huantai.qhytims.base.BaseActivity;
import com.huantai.qhytims.http.HttpCallBack;
import com.huantai.qhytims.http.HttpState;
import com.huantai.qhytims.http.HttpUtil;
import com.huantai.qhytims.http.ReturnValue;
import com.huantai.qhytims.model.InitModel;
import com.huantai.qhytims.sharepfs.Sharepf;
import com.huantai.qhytims.tools.CurrentVersion;
import com.huantai.qhytims.tools.FileUtil;
import com.huantai.qhytims.tools.Util;
import com.huantai.qhytims.view.AppUpdateProgressDialog;
import com.huantai.qhytims.vm.InitViewModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;


public class InitActivity extends BaseActivity {
    Sharepf sharepf;
    private String updateURL;
    private int LOGIN_RESULT_CODE = 1001;
    private AppUpdateProgressDialog dialog = null;
    InitViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        init();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(InitActivity.this, DemoActivity.class));
//                finish();
//            }
//        },2000);

    }

    private void init() {
        QMUIStatusBarHelper.translucent(this);
        viewModel = getViewModel(InitViewModel.class);
        viewModel.checkApphttpState.observe(this, new Observer<HttpState>() {
            @Override
            public void onChanged(HttpState httpState) {
                if (httpState.getState() == HttpState.HTTP_BEFORE) {
                } else if (httpState.getState() == HttpState.HTTP_ERROR) {
                    showToast(httpState.getErrMsg());
                    finish();
                } else if (httpState.getState() == HttpState.HTTP_AFTER) {
                    dissmissProgressDialog();
                    checkAppAnalysis(httpState.getReturnValue());
                }
            }
        });
        viewModel.checkAppHttp();
    }
    private void showUpdateDialog() {
        dialog = new AppUpdateProgressDialog(InitActivity.this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.startDownload();
                checkPermission();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
                System.exit(0);
            }
        });
        dialog.show();
    }

    /**
     * 检查是否打开读写文件的权限
     */
    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
        } else {
            if (Build.VERSION.SDK_INT >= 26) {
                boolean b = getPackageManager().canRequestPackageInstalls();
                if (!b) {
                    //请求安装未知应用来源的权限
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, 1002);
                    Uri packageURI = Uri.parse("package:"+getPackageName());
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
                    startActivityForResult(intent, 1002);
                }else {
                    downLoadApk();
                }
            }else {
                downLoadApk();
            }
        }
    }

    private void downLoadApk() {
        OkGo.<File>get(updateURL)
                .execute(new FileCallback(FileUtil.setMkdir(InitActivity.this),FileUtil.getFileName(updateURL)) {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<File> response) {
//                        try {
                            openFile();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        //这里回调下载进度(该回调在主线程,可以直接更新ui)
                        int value = (int) (progress.fraction*100); //(int) (progress.currentSize/progress.totalSize)
                        dialog.setProgress(value);
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<File> response) {
                        super.onError(response);
                        if(response != null){
                            Util.showTextToast(response.message()+response.code(),InitActivity.this);
                        }else {
                            Util.showTextToast("网络异常！",InitActivity.this);
                        }
                    }
                });
    }

    private void openFile() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        installApk();
    }

    private void installApk(){
        if (FileUtil.getFileType(updateURL).equals("apk")) {
            String str = "/myfile/" + FileUtil.getFileName(updateURL);
            String fileName = Environment.getExternalStorageDirectory() + str;
            File file = new File(fileName);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if(Build.VERSION.SDK_INT>=24) { //判读版本是否在7.0以上
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(InitActivity.this,"com.huantai.qhytims.fileProvider", file);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            }else{
                intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            }
            startActivityForResult(intent, LOGIN_RESULT_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1001://申请读写文件权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        boolean b = getPackageManager().canRequestPackageInstalls();
                        if (!b) {
                            //请求安装未知应用来源的权限
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, 1002);
                            Uri packageURI = Uri.parse("package:"+getPackageName());
                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
                            startActivityForResult(intent, 1002);
                        }else {
                            downLoadApk();
                        }
                    }else {
                        downLoadApk();
                    }
                }
                break;
            case 1002: //安装未知应用来源的权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    installApk();
                }
                break;
        }
    }

    public boolean checkToUpdate(String value) {
        boolean rs = false;
        try {
            if(TextUtils.isEmpty(value)){
                value = "0";
            }
            int  version = new CurrentVersion(this).getVerCode();
            if (Integer.valueOf(value) > version) {
                rs = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    private void checkAppAnalysis(ReturnValue value){
        if (value.isResult()) {
            String str = value.getJson();
            try {
                JSONObject js = new JSONObject(str);
                String verCode = js.get("VerCode").toString();
                updateURL = js.getString("UpdateURL");
                if (false) {//checkToUpdate(verCode)
                    showUpdateDialog();
                } else {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                                    if (sharepf.getIsLogin()) {
//                                        startActivity(new Intent(InitActivity.this, MainActivity.class));
//                                        finish();
//                                        return;
//                                    }
                            Intent intent = new Intent(InitActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },1000);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            Util.showTextToast(value.getErrMsg());
            finish();
        }
    }

    @SuppressWarnings("static-access")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == InitActivity.this.RESULT_CANCELED) {
            if (LOGIN_RESULT_CODE == requestCode) {

            }else {
                Util.showTextToast("用户取消了安装", InitActivity.this);
                finish();
                System.exit(0);
            }
        }

        if(resultCode == RESULT_OK && requestCode == 1002){
            downLoadApk();
        }
    }

}

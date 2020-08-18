package com.huantai.qhytims.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huantai.qhytims.R;
import com.huantai.qhytims.sharepfs.Sharepf;
import com.huantai.qhytims.tools.StatusBatUtil;
import com.huantai.qhytims.tools.Util;
import com.huantai.qhytims.view.CustomDialog;
import com.qmuiteam.qmui.arch.QMUIActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import androidx.annotation.CallSuper;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class BaseActivity extends QMUIActivity implements LifecycleOwner {
    /**
     * 0 = 加载中，1= 网络异常 ，2= 没有数据 ， 3 = 有数据 , 4 数据异常
     */
    CustomDialog qqDialog;
    public static final int DATA_ERROR = 4;
    public static final int DATA_HAS = 3;
    public static final int DATA_NO = 2;
    public static final int HTTP_ERROR = 1;
    public static final int LOADING = 0;//加载中

    private LinearLayout layout_no_data, layout_internet_err, layout_loading;
    private RelativeLayout layout_all_net;
    private Button btn_neterr;
    public TextView tx_no_data, tx_top_title, tx_right;
    public LinearLayout layout_back;
    public Sharepf sharepf;
    public TextView btnRight;
    public ImageView btnSearch;

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        StatusBatUtil.setWindowStatusBarColor(this,R.color.white);
//        QMUIStatusBarHelper.translucent(this);
        sharepf = new Sharepf(this);
    }

    public void initTitleBar(String title) {
        layout_back =  findViewById(R.id.layout_back);
        tx_top_title = findViewById(R.id.tx_top_title);
        btnRight = findViewById(R.id.btnRight);
        btnSearch = findViewById(R.id.btnSearch);
        tx_top_title.setText(title);
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public TextView initTitleRightBtn(String str, View.OnClickListener listen){
        tx_right = findViewById(R.id.btnRight);
        tx_right.setVisibility(View.VISIBLE);
        tx_right.setOnClickListener(listen);
        tx_right.setText(str);
        return tx_right;
    }

    public void initLayoutGetDataState(View.OnClickListener refreshListen) {
        tx_no_data = findViewById(R.id.tx_no_data);
        btn_neterr = findViewById(R.id.btn_neterr);
        layout_internet_err = findViewById(R.id.layout_internet_err);
        layout_no_data = findViewById(R.id.layout_no_data);
        layout_loading = findViewById(R.id.layout_loading);
        layout_all_net = findViewById(R.id.layout_all_net);
        btn_neterr.setOnClickListener(refreshListen);
        setErrAndNodataLayoutVisable(LOADING);
    }

    public void showToast(String msg) {
        Util.showTextToast(msg, this);
    }

    public void showToast(int id) {
        Util.showTextToast(id, this);
    }

    /**
     * 显示进度框
     */
    public ProgressDialog showProgressDialog(String str) {
        return Util.showProgressDialog(str,this);
    }

    /**
     * 隐藏进度框
     */
    public void dissmissProgressDialog() {
        Util.dissmissProgressDialog();
    }

    public String getResString(int id) {
        return getResources().getString(id);
    }

    /**
     * 0 = 加载中，1= 网络异常 ，2= 没有数据 ， 3 = 有数据 , 4 = 数据异常
     *
     * @param a
     */
    public void setErrAndNodataLayoutVisable(int a) {
        layout_all_net.setVisibility(View.VISIBLE);
        switch (a) {
            case 0:
                layout_loading.setVisibility(View.VISIBLE);
                layout_internet_err.setVisibility(View.GONE);
                layout_no_data.setVisibility(View.GONE);
                break;
            case 1:
                layout_loading.setVisibility(View.GONE);
                layout_internet_err.setVisibility(View.VISIBLE);
                layout_no_data.setVisibility(View.GONE);
                break;
            case 2:
                layout_loading.setVisibility(View.GONE);
                layout_internet_err.setVisibility(View.GONE);
                layout_no_data.setVisibility(View.VISIBLE);
                tx_no_data.setText(getResources().getString(R.string.string_no_data));
                break;
            case 3:
                layout_loading.setVisibility(View.GONE);
                layout_internet_err.setVisibility(View.GONE);
                layout_no_data.setVisibility(View.GONE);
                layout_all_net.setVisibility(View.GONE);
                break;
            case 4:
                layout_loading.setVisibility(View.GONE);
                layout_internet_err.setVisibility(View.GONE);
                layout_no_data.setVisibility(View.VISIBLE);
                tx_no_data.setText("数据获取异常！");//getResources().getString(R.string.string_data_error)
                break;
            default:
                break;
        }
    }

    public void showDialog(String str, DialogInterface.OnClickListener listen) {
        qqDialog = new CustomDialog.Builder(this)//(this, AlertDialog.THEME_HOLO_DARK)
                .setTitle(getResString(R.string.hint))
                .setMessage(str)
                .setPositiveButton(getResString(R.string.sure), listen)
                .create();
        qqDialog.show();
    }

    public void showDialog(String str, String btnStr, DialogInterface.OnClickListener listen) {
        qqDialog = new CustomDialog.Builder(this)//(this, AlertDialog.THEME_HOLO_DARK)
                .setTitle(getResString(R.string.hint))
                .setMessage(str)
                .setPositiveButton(btnStr, listen)
                .create();
        qqDialog.show();
    }

    public void showDialog(String str, DialogInterface.OnClickListener listenleft, DialogInterface.OnClickListener listenright) {
        qqDialog = new CustomDialog.Builder(this)//(this, AlertDialog.THEME_HOLO_DARK)
                .setTitle(getResString(R.string.hint))
                .setMessage(str)
                .setPositiveButton(getResString(R.string.sure), listenleft)
                .setNegativeButton(getResString(R.string.cancel), listenright)
                .create();
        qqDialog.show();
    }

    public void showYesOrNoDialog(String str, DialogInterface.OnClickListener listenleft, DialogInterface.OnClickListener listenright) {
        qqDialog = new CustomDialog.Builder(this)//(this, AlertDialog.THEME_HOLO_DARK)
                .setTitle(getResString(R.string.hint))
                .setMessage(str)
                .setPositiveButton(getResString(R.string.yes), listenleft)
                .setNegativeButton(getResString(R.string.no), listenright)
                .create();
        qqDialog.show();
    }

    public void exit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    public  <T extends ViewModel> T  getViewModel(Class<T> c){
        return new ViewModelProvider(this).get(c);
    }
}

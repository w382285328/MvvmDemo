package com.huantai.qhytims.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huantai.qhytims.R;
import com.huantai.qhytims.tools.CurrentVersion;


public class AppUpdateProgressDialog extends Dialog {

    private NumberProgressBar numberProgressBar;
    private TextView update_tv,newVersion,later;
    private Context context;
    private Button update;
    private View.OnClickListener updateListener,laterListener;

    public AppUpdateProgressDialog(Context context, View.OnClickListener updateListener, View.OnClickListener laterListener) {
        super(context, R.style.Custom_Progress);
        this.context = context;
        this.updateListener = updateListener;
        this.laterListener = laterListener;
        initLayout();
    }

    public AppUpdateProgressDialog(Context context, int theme) {
        super(context, R.style.Custom_Progress);
        initLayout();
    }

    private void initLayout() {
        this.setContentView(R.layout.update_progress_layout);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        numberProgressBar = (NumberProgressBar) findViewById(R.id.number_progress);
        update_tv = (TextView) findViewById(R.id.update_tv);
        newVersion = (TextView) findViewById(R.id.newVersion);
        later = (TextView) findViewById(R.id.later);
        update = (Button) findViewById(R.id.update);

        this.setCanceledOnTouchOutside(false);//点击dialog背景部分不消失
//        this.setCancelable(false);//dialog出现时，点击back键不消失
        CurrentVersion version = new CurrentVersion(context);
        update_tv.setText(version.getVerName()+"版更新中，请稍等");
        newVersion.setText("新版本 V"+version.getVerName()+" 来啦~");

        update.setOnClickListener(updateListener);

        later.setOnClickListener(laterListener);
    }

    public void setProgress(int mProgress) {
        numberProgressBar.setProgress(mProgress);
    }

    public void startDownload(){
        update_tv.setVisibility(View.VISIBLE);
        numberProgressBar.setVisibility(View.VISIBLE);
        update.setVisibility(View.GONE);
        later.setVisibility(View.GONE);
        newVersion.setVisibility(View.GONE);
    }

}

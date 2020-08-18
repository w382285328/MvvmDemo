package com.huantai.qhytims.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huantai.qhytims.R;
import com.qmuiteam.qmui.arch.QMUIFragment;

import androidx.lifecycle.LifecycleOwner;


public abstract class BaseFragment extends QMUIFragment implements LifecycleOwner {

    public static final int DATA_ERROR = 4;
    public static final int DATA_HAS = 3;
    public static final int DATA_NO = 2;
    public static final int HTTP_ERROR = 1;
    public static final int LOADING = 0;//加载中

    private LinearLayout layout_no_data, layout_internet_err, layout_loading;
    private Button btn_neterr;
    public TextView tx_no_data;
    private RelativeLayout layout_all_net;

    public void initLayoutGetDataState(View parent, View.OnClickListener refreshListen) {
        tx_no_data = parent.findViewById(R.id.tx_no_data);
        btn_neterr = parent.findViewById(R.id.btn_neterr);
        layout_internet_err = parent.findViewById(R.id.layout_internet_err);
        layout_no_data = parent.findViewById(R.id.layout_no_data);
        layout_loading = parent.findViewById(R.id.layout_loading);
        layout_all_net = parent.findViewById(R.id.layout_all_net);
        btn_neterr.setOnClickListener(refreshListen);
        setErrAndNodataLayoutVisable(LOADING);
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
//    public abstract View getPLayout();

}

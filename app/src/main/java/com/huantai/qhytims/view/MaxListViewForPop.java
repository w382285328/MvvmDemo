package com.huantai.qhytims.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ListView;

public class MaxListViewForPop extends ListView {

    private Context mContext;

    public MaxListViewForPop(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        // TODO Auto-generated constructor stub
    }

    public MaxListViewForPop(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        // TODO Auto-generated constructor stub
    }

    public MaxListViewForPop(Context context) {
        super(context);
        mContext = context;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics metrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(metrics);
//        int pixelsHeight = metrics.heightPixels;
//        int widthPixels = metrics.widthPixels;
//        int maxHeight = (int) (pixelsHeight * 0.45);
////        int maxWidth = (int) (widthPixels*0.8);
//        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        if (heightMode == MeasureSpec.UNSPECIFIED) {
//            return;
//        }
//        int height = getMeasuredHeight();
//        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
//        if (height > maxHeight) {
//            setMeasuredDimension(specWidthSize, maxHeight);
//        } else {
//            setMeasuredDimension(specWidthSize, height);
//        }
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        int pixelsHeight = metrics.heightPixels;
        int maxHeight = (int) (pixelsHeight * 0.45);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        //限制高度小于lv高度,设置为限制高度
        if (maxHeight <= specSize && maxHeight > -1) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Float.valueOf(maxHeight).intValue(),
                    MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}

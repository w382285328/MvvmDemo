package com.huantai.qhytims.view;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoScrollListView  extends ListView {
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	  public NoScrollListView(Context context) {
		super(context);
	}


    public NoScrollListView(Context context, AttributeSet attrs){
         super(context, attrs);  
    }  
    
 @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){  
         int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
         super.onMeasure(widthMeasureSpec, mExpandSpec);  
    }  
   

}

package com.huantai.qhytims.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class NoScrollGridView  extends GridView {
	  private Context context;

    public NoScrollGridView(Context context, AttributeSet attrs){
         super(context, attrs);  
         this.context =context;
    }  
 
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){  
         int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
         super.onMeasure(widthMeasureSpec, mExpandSpec);  
    }  
   

}

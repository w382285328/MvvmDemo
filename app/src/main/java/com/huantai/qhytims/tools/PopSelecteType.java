package com.huantai.qhytims.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;


import com.huantai.qhytims.R;
import com.huantai.qhytims.adapter.PopStringAdapter;
import com.huantai.qhytims.bean.PopSelectInfo;

import java.util.ArrayList;
import java.util.List;

public class PopSelecteType {
    ListView list;
	PopStringAdapter adapter;
	private Context context;
	PopupWindow eFilterPop;
	View view;
	List<PopSelectInfo> allPop;
	AdapterView.OnItemClickListener onItemClickListener;
	public PopSelecteType(Context context, View view, AdapterView.OnItemClickListener onItemClickListener) {
		super();
		this.context = context;
		this.view = view;
		this.onItemClickListener = onItemClickListener;
		initEFilterPopWindow();
	}
	public PopSelecteType(Context context, AdapterView.OnItemClickListener onItemClickListener) {
		super();
		this.context = context;
		this.view = ((Activity)context).findViewById(android.R.id.content);
		this.onItemClickListener = onItemClickListener;
		initEFilterPopWindow();
	}
	/**
	 * 显示下拉选择 值会直接附在传进来的TextVIew上
	 */
	public void showPop(List<PopSelectInfo> allPop) {
		this.allPop.clear();
		this.allPop.addAll(allPop);
//		adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

		if (eFilterPop != null && eFilterPop.isShowing()) {
			eFilterPop.dismiss();
			return;
		}
		eFilterPop.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0x40000000);
		eFilterPop.setBackgroundDrawable(dw);
		eFilterPop.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
	}
	public void closePop(){
		if(eFilterPop!=null){
			eFilterPop.dismiss();
		}
	}

	private void initEFilterPopWindow() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.layout_new_popwindow, null);
		list = view.findViewById(R.id.amorpm);
		allPop = new ArrayList<>();
		adapter = new PopStringAdapter(allPop, context);
		list.setAdapter(adapter);
		list.setOnItemClickListener(onItemClickListener);
		eFilterPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
				false);
		eFilterPop.setOutsideTouchable(true);
		eFilterPop.setAnimationStyle(R.style.pop_b_animation2);//PopupAnimation
		eFilterPop.getContentView().setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				eFilterPop.setFocusable(false);
				eFilterPop.dismiss();
				return false;
			}
		});
	}
}

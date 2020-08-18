package com.huantai.qhytims.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;


import com.huantai.qhytims.R;
import com.huantai.qhytims.adapter.MenuAdapter;
import com.huantai.qhytims.base.BaseRecyclerAdapter;
import com.huantai.qhytims.bean.PopSelectInfo;


import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PopMenu {
	RecyclerView list;
	MenuAdapter adapter;
	private Context context;
	PopupWindow eFilterPop;
	View view;
	List<PopSelectInfo> allPop;
	BaseRecyclerAdapter.OnItemClickListener onItemClickListener;
	private int lNum = 3;//一行多少个
	public PopMenu(Context context, View view, BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
		super();
		this.context = context;
		this.view = view;
		this.onItemClickListener = onItemClickListener;
		initPopWindow();
	}
	public PopMenu(Context context, BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
		super();
		this.context = context;
		this.view = ((Activity)context).findViewById(android.R.id.content);
		this.onItemClickListener = onItemClickListener;
		initPopWindow();
	}
	public PopMenu(Context context, BaseRecyclerAdapter.OnItemClickListener onItemClickListener, int num) {
		super();
		this.context = context;
		this.view = ((Activity)context).findViewById(android.R.id.content);
		this.onItemClickListener = onItemClickListener;
		this.lNum = num;
		initPopWindow();
	}
	/**
	 * 显示下拉选择 值会直接附在传进来的TextVIew上
	 */
	public void showPop(List<PopSelectInfo> allPop) {
		setPopList(allPop);
		showPop();
	}

	public void setPopList(List<PopSelectInfo> all) {
		this.allPop = all;
		adapter = new MenuAdapter(context,allPop);
		list.setAdapter(adapter);
		adapter.setOnItemClickListener(onItemClickListener);

	}
	public void showPop(){
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

	private void initPopWindow() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.layout_menu, null);
		list = view.findViewById(R.id.list);
		GridLayoutManager gridLayoutManager = new GridLayoutManager(context,lNum);
		list.setLayoutManager(gridLayoutManager);
		eFilterPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
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

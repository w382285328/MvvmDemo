package com.huantai.qhytims.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.huantai.qhytims.R;
import com.huantai.qhytims.bean.PopSelectInfo;

import java.util.List;


public class PopStringAdapter extends BaseAdapter {
	private List<PopSelectInfo> all;
	private Context context;
	private LayoutInflater mLayoutInflater;
	
	

	public PopStringAdapter(List<PopSelectInfo> all, Context context) {
		super();
		this.all = all;
		this.context = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return all.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return all.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHouder houder =null;
			if(convertView==null){
				houder=new ViewHouder();
				convertView = mLayoutInflater.inflate(R.layout.group_item, null);
				houder.name = convertView.findViewById(R.id.name);
				houder.name.setTextColor(context.getResources().getColor(R.color.main_blue));
				convertView.setTag(houder);
			}else{
				houder = (ViewHouder) convertView.getTag();	
			}
			houder.name.setText(all.get(position).getName());
			
		// TODO Auto-generated method stub
		return convertView;
	}
	public class ViewHouder{
     	public TextView name;
     }

}

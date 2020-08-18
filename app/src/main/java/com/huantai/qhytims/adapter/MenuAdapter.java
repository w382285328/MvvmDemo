package com.huantai.qhytims.adapter;

import android.content.Context;

import com.huantai.qhytims.R;
import com.huantai.qhytims.base.BaseRecyclerAdapter;
import com.huantai.qhytims.base.RecyclerViewHolder;
import com.huantai.qhytims.bean.PopSelectInfo;

import java.util.List;

import androidx.annotation.Nullable;

public class MenuAdapter extends BaseRecyclerAdapter<PopSelectInfo> {


    public MenuAdapter(Context ctx, @Nullable List<PopSelectInfo> list) {
        super(ctx, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_menu;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, PopSelectInfo item) {
        holder.setText(R.id.tx_name,item.getName());
        holder.setBackground(R.id.v_image,item.getImageId());
    }
}

package com.huantai.qhytims.adapter;

import android.content.Context;

import com.huantai.qhytims.R;
import com.huantai.qhytims.base.BaseRecyclerAdapter;
import com.huantai.qhytims.base.RecyclerViewHolder;
import com.huantai.qhytims.bean.PopSelectInfo;

import java.util.List;

import androidx.annotation.Nullable;

public
class DemoAdapter extends BaseRecyclerAdapter<PopSelectInfo> {
    public DemoAdapter(Context ctx, @Nullable List<PopSelectInfo> list) {
        super(ctx, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.group_item;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, PopSelectInfo item) {
    }
}

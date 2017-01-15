package com.chenggoi.ourchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/12.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private Context context;
    public OnRecyclerViewListener listener;

    public BaseViewHolder(Context context, ViewGroup group, int layoutId, OnRecyclerViewListener listener) {
        super(LayoutInflater.from(context).inflate(layoutId, group, false));
        this.context = context;
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    public abstract void bindData(T t);
}

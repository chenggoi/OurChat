package com.chenggoi.ourchat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.chenggoi.ourchat.R;
import com.chenggoi.ourchat.model.Friend;

import java.util.ArrayList;

/**
 * Created by chenggoi on 17-1-17.
 */

public class ContactListAdapter extends RecyclerView.Adapter {
    OnRecyclerViewListener listener;
    ArrayList<Friend> list;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(parent.getContext(), parent, R.layout.item_contact_list, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((BaseViewHolder) holder).bindData(null);
        } else {
            ((BaseViewHolder) holder).bindData(list.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
}

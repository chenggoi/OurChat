package com.chenggoi.ourchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMMessageType;
import cn.bmob.v3.BmobUser;

/**
 * Created by chenggoi on 17-1-12.
 */

public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<BmobIMMessage> messages = new ArrayList<>();
    String mCurrentUID = "";
    BmobIMConversation conversation;
    private onRecyclerViewListener onRecyclerViewListener;

    private final int TYPE_SEND_TEXT = 0;
    private final int TYPE_RECEIVE_TEXT = 1;

    public ChatListAdapter(Context context, BmobIMConversation conversation) {
        try {
            mCurrentUID = BmobUser.getCurrentUser(context).getObjectId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.conversation = conversation;
    }

    public void addMessages(List<BmobIMMessage> messages) {
        this.messages.addAll(0, messages);
        notifyDataSetChanged();
    }

    public void addMessage(BmobIMMessage message) {
        this.messages.addAll(Arrays.asList(message));
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        BmobIMMessage message = messages.get(position);
        if (message.getMsgType().equals(BmobIMMessageType.TEXT.getType())) {
            return message.getFromId().equals(mCurrentUID) ? TYPE_SEND_TEXT : TYPE_RECEIVE_TEXT;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SEND_TEXT:
                return new ChatSendViewHolder(parent, conversation, onRecyclerViewListener);
            case TYPE_RECEIVE_TEXT:
                return new ChatReceiveViewHolder(parent, conversation, onRecyclerViewListener);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).bindData(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setOnRecyclerViewListener(onRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}

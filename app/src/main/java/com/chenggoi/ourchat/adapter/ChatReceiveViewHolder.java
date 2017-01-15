package com.chenggoi.ourchat.adapter;

import android.icu.text.SimpleDateFormat;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenggoi.ourchat.R;

import butterknife.BindView;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;

/**
 * Created by Administrator on 2017/1/12.
 * 用于接收信息的ViewHolder
 */

public class ChatReceiveViewHolder extends BaseViewHolder {

    @BindView(R.id.chat_list_receive_avatar)
    ImageView chatReceiveAvatar;
    @BindView(R.id.chat_list_receive_tx)
    TextView chatReceiveText;
    @BindView(R.id.chat_list_receive_time)
    TextView cahtReceiveTime;
    private BmobIMConversation conversation;

    public ChatReceiveViewHolder(ViewGroup group, BmobIMConversation conversation, OnRecyclerViewListener listener) {
        super(group.getContext(), group, R.layout.item_chat_list_receive_msg, listener);
        this.conversation = conversation;
    }

    @Override
    public void bindData(Object o) {
        BmobIMMessage message = (BmobIMMessage) o;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String msgContent = message.getContent();
        String time = dateFormat.format(message.getCreateTime());
        chatReceiveText.setText(msgContent);
        cahtReceiveTime.setText(time);
    }
}

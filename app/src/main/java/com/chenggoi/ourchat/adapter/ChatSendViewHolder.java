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
 * Created by chenggoi on 17-1-12.
 * 用于发送信息的ViewHolder
 */

public class ChatSendViewHolder extends BaseViewHolder {
    @BindView(R.id.chat_list_send_tx)
    TextView chatSendText;
    @BindView(R.id.chat_list_send_avatar)
    ImageView chatSendAvatar;
    @BindView(R.id.chat_list_send_time)
    TextView chatSendTime;

    private BmobIMConversation conversation;

    public ChatSendViewHolder(ViewGroup group, BmobIMConversation conversation, onRecyclerViewListener listener) {
        super(group.getContext(), group, R.layout.item_chat_list_send_msg, listener);
        this.conversation = conversation;
    }

    @Override
    public void bindData(Object o) {
        BmobIMMessage message = (BmobIMMessage) o;
        // 格式化时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = dateFormat.format(message.getCreateTime());

        String msgContent = message.getContent();
        chatSendText.setText(msgContent);
        chatSendTime.setText(time);
    }
}

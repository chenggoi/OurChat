package com.chenggoi.ourchat.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chenggoi.ourchat.R;
import com.chenggoi.ourchat.adapter.ChatListAdapter;
import com.chenggoi.ourchat.adapter.OnRecyclerViewListener;
import com.chenggoi.ourchat.util.Global;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.MessageListHandler;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by Administrator on 2017/1/10.
 * 用户聊天界面
 */

public class ChatActivity extends BaseActivity implements MessageListHandler {

    @BindView(R.id.chat_list_recycle_view)
    RecyclerView rc_chat_view;
    @BindView(R.id.chat_bottom_edit_text)
    EditText edit_input_msg;
    @BindView(R.id.chat_bottom_add_button)
    Button btn_chat_add;
    @BindView(R.id.chat_bottom_emoticon_button)
    Button btn_chat_emoticon;
    @BindView(R.id.chat_bottom_voice_button)
    Button btn_chat_voice;
    @BindView(R.id.chat_bottom_send_button)
    Button btn_chat_send;

    BmobIMConversation conversation;
    LinearLayoutManager layoutManager;
    ChatListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        layoutManager = new LinearLayoutManager(this);
        Bundle bundle = getIntent().getBundleExtra(getPackageName());
        // 使用obtain方法才会真正创建一个管理消息发送的会话
        conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), (BmobIMConversation) bundle.getSerializable(Global.CONVERSION_MSG));

        adapter = new ChatListAdapter(this, conversation);
        rc_chat_view.setLayoutManager(layoutManager);
        rc_chat_view.setAdapter(adapter);
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void itemClick(int position) {

            }

            @Override
            public void itemLongClick(int position) {

            }
        });

        // 输入框的输入检测，如果输入了字符，则将发送按钮显示出来
        edit_input_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                scrollToBottom();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(edit_input_msg.getText())) {
                    btn_chat_send.setVisibility(View.VISIBLE);
                    btn_chat_add.setVisibility(View.GONE);
                } else {
                    btn_chat_send.setVisibility(View.GONE);
                    btn_chat_add.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.chat_bottom_send_button)
    public void onSendClick(View view) {
        sendMessage();
    }

    private void sendMessage() {
        String text = edit_input_msg.getText().toString();
        if (TextUtils.isEmpty(text.trim())) {
            return;
        }
        BmobIMMessage message = new BmobIMMessage();
        message.setContent(text);
        conversation.sendMessage(message, listener);
    }

    private void scrollToBottom() {
        layoutManager.scrollToPositionWithOffset(adapter.getItemCount() - 1, 0);
    }

    /**
     * 消息发送监听器
     */
    public MessageSendListener listener = new MessageSendListener() {
        @Override
        public void done(BmobIMMessage bmobIMMessage, BmobException e) {
            // 发送完成
            adapter.notifyDataSetChanged();
            edit_input_msg.setText("");
            scrollToBottom();
        }

        @Override
        public void onStart(BmobIMMessage bmobIMMessage) {
            // 开始发送
            super.onStart(bmobIMMessage);
            edit_input_msg.setText("");
            scrollToBottom();
            adapter.addMessage(bmobIMMessage);
        }
    };

    /**
     * 消息的接收
     *
     * @param list
     */
    @Override
    public void onMessageReceive(List<MessageEvent> list) {
        for (int i = 0; i < list.size(); i++) {
            MessageEvent event = list.get(i);
            BmobIMMessage msg = event.getMessage();
            if (conversation != null && event != null && conversation.getConversationId().equals(event.getConversation().getConversationId()) && !msg.isTransient()) {
                if (adapter.findPosition(msg) < 0) {
                    adapter.addMessage(msg);
                    conversation.updateReceiveStatus(msg);
                }
                scrollToBottom();
            }
        }
    }
}

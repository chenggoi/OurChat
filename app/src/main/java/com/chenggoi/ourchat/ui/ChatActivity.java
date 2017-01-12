package com.chenggoi.ourchat.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.chenggoi.ourchat.R;
import com.chenggoi.ourchat.util.Global;

import butterknife.BindView;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.core.BmobIMClient;

/**
 * Created by Administrator on 2017/1/10.
 * 用户聊天界面
 */

public class ChatActivity extends BaseActivity {

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

    BmobIMConversation conversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle bundle = getIntent().getBundleExtra(getPackageName());
        conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), (BmobIMConversation) bundle.getSerializable(Global.CONVERSION_MSG));
    }
}

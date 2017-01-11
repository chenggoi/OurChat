package com.chenggoi.ourchat.ui;

import android.os.Bundle;

import com.chenggoi.ourchat.util.Global;

import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.core.BmobIMClient;

/**
 * Created by Administrator on 2017/1/10.
 * 用户聊天界面
 */

public class ChatActivity extends BaseActivity {
    BmobIMConversation conversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra(getPackageName());
        conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), (BmobIMConversation) bundle.getSerializable(Global.CONVERSION_MSG));
    }
}

package com.chenggoi.ourchat.util;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * Created by chenggoi on 16-12-28.
 * 自定义消息接收器来处理服务器发来的消息和离线消息
 */

public class ChatMessageHandler extends BmobIMMessageHandler {
    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        //当接收到服务器发来的消息时，此方法被调用
        super.onMessageReceive(messageEvent);
    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        //每次调用connect方法时会查询一次离线消息，如果有，此方法会被调用
        super.onOfflineReceive(offlineMessageEvent);
    }
}

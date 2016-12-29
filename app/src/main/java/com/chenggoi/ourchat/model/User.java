package com.chenggoi.ourchat.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by chenggoi on 16-12-29.
 * 存放用户数据相关的类，包括username、password、avatar等
 */

public class User extends BmobUser {
    private String avatar;

    public User() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

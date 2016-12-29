package com.chenggoi.ourchat.util;

import android.content.Context;
import android.text.TextUtils;

import com.chenggoi.ourchat.model.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by chenggoi on 16-12-29.
 * 用户数据相关操作，包括登陆，注册，注销，获取已经登陆的用户等
 */

public class UserUtil {
    public static final int DEFAULT_LIMIT = 20;
    public static int CODE_NOT_EQUAL = 1001;
    private static UserUtil userUtil = new UserUtil();
    public int CODE_NULL = 1000;

    private UserUtil() {

    }

    // 获取UserUtil实例
    public static UserUtil getInstance() {
        return userUtil;
    }

    public Context getContext() {
        return BaseApplication.getInstance();
    }

    /**
     * 用户登录账号
     */
    public void userLogin(String username, String password, final LogInListener listener) {
        // 检测用户名和密码是否为空
        if (TextUtils.isEmpty(username)) {
            listener.internalDone(new BmobException(CODE_NULL, "请填写用户名"));
        }
        if (TextUtils.isEmpty(password)) {
            listener.internalDone(new BmobException(CODE_NULL, "请填写密码"));
        }
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(getContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                listener.done(getCurrentUser(), null);
            }

            @Override
            public void onFailure(int i, String s) {
                listener.done(user, new BmobException(i, s));
            }
        });

    }

    // 用户注销账号
    public void userLogOut() {
        BmobUser.logOut(getContext());
    }

    /**
     * 用户注册账号
     */
    public void userRegister(String username, String password, final LogInListener listener) {
        if (TextUtils.isEmpty(username)) {
            listener.internalDone(new BmobException(CODE_NULL, "请填写用户名"));
        }
        if (TextUtils.isEmpty(password)) {
            listener.internalDone(new BmobException(CODE_NULL, "请填写密码"));
        }
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(getContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                listener.done(getCurrentUser(), null);
            }

            @Override
            public void onFailure(int i, String s) {
                listener.done(user, new BmobException(i, s));
            }
        });

    }

    // 获取当前已登陆的账号信息
    public User getCurrentUser() {
        return BmobUser.getCurrentUser(getContext(), User.class);
    }
}

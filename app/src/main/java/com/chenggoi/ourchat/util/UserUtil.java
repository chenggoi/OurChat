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
 */

public class UserUtil {
    public static final int DEFAULT_LIMIT = 20;
    public static int CODE_NOT_EQUAL = 1001;
    private static UserUtil userUtil = new UserUtil();
    public int CODE_NULL = 1000;

    private UserUtil() {

    }

    public static UserUtil getInstance() {
        return userUtil;
    }

    public Context getContext() {
        return BaseApplication.getInstance();
    }

    public void userLogin(String username, String password, final LogInListener listener) {
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

    public void userLogOut() {
        BmobUser.logOut(getContext());
    }

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

    public User getCurrentUser() {
        return BmobUser.getCurrentUser(getContext(), User.class);
    }
}

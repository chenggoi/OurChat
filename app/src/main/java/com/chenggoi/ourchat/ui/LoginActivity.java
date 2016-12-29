package com.chenggoi.ourchat.ui;

import android.os.Bundle;
import android.widget.EditText;

import com.chenggoi.ourchat.R;
import com.chenggoi.ourchat.model.User;
import com.chenggoi.ourchat.util.UserUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by chenggoi on 16-12-29.
 * 用户登录界面
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_user_id_text)
    EditText userIdText;
    @BindView(R.id.login_user_password_text)
    EditText userPasswordText;

    @OnClick(R.id.user_login_button)
    public void onLoginClick() {
        UserUtil.getInstance().userLogin(userIdText.getText().toString(), userPasswordText.getText().toString(), new LogInListener() {
            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {
                    User user = (User) o;
                    BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
                    startActivity(MainActivity.class);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}

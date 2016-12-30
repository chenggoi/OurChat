package com.chenggoi.ourchat.ui;

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
 * Created by chenggoi on 16-12-30.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.register_user_name)
    EditText username;
    @BindView(R.id.register_user_password)
    EditText password;

    @OnClick(R.id.user_register_button)
    public void onRegisterClick() {
        UserUtil.getInstance().userRegister(username.getText().toString(), password.getText().toString(), new LogInListener() {
            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {
                    User user = (User) o;
                    BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
                    startActivity(MainActivity.class, null, true);
                } else {
                    showToast(e.getMessage() + "(" + e.getErrorCode() + ")");
                }
            }
        });
    }

}

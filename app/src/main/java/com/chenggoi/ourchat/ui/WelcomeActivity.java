package com.chenggoi.ourchat.ui;

import android.os.Bundle;
import android.view.View;

import com.chenggoi.ourchat.R;
import com.chenggoi.ourchat.model.User;

import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * Created by chenggoi on 16-12-30.
 */

public class WelcomeActivity extends BaseActivity {

    @OnClick({R.id.register_button, R.id.login_button})
    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                startActivity(RegisterActivity.class, null, false);
                break;
            case R.id.login_button:
                startActivity(LoginActivity.class, null, false);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        User user = BmobUser.getCurrentUser(WelcomeActivity.this, User.class);
        if (user != null) {
            startActivity(MainActivity.class, null, true);
        }
    }
}

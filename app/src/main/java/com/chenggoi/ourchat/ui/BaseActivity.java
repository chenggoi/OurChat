package com.chenggoi.ourchat.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by chenggoi on 16-12-6.
 */

public class BaseActivity extends Activity {
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        // add for bind butter knife
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        // add for bind butter knife
        ButterKnife.bind(this);
        initView();
    }

    protected void startActivity(Class<? extends Activity> c, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(BaseActivity.this, c);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }

    protected void initView() {
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

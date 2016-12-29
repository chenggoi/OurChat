package com.chenggoi.ourchat.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by chenggoi on 16-12-6.
 */

public class BaseActivity extends Activity {
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        initView();
    }

    protected void startActivity(Class<? extends Activity> c) {
        Intent intent = new Intent(BaseActivity.this, c);
        startActivity(intent);
    }

    protected void initView() {
    }
}

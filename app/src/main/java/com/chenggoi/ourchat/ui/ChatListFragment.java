package com.chenggoi.ourchat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenggoi.ourchat.R;

/**
 * Created by chenggoi on 16-12-6.
 * Chat list UI.
 */

public class ChatListFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Bind layout to fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        return v;
    }

}

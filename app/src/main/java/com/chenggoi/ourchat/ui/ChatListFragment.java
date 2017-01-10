package com.chenggoi.ourchat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenggoi.ourchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenggoi on 16-12-6.
 * Chat list UI.
 */

public class ChatListFragment extends BaseFragment {
    @BindView(R.id.chat_list_recycler_view)
    RecyclerView chatListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Bind layout to fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

}

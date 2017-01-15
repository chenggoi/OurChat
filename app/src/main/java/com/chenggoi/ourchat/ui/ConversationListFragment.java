package com.chenggoi.ourchat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenggoi.ourchat.R;
import com.chenggoi.ourchat.adapter.ConversationListAdapter;
import com.chenggoi.ourchat.adapter.OnRecyclerViewListener;
import com.chenggoi.ourchat.util.Global;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.newim.bean.BmobIMConversation;

/**
 * Created by chenggoi on 16-12-6.
 * 会话列表
 */

public class ConversationListFragment extends BaseFragment {
    @BindView(R.id.conversation_list_recycler_view)
    RecyclerView mConversationListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Bind layout to fragment
        View v = inflater.inflate(R.layout.fragment_conversation, container, false);
        ButterKnife.bind(this, v);
        // 设置RecyclerView的LayoutManager
        mConversationListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ConversationListAdapter adapter = new ConversationListAdapter();
        // 设置Adapter
        mConversationListView.setAdapter(adapter);
        // 设置RecyclerView上Item的点击事件，由于RecyclerView不像ListView有原生的Item点击事件，所以需要自定义一个listener然后通过Adapter触发回调
        adapter.setRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void itemClick(int position) {
                Bundle bundle = new Bundle();
                BmobIMConversation conversation = adapter.getItem(position);
                bundle.putSerializable(Global.CONVERSION_MSG, conversation);
                startActivity(ChatActivity.class, bundle);
            }

            @Override
            public void itemLongClick(int position) {

            }
        });
        return v;
    }

}

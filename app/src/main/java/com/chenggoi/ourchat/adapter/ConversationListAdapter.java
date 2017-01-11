package com.chenggoi.ourchat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenggoi.ourchat.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;

/**
 * Created by chenggoi on 17-1-5.
 */

public class ConversationListAdapter extends RecyclerView.Adapter<ConversationListAdapter.ViewHolder> {

    private List<BmobIMConversation> conversations;
    private onRecyclerViewListener listener;

    public void setRecyclerViewListener(onRecyclerViewListener listener) {
        this.listener = listener;
    }

    public ConversationListAdapter() {
        // 获取所有的会话
        conversations = BmobIM.getInstance().loadAllConversation();
    }

    // 创建一个新的view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    // 将数据与界面元素进行绑定操作
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        BmobIMConversation conversation = conversations.get(position);
        holder.userName.setText(conversation.getConversationId());
        holder.userNews.setText(conversation.getConversationTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && v != null) {
                    listener.itemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener != null && v != null) {
                    listener.itemLongClick(position);
                }
                return true;
            }
        });

    }

    // 根据位置获取元素
    public BmobIMConversation getItem(int position) {
        return conversations.get(position);
    }

    // 获取list项目数量
    @Override
    public int getItemCount() {
        return conversations.size();
    }

    // 实现一个ViewHolder绑定布局供Adapter使用
    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.conversation_list_user_icon)
        ImageView userIcon;
        @BindView(R.id.conversation_list_user_name)
        TextView userName;
        @BindView(R.id.conversation_list_user_news)
        TextView userNews;
        @BindView(R.id.conversation_list_user_time)
        TextView userTime;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}

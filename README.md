# OurChat

##[Butter Knife](http://jakewharton.github.io/butterknife/)

- Field and method binding for Android views which uses annotation processing to generate boilerplate code for you.
- 使用**@BindView(R.id.xx)**代替**findViewById()**
- 使用**@onClick(R.id.xx)**代替**onClickListener()**
- 使用**@BindBool, @BindColor, @BindDimen, @BindDrawable, @BindInt, @BindString**进行相关的绑定
- 还可以绑定一组Views，如**@onClick({R.id.aa,R.id.bb})**
- 在**Activity**的**onCreate()**方法中使用**ButterKnife.bind(this)**方法来生成代码，或者也可以在自定义的view root(如Fragment)下使用**ButterKnife.bind(this, view)**方法生成代码

```java
    public class MainActivity extends BaseActivity {

        // init bottom bar
        @BindView(R.id.chat_image)
        ImageView chatImage;
        @BindView(R.id.contacts_image)
        ImageView contactsImage;
        @BindView(R.id.discover_image)
        ImageView discoverImage;
        @BindView(R.id.mysetting_image)
        ImageView mySettingImage;

        @OnClick({R.id.chat_layout, R.id.contacts_layout, R.id.discover_layout, R.id.mysetting_layout})
        public void onBottonClick(View view) {
            // 根据view id来判断点击的哪个view从而做出相应的操作
        }
    }
```

##[Bmob IM SDK](http://docs.bmob.cn/im/Android/b_developdoc/doc/index.html)

- 注册消息接收器

```java
    public class ChatMessageHandler extends BmobIMMessageHandler{

        @Override
        public void onMessageReceive(final MessageEvent event) {
            //当接收到服务器发来的消息时，此方法被调用
        }

        @Override
        public void onOfflineReceive(final OfflineMessageEvent event) {
            //每次调用connect方法时会查询一次离线消息，如果有，此方法会被调用
        }
    }
```

- 在BaseApplication中的onCreate方法中注册该Handler

```java
    public class BmobIMApplication extends Application{

        @Override
        public void onCreate() {
            super.onCreate();
            //NewIM初始化
            BmobIM.init(this);
            //注册消息接收器
            BmobIM.registerDefaultMessageHandler(new ChatMessageHandler());
        }
    }
```

- 通过user.signUp()方法进行用户注册，user.login()方法进行用户登录，并对该段代码的实现进行了封装，通过**LogInListener listener**将结果回调
- 用户注册模块

```java
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
```

- 用户登录模块

```java
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
```

##RecyclerView

- 在Activity中获取这个RecyclerView，并声明LayoutManager与Adapter

```java
    // 设置RecyclerView的LayoutManager
    mConversationListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    final ConversationListAdapter adapter = new ConversationListAdapter();
    // 设置Adapter
    mConversationListView.setAdapter(adapter);
```

- 为RecyclerView定义一个Adapter继承自**RecyclerView.Adapter<ConversationListAdapter.ViewHolder>**其中**ViewHolder**使用内部类进行自定义操作
- ViewHolder用于绑定Item布局元素的操作,继承自**RecyclerView.ViewHolder**

```java
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
```

- Adapter中需要重写以下三个方法

```java
    // 创建一个新的view并绑定ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_conversation, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    // 将数据与界面元素进行绑定操作
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        BmobIMConversation conversation = conversations.get(position);
        holder.userName.setText(conversation.getConversationId());
        holder.userNews.setText(conversation.getConversationTitle());
    }
    // 获取list项目数量,同时也决定了list中展示的item数量
    @Override
    public int getItemCount() {
        return conversations.size();
    }
```

- RecyclerView没有提供原生的ItemClick方法，因此需要通过Adapter来模拟每一个item被点击的事件。这里可以通过自定义一个listener监听器来实现，首先在onBindViewHolder中通过ViewHolder获取到itemView并设置点击事件，点击事件中使用的listener为自定义的listener

```java
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null && v != null) {
                listener.itemClick(position);
            }
        }
    });
```

- 然后通过设置set方法，让外部类可以通过该方法为Adapter设置listener

```java
    public void setRecyclerViewListener(onRecyclerViewListener listener) {
        this.listener = listener;
    }
```

- 最后在外部类，如Activity中,通过调用Adapter的set方法，为listener设置回调的操作，从而实现item的点击效果

```java
    adapter.setRecyclerViewListener(new onRecyclerViewListener() {
        @Override
        public void itemClick(int position) {
            Bundle bundle = new Bundle();
            BmobIMConversation conversation = adapter.getItem(position);
            bundle.putSerializable(Global.CONVERSION_MSG, conversation);
            startActivity(ChatActivity.class, bundle);
        }
    }
```


##BaseActivity

- 重写**setContentView()**方法，加入butter knife的**ButterKnife.bind(this)**方法，省去其他activity频繁写此方法

##MainActivity

- 通过**getFragmentManager()**获取FragmentManager实例，使用**fragmentManager.beginTransaction()**的**add()**、**hide()**和**show()**方法控制Fragment页面的显示和隐藏，避免使用**replace()**每次切换都要new一个Fragment实例
- 由于动态添加Fragment使得每一个Fragment的resource id都是同一个id，因此需要通过为每个Fragment在add时添加的TAG加以区分
- 用户通过点击bottom bar上的按钮切换Fragment页面，通过bottom bar上按钮的**layout id**判断目标页面，通过**currentFragment.getTag()**来判断是当前哪个Fragment页面，并对bottom bar上的按钮进行变色


##Error

- gradle报**AGPBI: {"kind":"error","text":"warning: Ignoring InnerClasses attribute for an anonymous inner class...**错误，在build.gradle配置中添加**defaultConfig { ... ... multiDexEnabled true}**

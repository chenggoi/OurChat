#UI部分注释

##activity_welcome.xml

- 由一张全屏背景图和两个按钮组成
- **android:scaleType="fitXY"**:设置背景图拉伸
- RelativeLayout中将width设置为0可平分空间
- 设置一个居中的view，可以帮助两边的按钮以中轴线对齐
- drawable中设置item的**state_pressed**来模拟点击事件

##action_bar_title.xml

- 使用Toolbar作为导航栏控件
- 可在java代码中通过toolbar.setNavigationIcon等一系列set代码设置**导航栏图标、App的logo、标题和子标题、自定义控件、以及ActionMenu**
- 也可以在xml中设置上述内容，但要使用自定义属性的命名空间**(xmlns:toolbar="http://schemas.android.com/apk/res-auto")**，否则无效
- 由于Toolbar中setTitle设置的标题是靠左对其的，如果想要一个居中的标题，则应该在Toolbar中添加一个Textview，并设置**android:layout_gravity="center"**

##activity_login.xml

- 2dp高的ImageView用来做分割线
- EditText后边跟一个ImageView用来做清空输入的图标
- Button默认会将显示的英文全部大写，通过设置textAllCaps为false恢复正常

##action_bar_bottom

- ImageView中的图片要使用src设置，才可以在java中通过set方法更改，否则会造成和background的重叠

##activity_main

- 使用一个FrameLayout布局作为动态添加Fragment的容器，动态添加的Fragment可以通过设置TAG来进行区分，因为他们的res id都是一样的

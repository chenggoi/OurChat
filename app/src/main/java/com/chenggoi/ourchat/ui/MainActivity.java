package com.chenggoi.ourchat.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenggoi.ourchat.R;
import com.chenggoi.ourchat.util.Global;

/**
 * Created by chenggoi on 16-12-6.
 * MainActivity contains four fragment.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    FragmentManager fragmentManager;
    Fragment chatListFragment;
    Fragment contactsListFragment;
    Fragment discoverFragment;
    Fragment mySettingFragment;
    Fragment currentFragment;

    RelativeLayout chatLayout;
    RelativeLayout contactsLayout;
    RelativeLayout discoverLayout;
    RelativeLayout mySettingLayout;

    ImageView chatImage;
    ImageView contactsImage;
    ImageView discoverImage;
    ImageView mySettingImage;

    TextView chatText;
    TextView contactsText;
    TextView discoverText;
    TextView mySettingText;

    Toolbar topActionToolBar;
    TextView actionbarTitleText;

    @Override
    public void onClick(View view) {
        // Users may click the bottom button to change the fragment.
        switch (view.getId()) {
            case R.id.chat_layout:
                changeFragment(chatListFragment);
                chatImage.setImageResource(R.drawable.button_chat_down);
                chatText.setTextColor(chatText.getResources().getColor(R.color.text_color_green));
                actionbarTitleText.setText(R.string.chat_text);
                break;
            case R.id.contacts_layout:
                changeFragment(contactsListFragment);
                contactsImage.setImageResource(R.drawable.button_contact_down);
                contactsText.setTextColor(contactsText.getResources().getColor(R.color.text_color_green));
                actionbarTitleText.setText(R.string.contact_text);
                break;
            case R.id.discover_layout:
                changeFragment(discoverFragment);
                discoverImage.setImageResource(R.drawable.button_discover_down);
                discoverText.setTextColor(discoverText.getResources().getColor(R.color.text_color_green));
                actionbarTitleText.setText(R.string.discover_text);
                break;
            case R.id.mysetting_layout:
                changeFragment(mySettingFragment);
                mySettingImage.setImageResource(R.drawable.button_mysetting_down);
                mySettingText.setTextColor(mySettingText.getResources().getColor(R.color.text_color_green));
                actionbarTitleText.setText(R.string.setting_text);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use FragmentManager to control fragments.
        fragmentManager = getFragmentManager();

        chatListFragment = new ChatListFragment();
        contactsListFragment = new ContactListFragment();
        discoverFragment = new DiscoverFragment();
        mySettingFragment = new MySettingFragment();

        // add fragment to FragmentManager.
        fragmentManager.beginTransaction().add(R.id.main_frame_layout, chatListFragment, Global.CHAT_TAG).commit();
        fragmentManager.beginTransaction().add(R.id.main_frame_layout, contactsListFragment, Global.CONTACTS_TAG).commit();
        fragmentManager.beginTransaction().add(R.id.main_frame_layout, discoverFragment, Global.DISCOVER_TAG).commit();
        fragmentManager.beginTransaction().add(R.id.main_frame_layout, mySettingFragment, Global.SETTING_TAG).commit();
        fragmentManager.beginTransaction().hide(contactsListFragment).hide(discoverFragment).hide(mySettingFragment).show(chatListFragment).commit();

        currentFragment = chatListFragment;

        chatLayout = (RelativeLayout) findViewById(R.id.chat_layout);
        contactsLayout = (RelativeLayout) findViewById(R.id.contacts_layout);
        discoverLayout = (RelativeLayout) findViewById(R.id.discover_layout);
        mySettingLayout = (RelativeLayout) findViewById(R.id.mysetting_layout);

        chatLayout.setOnClickListener(this);
        contactsLayout.setOnClickListener(this);
        discoverLayout.setOnClickListener(this);
        mySettingLayout.setOnClickListener(this);

        chatImage = (ImageView) findViewById(R.id.chat_image);
        contactsImage = (ImageView) findViewById(R.id.contacts_image);
        discoverImage = (ImageView) findViewById(R.id.discover_image);
        mySettingImage = (ImageView) findViewById(R.id.mysetting_image);

        chatText = (TextView) findViewById(R.id.chat_text);
        contactsText = (TextView) findViewById(R.id.contacts_text);
        discoverText = (TextView) findViewById(R.id.discover_text);
        mySettingText = (TextView) findViewById(R.id.mysetting_text);

        chatImage.setImageResource(R.drawable.button_chat_down);
        chatText.setTextColor(chatText.getResources().getColor(R.color.text_color_green));

        topActionToolBar = (Toolbar) findViewById(R.id.action_bar);
        actionbarTitleText = (TextView) findViewById(R.id.action_bar_center_title);
        actionbarTitleText.setText(R.string.chat_text);
    }

    /**
     * Use add() and hide() to change the fragment.
     * change the color of the bottom button.
     */
    private void changeFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            if (fragment.isAdded()) {
                fragmentManager.beginTransaction().hide(currentFragment).show(fragment).commit();
            } else {
                fragmentManager.beginTransaction().hide(currentFragment).add(R.id.main_frame_layout, fragment).commit();
            }
            switch (currentFragment.getTag()) {
                case Global.CHAT_TAG:
                    chatImage.setImageResource(R.drawable.button_chat_up);
                    chatText.setTextColor(chatText.getResources().getColor(R.color.text_color_grey));
                    break;
                case Global.CONTACTS_TAG:
                    contactsImage.setImageResource(R.drawable.button_contact_up);
                    contactsText.setTextColor(contactsText.getResources().getColor(R.color.text_color_grey));
                    break;
                case Global.DISCOVER_TAG:
                    discoverImage.setImageResource(R.drawable.button_discover_up);
                    discoverText.setTextColor(discoverText.getResources().getColor(R.color.text_color_grey));
                    break;
                case Global.SETTING_TAG:
                    mySettingImage.setImageResource(R.drawable.button_mysetting_up);
                    mySettingText.setTextColor(mySettingText.getResources().getColor(R.color.text_color_grey));
                    break;
                default:
                    break;
            }
            currentFragment = fragment;
        }
    }
}

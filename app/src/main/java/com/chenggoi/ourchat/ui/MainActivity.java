package com.chenggoi.ourchat.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenggoi.ourchat.R;
import com.chenggoi.ourchat.util.Global;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenggoi on 16-12-6.
 * MainActivity contains four fragment.
 */

public class MainActivity extends BaseActivity {

    FragmentManager fragmentManager;
    Fragment chatListFragment;
    Fragment contactsListFragment;
    Fragment discoverFragment;
    Fragment mySettingFragment;
    Fragment currentFragment;

    // init bottom bar
    @BindView(R.id.chat_image)
    ImageView chatImage;
    @BindView(R.id.contacts_image)
    ImageView contactsImage;
    @BindView(R.id.discover_image)
    ImageView discoverImage;
    @BindView(R.id.mysetting_image)
    ImageView mySettingImage;

    @BindView(R.id.chat_text)
    TextView chatText;
    @BindView(R.id.contacts_text)
    TextView contactsText;
    @BindView(R.id.discover_text)
    TextView discoverText;
    @BindView(R.id.mysetting_text)
    TextView mySettingText;

    // init action bar
    @BindView(R.id.action_bar)
    Toolbar topActionToolBar;
    @BindView(R.id.action_bar_center_title)
    TextView actionbarTitleText;

    @OnClick({R.id.chat_layout, R.id.contacts_layout, R.id.discover_layout, R.id.mysetting_layout})
    public void onBottonClick(View view) {
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
        ButterKnife.bind(this);

        // Use FragmentManager to control fragments.
        fragmentManager = getFragmentManager();

        //init fragments
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

        chatImage.setImageResource(R.drawable.button_chat_down);
        chatText.setTextColor(chatText.getResources().getColor(R.color.text_color_green));

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

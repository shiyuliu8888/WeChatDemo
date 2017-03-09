package com.wechatdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Irain on 2017/2/27.
 */

public class ActivityMainWeChat extends Activity{
    public static ActivityMainWeChat instance = null;

    private ViewPager mTabPager;
    private ImageView mTab0,mTab1,mTab2,mTab3;
    private View mView0,mView1,mView2,mView3;
    private ImageView mAnimImg;
    private int zero = 0, one, two, three;//动画图片的偏移
    private int currIndex = 0;
    private boolean menu_display = false;
    //悬浮在activity上可以任意布局的弹出框
    private PopupWindow menuWindow;
    private LayoutInflater mInflater;
    private View viewLayout;
    private LinearLayout mCloseBtn;
    private LinearLayout mClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainwechat);
        //soft keyboard always hidden
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        instance = this;

        mTabPager = (ViewPager) findViewById(R.id.tabpager);
        mTabPager.addOnPageChangeListener(new MyOnPageChangeListener());
        mTab0 = (ImageView) findViewById(R.id.img_weixin);
        mTab1 = (ImageView) findViewById(R.id.img_address);
        mTab2 = (ImageView) findViewById(R.id.img_friends);
        mTab3 = (ImageView) findViewById(R.id.img_settings);
        mAnimImg = (ImageView) findViewById(R.id.img_tab_now);

        mTab0.setOnClickListener(new MyOnClickListener(0));
        mTab1.setOnClickListener(new MyOnClickListener(1));
        mTab2.setOnClickListener(new MyOnClickListener(2));
        mTab3.setOnClickListener(new MyOnClickListener(3));

        //获取屏幕分辨率
        Display currDisplay = getWindowManager().getDefaultDisplay();
        int displayWidth = currDisplay.getWidth();
        int displayHeight = currDisplay.getHeight();
        //将当前屏幕的宽度分为四份，bottombar的绿光效果就根据这个来变化
        one = displayWidth/4;
        two = one*2;
        three = one*3;

        LayoutInflater mLi = LayoutInflater.from(this);
        mView0 = (View)mLi.inflate(R.layout.viewpager_main0,null);
        mView1 = (View) mLi.inflate(R.layout.viewpager_main1,null);
        mView2 = (View) mLi.inflate(R.layout.viewpager_main2,null);
        mView3 = (View) mLi.inflate(R.layout.viewpager_main3,null);

        final ArrayList<View> views = new ArrayList<View>();
        views.add(mView0);
        views.add(mView1);
        views.add(mView2);
        views.add(mView3);

        PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager) container).removeView(views.get(position));
            }
            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }
        };
        mTabPager.setAdapter(mPagerAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){

            if(menu_display){
                menuWindow.dismiss();
                menu_display = false;
            }else{
                Intent intent = new Intent();
                intent.setClass(ActivityMainWeChat.this, ActivityExit.class);
                startActivity(intent);
            }
            //return false;
        }else if(keyCode == KeyEvent.KEYCODE_MENU){
            //when click menu ,menu need to initial
            if(!menu_display){
                mInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                viewLayout = mInflater.inflate(R.layout.menu_mainwechat,null);

                //new popupwindow, include contentView, width and height of layout
                menuWindow = new PopupWindow(viewLayout, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);

                //the animation when popupwindow show
                menuWindow.showAsDropDown(viewLayout);

                //the location of my popupwindow, parent and gravity and point x,y
                menuWindow.showAtLocation(this.findViewById(R.id.mainweixin),
                        Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,0);

                mClose = (LinearLayout) viewLayout.findViewById(R.id.menu_close);
                mCloseBtn = (LinearLayout) viewLayout.findViewById(R.id.menu_close_btn);

                mCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(ActivityMainWeChat.this, ActivityExit.class);
                        startActivity(intent);
                        menuWindow.dismiss();
                    }
                });
                menu_display = true;
            }else {
                menuWindow.dismiss();
                menu_display = false;
            }
            return true;
        }
        //Return true to prevent this event from being propagated further, or false to indicate that
        //you have not handled this event and it should continue to be propagated.
        return super.onKeyDown(keyCode, event);
    }

    public void btnmainright(View view){
        Intent intent = new Intent(ActivityMainWeChat.this, ActivityMainTopRightDialog.class);
        startActivity(intent);
    }

    public void startchat(View view){
        Intent intent = new Intent(ActivityMainWeChat.this, ActivityChat.class);
        startActivity(intent);
    }

    public void exit_settings(View v) { // 退出 伪“对话框”，其实是一个activity
        Intent intent = new Intent(ActivityMainWeChat.this, ActivityExitDialog.class);
        startActivity(intent);
    }
    public void btn_shake(View v) { // 手机摇一摇
        Intent intent = new Intent(ActivityMainWeChat.this, ActivityShake.class);
        startActivity(intent);
    }

    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0;
        public MyOnClickListener(int currIndex){index = currIndex;}
        @Override
        public void onClick(View view) {
            mTabPager.setCurrentItem(index);
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            switch (position) {
                //选中position为0时，动画需要从currIndex当前最近滑到选中的position
                case 0:
                    mTab0.setImageDrawable(getResources().getDrawable(
                            R.drawable.tab_weixin_pressed));
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, 0, 0, 0);
                        mTab1.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_address_normal));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_find_frd_normal));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, 0, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_settings_normal));
                    }
                    break;
                case 1:
                    //选中position为1时，动画需要从currIndex当前最近滑到第二格，则toX为one
                    mTab1.setImageDrawable(getResources().getDrawable(
                            R.drawable.tab_address_pressed));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, one, 0, 0);
                        mTab0.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_weixin_normal));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_find_frd_normal));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, one, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_settings_normal));
                    }
                    break;
                case 2:
                    mTab2.setImageDrawable(getResources().getDrawable(
                            R.drawable.tab_find_frd_pressed));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, two, 0, 0);
                        mTab0.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_weixin_normal));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                        mTab1.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_address_normal));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(three, two, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_settings_normal));
                    }
                    break;
                case 3:
                    mTab3.setImageDrawable(getResources().getDrawable(
                            R.drawable.tab_settings_pressed));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(zero, three, 0, 0);
                        mTab0.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_weixin_normal));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, three, 0, 0);
                        mTab1.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_address_normal));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, three, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(
                                R.drawable.tab_find_frd_normal));
                    }
                    break;
            }
            //currIndex的初始值为0
            currIndex = position;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(150);// 动画持续时间
            mAnimImg.startAnimation(animation);// 开始动画
        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}

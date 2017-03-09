package com.wechatdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * Created by Irain on 2017/2/27.
 */

public class AcitvityWhatsnew extends Activity {
    private ViewPager mViewpager;
    private ImageView mPage0;
    private ImageView mPage1;
    private ImageView mPage2;
    private ImageView mPage3;
    private ImageView mPage4;
    private ImageView mPage5;
    private int currIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsnew_viewpager);
        mViewpager = (ViewPager) findViewById(R.id.whatsnew_viewpager);
        mViewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        mPage0 = (ImageView) findViewById(R.id.page0);
        mPage1 = (ImageView) findViewById(R.id.page1);
        mPage2 = (ImageView) findViewById(R.id.page2);
        mPage3 = (ImageView) findViewById(R.id.page3);
        mPage4 = (ImageView) findViewById(R.id.page4);
        mPage5 = (ImageView) findViewById(R.id.page5);

        LayoutInflater mLi = LayoutInflater.from(this);
        View mView0 = mLi.inflate(R.layout.viewpager_whats0,null);
        View mView1 = mLi.inflate(R.layout.viewpager_whats1,null);
        View mView2 = mLi.inflate(R.layout.viewpager_whats2,null);
        View mView3 = mLi.inflate(R.layout.viewpager_whats3,null);
        View mView4 = mLi.inflate(R.layout.viewpager_whats4,null);
        View mView5 = mLi.inflate(R.layout.viewpager_whats5,null);

        final ArrayList<View> views = new ArrayList<>();
        views.add(mView0);
        views.add(mView1);
        views.add(mView2);
        views.add(mView3);
        views.add(mView4);
        views.add(mView5);

        PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            //这两个方法必要 否则会报错
            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };

        mViewpager.setAdapter(mPagerAdapter);
    }

    public void startbutton(View v) {
        Intent intent = new Intent();
        intent.setClass(AcitvityWhatsnew.this, ActivityWhatsnewDoor.class);
        startActivity(intent);
        this.finish();
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 1:
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 2:
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 3:
                    mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 4:
                    mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 5:
                    mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
            }
            currIndex = position;

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}

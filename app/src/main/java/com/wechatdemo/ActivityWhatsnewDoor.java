package com.wechatdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Irain on 2017/2/27.
 */

public class ActivityWhatsnewDoor extends Activity{
    private ImageView mLeft;
    private ImageView mRight;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsnewdoor);

        mLeft = (ImageView) findViewById(R.id.imageLeft);
        mRight = (ImageView) findViewById(R.id.imageRight);
        mText = (TextView) findViewById(R.id.anim_text);

        AnimationSet anim0  = new AnimationSet(true);
        TranslateAnimation myTransAnimation0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,-1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        myTransAnimation0.setDuration(2000);
        anim0.setStartOffset(800);
        anim0.addAnimation(myTransAnimation0);
        anim0.setFillAfter(true);
        mLeft.startAnimation(anim0);

        AnimationSet anim1 = new AnimationSet(true);
        TranslateAnimation myTransAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,+1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        myTransAnimation1.setDuration(1500);
        anim1.setStartOffset(800);
        anim1.addAnimation(myTransAnimation1);
        anim1.setFillAfter(true);
        mRight.startAnimation(anim1);

        AnimationSet anim2 = new AnimationSet(true);
        ScaleAnimation myScaleAnimation = new ScaleAnimation(1f,3f,1f,3f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        myScaleAnimation.setDuration(1000);
        AlphaAnimation myAlphaAnimation = new AlphaAnimation(1,0.0001f);
        myAlphaAnimation.setDuration(1500);
        anim2.addAnimation(myScaleAnimation);
        anim2.addAnimation(myAlphaAnimation);
        anim2.setFillAfter(true);
        mText.setAnimation(anim2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(ActivityWhatsnewDoor.this,ActivityMainWeChat.class);
                startActivity(intent);
                ActivityWhatsnewDoor.this.finish();
            }
        },2300);
    }
}

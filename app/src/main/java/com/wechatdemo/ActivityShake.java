package com.wechatdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.Toast;

/**
 * Created by Irain on 2017/3/1.
 */

public class ActivityShake extends Activity{

    ListenerShake mListenerShake = null;
    Vibrator mVibrator;

    private RelativeLayout mImgUp;
    private RelativeLayout mImgDn;
    private RelativeLayout mTitle;

    //private DrawerLayout mDrawerlayout;
    private SlidingDrawer mDrawer;
    private Button mDrawerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        mVibrator = (Vibrator) getApplication().getSystemService(VIBRATOR_SERVICE);
        mImgUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
        mImgDn = (RelativeLayout) findViewById(R.id.shakeImgDown);
        mTitle = (RelativeLayout) findViewById(R.id.shake_title_bar);

        mDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
        mDrawerBtn = (Button) findViewById(R.id.handle);

        mDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                mDrawerBtn.setBackground(getResources().getDrawable(R.drawable.shake_report_dragger_down));
                TranslateAnimation titleUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-1.0f);
                titleUp.setDuration(200);
                titleUp.setFillAfter(true);
                mTitle.startAnimation(titleUp);
            }
        });
        mDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                mDrawerBtn.setBackground(getResources().getDrawable(R.drawable.shake_report_dragger_up));
                TranslateAnimation titleDn = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-1.0f,Animation.RELATIVE_TO_SELF,0f);
                titleDn.setDuration(200);
                titleDn.setFillAfter(false);
                mTitle.startAnimation(titleDn);
            }
        });

        mListenerShake  = new ListenerShake(this);
        mListenerShake.setOnShakeListener(new ListenerShake.OnShakeListener() {
            @Override
            public void onShake() {
                startAnim();
                mListenerShake.stopSensor();
                startVibrator();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast mToast = Toast.makeText(getApplicationContext(),"抱歉没找到同时摇的人",Toast.LENGTH_SHORT);
                        mToast.show();
                        mVibrator.cancel();
                        mListenerShake.startSensor();
                   }
                },2000);
            }
        });
    }

    //shake animation
    public void startAnim(){
        AnimationSet animup = new AnimationSet(true);
        TranslateAnimation mytranslateanimup0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.5f);
        mytranslateanimup0.setDuration(1000);
        TranslateAnimation mytranslateanimup1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+0.5f);
        mytranslateanimup1.setDuration(1000);
        mytranslateanimup1.setStartOffset(1000);
        animup.addAnimation(mytranslateanimup0);
        animup.addAnimation(mytranslateanimup1);
        mImgUp.startAnimation(animup);

        AnimationSet animdn = new AnimationSet(true);
        TranslateAnimation mytranslateanimdn0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+0.5f);
        mytranslateanimdn0.setDuration(1000);
        TranslateAnimation mytranslateanimdn1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.5f);
        mytranslateanimdn1.setDuration(1000);
        mytranslateanimdn1.setStartOffset(1000);
        animdn.addAnimation(mytranslateanimdn0);
        animdn.addAnimation(mytranslateanimdn1);
        mImgDn.startAnimation(animdn);
    }

    public void startVibrator(){
        mVibrator.vibrate(new long[]{500,200,500,200},-1);//第一个｛｝里面是节奏数组， 第二个参数是重复次数，-1为不重复，非-1则从pattern的指定下标开始重复
    }

    public void linshi(View v) {
        startAnim();
//        mListenerShake.stopSensor();
//        startVibrator();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Toast mToast = Toast.makeText(getApplicationContext(),"抱歉没找到同时摇的人",Toast.LENGTH_SHORT);
//                mToast.show();
//                mVibrator.cancel();
//                mListenerShake.startSensor();
//            }
//        },2000);
    }
    public void shake_activity_back(View v) {     //标题栏 返回按钮
        this.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (mListenerShake != null) {
            mListenerShake.stopSensor();
        }
    }
}

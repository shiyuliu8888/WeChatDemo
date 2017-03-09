package com.wechatdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;

import static android.view.WindowManager.*;

public class ActivityAppStart extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //no title in here
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstart);
        //full screen
        getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
        //anim when in and out
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ActivityAppStart.this,ActivityWelcome.class);
                startActivity(intent);
                ActivityAppStart.this.finish();
            }
        }, 1000);
    }
}

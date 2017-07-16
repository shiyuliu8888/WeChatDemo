package com.wechatdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Irain on 2017/2/24.
 */

public class ActivityWelcome extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void welcome_login(View v){
        Intent intent = new Intent(ActivityWelcome.this, ActivityLogin.class);
        startActivity(intent);
    }

    public void welcome_register(View v){
        Intent intent = new Intent(ActivityWelcome.this, ActivityLogin.class);
        startActivity(intent);

    }
}

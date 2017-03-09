package com.wechatdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Irain on 2017/3/9.
 */

public class ActivityInfoPaofu extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_xiaohei);
    }

    public void btn_back(View v) {     //标题栏 返回按钮
        this.finish();
    }
    public void btn_back_send(View v) {     //标题栏 返回按钮
        this.finish();
    }
    public void head_xiaohei(View v) {     //头像按钮
        Intent intent = new Intent();
        intent.setClass(ActivityInfoPaofu.this,ActivityInfoHead.class);
        startActivity(intent);
    }
}

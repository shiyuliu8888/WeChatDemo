package com.wechatdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Irain on 2017/2/28.
 */

public class ActivityExit extends Activity{
    private LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);
        mLayout = (LinearLayout) findViewById(R.id.exit_layout);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.finish();
        return true;
    }

    public void exitbutton1(View view){
        this.finish();
    }

    public void exitbutton0(View view){
        this.finish();
        ActivityMainWeChat.instance.finish();

    }
}

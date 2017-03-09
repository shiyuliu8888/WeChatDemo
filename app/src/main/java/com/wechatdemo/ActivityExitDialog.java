package com.wechatdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Irain on 2017/3/1.
 */

public class ActivityExitDialog extends Activity{
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit_from_setting);
        //dialog=new MyDialog(this);
        layout=(LinearLayout)findViewById(R.id.exit_layout2);
        layout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }

    public void exitbutton1(View v) {
        this.finish();
    }
    public void exitbutton0(View v) {
        this.finish();
        ActivityMainWeChat.instance.finish();//关闭Main 这个Activity
    }

}

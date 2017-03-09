package com.wechatdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Irain on 2017/2/27.
 */

public class ActivityLogin extends Activity {
    private EditText mUser;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUser = (EditText) findViewById(R.id.login_user_edit);
        mPassword = (EditText) findViewById(R.id.login_passwd_edit);
    }

    public void login_mainwechat(View v){
        if("paofu".equals(mUser.getText().toString())&&"123".equals(mPassword.getText().toString())){
            Intent intent = new Intent();
            intent.setClass(ActivityLogin.this,ActivityLoading.class);
            startActivity(intent);
        }else if("".equals(mUser.getText().toString())||"".equals(mPassword.getText().toString())){
            new AlertDialog.Builder(ActivityLogin.this).setIcon(getResources()
                    .getDrawable(R.drawable.login_error_icon))
                    .setTitle("登录错误").setMessage("微信帐号或者密码不能为空，\n请输入后再登录！")
                    .create().show();
        }else{
            new AlertDialog.Builder(ActivityLogin.this)
                    .setIcon(getResources().getDrawable(R.drawable.login_error_icon))
                    .setTitle("登录失败")
                    .setMessage("微信帐号或者密码不正确，\n请检查后重新输入！")
                    .create().show();
        }
    }

    public void login_back(View v){
        this.finish();
    }

    public void login_pw(View v){
        Uri uri = Uri.parse("http://3g.qq.com");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}


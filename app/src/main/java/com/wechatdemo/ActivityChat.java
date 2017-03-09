package com.wechatdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Irain on 2017/3/1.
 */

public class ActivityChat extends Activity implements View.OnClickListener{

    private Button mBtnSend;
    private Button mBtnBack;
    private EditText mEditTextContent;
    private ListView mListView;
    private AdapterChatMsgView mAdapter;
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_xiaohei);
        //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();
        initData();
    }


    public void initView()
    {
        mListView = (ListView) findViewById(R.id.listview);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);

        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
    }

    private String[]msgArray = new String[]{"有大", "有！？", "我也有", "那上吧",
            "打啊！你放大啊", "你不？留人头那！。",
            "不解释", "....",};

    private String[]dataArray = new String[]{"2012-09-01 18:00", "2012-09-01 18:10",
            "2012-09-01 18:11", "2012-09-01 18:20",
            "2012-09-01 18:30", "2012-09-01 18:35",
            "2012-09-01 18:40", "2012-09-01 18:50"};
    private final static int COUNT = 8;
    public void initData()
    {
        for(int i = 0; i < COUNT; i++)
        {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(dataArray[i]);
            if (i % 2 == 0)
            {
                entity.setName("泡芙");
                entity.setMsgType(true);
            }else{
                entity.setName("可爱的");
                entity.setMsgType(false);
            }

            entity.setText(msgArray[i]);
            mDataArrays.add(entity);
        }

        mAdapter = new AdapterChatMsgView(this, mDataArrays);
        mListView.setAdapter(mAdapter);

    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.btn_send:
                send();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void send()
    {
        String contString = mEditTextContent.getText().toString();
        if (contString.length() > 0)
        {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(getDate());
            entity.setName("人马");
            entity.setMsgType(false);
            entity.setText(contString);

            mDataArrays.add(entity);
            //更新到
            mAdapter.notifyDataSetChanged();
            //编辑框置空
            mEditTextContent.setText("");
            mListView.setSelection(mListView.getCount() - 1);
        }
    }

    private String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));


        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);


        return sbBuffer.toString();
    }


    public void head_xiaoheii(View v) {     //标题栏 返回按钮
        Intent intent = new Intent (ActivityChat.this,ActivityInfoPaofu.class);
        startActivity(intent);
    }
}

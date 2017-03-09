package com.wechatdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Irain on 2017/3/9.
 */

public class AdapterChatMsgView extends BaseAdapter{

    public static interface IMsgViewType{
        int IMVT_COM_MSG = 0;
        int IMVT_TO_MSG = 1;
    }
    private static final String TAG = AdapterChatMsgView.class.getSimpleName();
    private List<ChatMsgEntity> coll;
    private Context mContext;
    private LayoutInflater mInflater;

    public AdapterChatMsgView(Context context,List<ChatMsgEntity> list){
        mContext = context;
        coll = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {return coll.size();}
    public Object getItem(int position) {return coll.get(position);}
    public long getItemId(int position) {return position;}

    public int getViewTypeCount(){
        return 2;
    }

    public int getItemViewType(int position){
        ChatMsgEntity entity = coll.get(position);
        if(entity.getMsgType())
            return IMsgViewType.IMVT_COM_MSG;
        else
            return IMsgViewType.IMVT_TO_MSG;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMsgEntity entity = coll.get(position);
        boolean isComMsg = entity.getMsgType();

        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            if (isComMsg)
            {
                convertView = mInflater.inflate(R.layout.chatting_item_msg_text_left, null);
            }else{
                convertView = mInflater.inflate(R.layout.chatting_item_msg_text_right, null);
            }

            viewHolder = new ViewHolder();
            viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            viewHolder.isComMsg = isComMsg;

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.tvSendTime.setText(entity.getDate());
        viewHolder.tvUserName.setText(entity.getName());
        viewHolder.tvContent.setText(entity.getText());

        return convertView;
    }

    static class ViewHolder {
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public boolean isComMsg = true;
    }
}

package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/6/4.
 */
import android.widget.BaseAdapter;
import android.content.Context;
import java.util.LinkedList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NotificationAdapter extends BaseAdapter {

    private LinkedList<Notification> mData;
    private Context mContext;

    public NotificationAdapter(LinkedList<Notification> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_message,parent,false);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_notification,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView txt_aName = (TextView) convertView.findViewById(R.id.new_name);
        TextView txt_aSpeak = (TextView) convertView.findViewById(R.id.new_signature);
        img_icon.setBackgroundResource(mData.get(position).getaIcon());
        txt_aName.setText(mData.get(position).getaName());
        txt_aSpeak.setText(mData.get(position).getaSpeak());
        return convertView;
    }
}
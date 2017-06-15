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

public class GroupAdapter extends BaseAdapter {

    private LinkedList<Group> mData;
    private Context mContext;

    public GroupAdapter(LinkedList<Group> mData, Context mContext) {
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_group,parent,false);
        TextView groupname = (TextView) convertView.findViewById(R.id.group_name);
        groupname.setText(mData.get(position).getaName());
        return convertView;
    }
}
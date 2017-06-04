package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/5/27.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;
import android.widget.ListView;

import java.util.LinkedList;
import android.app.Activity;


public class MyFragment1 extends Fragment implements OnClickListener{
    private Context mContext;
    private LinkedList<Message> mData = null;
    private MessageAdapter mAdapter = null;
    private ListView messagelist;

    public MyFragment1(){};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.frg_content1,container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
//        ListView message1=(ListView) getActivity().findViewById(R.id.message_list);
//        message1.setOnClickListener(this);
        ///////////////////////
        mContext = getContext();
        messagelist = (ListView) getActivity().findViewById(R.id.message_list);
        //messagelist.setOnClickListener(this);
        mData = new LinkedList<Message>();
        mData.add(new Message("狗说", "你是狗么?", R.mipmap.ic_launcher_round));
        mData.add(new Message("牛说", "你是牛么?", R.mipmap.ic_launcher_round));
        mData.add(new Message("鸭说", "你是鸭么?", R.mipmap.ic_launcher_round));
        mData.add(new Message("鱼说", "你是鱼么?", R.mipmap.ic_launcher_round));
        mData.add(new Message("马说", "你是马么?", R.mipmap.ic_launcher_round));
        mAdapter = new MessageAdapter((LinkedList<Message>) mData,mContext);
        messagelist.setAdapter(mAdapter);

    }
    public void onClick(View v){
        String name;
        switch (v.getId()){
            case R.id.message_list:
                name=this.getString(R.string.friend1_name);
                Intent it1=new Intent(getActivity(),ChatActivity.class);
                it1.putExtra("name",name);
                startActivity(it1);
                break;
            default:break;
        }
    }


}
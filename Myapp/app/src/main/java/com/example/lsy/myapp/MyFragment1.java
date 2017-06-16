package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/5/27.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.LinkedList;



public class MyFragment1 extends Fragment {
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
        mContext = getContext();
        messagelist = (ListView) getActivity().findViewById(R.id.message_list);
        mData = new LinkedList<Message>();
        mData.add(new Message("李四", "你是狗么?",14241425, R.mipmap.ic_launcher_round));
        //mData.add(new Message("牛说", "你是牛么?", R.mipmap.ic_launcher_round));
        //mData.add(new Message("鸭说", "你是鸭么?", R.mipmap.ic_launcher_round));
        //mData.add(new Message("鱼说", "你是鱼么?", R.mipmap.ic_launcher_round));
        //mData.add(new Message("马说", "你是马么?", R.mipmap.ic_launcher_round));
        mAdapter = new MessageAdapter((LinkedList<Message>) mData,mContext);
        messagelist.setAdapter(mAdapter);
        messagelist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String name=mData.get(position).getaName();
                int topic = mData.get(position).getTopic();
                Intent it1=new Intent(getActivity(),ChatActivity.class);
                it1.putExtra("name",name);
                it1.putExtra("topic",topic);
                startActivity(it1);
            }

        });

    }

}
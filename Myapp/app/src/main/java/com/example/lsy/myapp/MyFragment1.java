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
import android.widget.Button;
import android.widget.ListView;
import java.util.LinkedList;



public class MyFragment1 extends Fragment implements View.OnClickListener{
    private Context mContext;
    private LinkedList<Message> mData = null;
    private MessageAdapter mAdapter = null;
    private ListView messagelist;
    private String friendName;

    public MyFragment1(){};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.frg_content1,container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        Bundle bundle = getArguments();
        friendName=bundle.getString("friendName");
        messagelist = (ListView) getActivity().findViewById(R.id.message_list);
        Button message_find=(Button)getActivity().findViewById(R.id.button_sousuo);
        message_find.setOnClickListener(this);
        mData = new LinkedList<Message>();
        if (friendName!=null){
            mData.add(new Message(friendName,"你是狗么?",1, R.mipmap.ic_launcher_round));
        }
        mAdapter = new MessageAdapter((LinkedList<Message>) mData,mContext);
        messagelist.setAdapter(mAdapter);
        messagelist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                String friendName=mData.get(position).getaName();
                Intent it1=new Intent(getActivity(),ChatActivity.class);
                it1.putExtra("friendName",friendName);
                String name=mData.get(position).getaName();
                int topic = mData.get(position).getTopic();
                it1.putExtra("topic",topic);
                startActivity(it1);
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_sousuo:
                Intent intent=new Intent(getActivity(),FindFriendActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
    }
}
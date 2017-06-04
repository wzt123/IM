package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/5/27.
 */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;


public class MyFragment1 extends Fragment implements OnClickListener{
    public MyFragment1(){};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.frg_content1,container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        RelativeLayout message1=(RelativeLayout)getActivity().findViewById(R.id.message1);
        message1.setOnClickListener(this);
        ImageButton friend_pic1=(ImageButton)getActivity().findViewById(R.id.friend1_name_pic);
        friend_pic1.setOnClickListener(this);

    }
    public void onClick(View v){
        String name;
        switch (v.getId()){
            case R.id.message1:
                name=this.getString(R.string.friend1_name);
                Intent it1=new Intent(getActivity(),ChatActivity.class);
                it1.putExtra("name",name);
                startActivity(it1);
                break;
            case R.id.friend1_name_pic:
                Intent it2=new Intent(getActivity(),ChatActivity.class);
                //it1.putExtra("temp",name);
                startActivity(it2);
                break;
            default:break;
        }
    }
}
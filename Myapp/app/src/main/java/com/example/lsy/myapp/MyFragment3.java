package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/5/27.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


public class MyFragment3 extends Fragment implements OnClickListener {

    public MyFragment3(){};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.frg_content3,container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton login = (ImageButton) getActivity().findViewById(R.id.head_pic);
        login.setOnClickListener(this);
        RelativeLayout attention=(RelativeLayout)getActivity().findViewById(R.id.attention) ;
        attention.setOnClickListener(this);
        RelativeLayout games=(RelativeLayout)getActivity().findViewById(R.id.games);
        games.setOnClickListener(this);
        RelativeLayout arounding=(RelativeLayout)getActivity().findViewById(R.id.arounding);
        arounding.setOnClickListener(this);

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.head_pic:
                Intent it = new Intent(getActivity(),LoginActivity.class);
                startActivity(it);
                //关闭第一个Activit
                break;
            case R.id.attention:
                Intent it1 = new Intent(getActivity(),MyAttentionActivity.class);
                startActivity(it1);
                //关闭第一个Activit
                break;
            case R.id.arounding:
                Intent it2 = new Intent(getActivity(),AroundingActivity.class);
                startActivity(it2);
                //关闭第一个Activit
                break;
            case R.id.games:
                Intent it3 = new Intent(getActivity(),GamesActivity.class);
                startActivity(it3);
                //关闭第一个Activit
                break;
            default:break;
        }
    }

    private boolean judgeLogin(){
        SharedPreferences sp =MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        if(sp.getInt("userId",0)!=0) {
            return true;
        }
        return false;
    }
}
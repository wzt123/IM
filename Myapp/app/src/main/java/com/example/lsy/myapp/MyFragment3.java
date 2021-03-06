package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/5/27.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MyFragment3 extends Fragment implements OnClickListener {
    private Button tuichu;
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
        tuichu=(Button)getActivity().findViewById(R.id.button_tuichu);
        tuichu.setOnClickListener(this);
        if(judgeLogin()){
            tuichu.setVisibility(View.VISIBLE);
        }else {
            tuichu.setVisibility(View.GONE);
        }

        SharedPreferences sp =MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        if(sp.getInt("userId",0)!=0) {
            TextView login_text = (TextView) getActivity().findViewById(R.id.login_text);
            login_text.setText(sp.getString("userName", "Hello world"));
            utilsOfSDCard friendGroupMemory = new utilsOfSDCard();
            connectMysql connection = new connectMysql();
            Handler handler = null;
            connection.findFriendGroup(handler,sp.getInt("userId",0));
            connection.showFriend(handler,sp.getInt("userId",0));
//            ImInit mimInit = new ImInit();
//            mimInit.Init(handler);

//            ImMsg mimMsg = new ImMsg(mimInit.getClient());
//            boolean status = mimMsg.getMqttConnectStatus();
//            mimMsg.subscribe(14251426);
//            mimMsg.publish(14251426,"hello world");
        }
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.head_pic:
                if(judgeLogin()){
                    Intent it = new Intent(getActivity(),PersonalDataActivity.class);
                    startActivity(it);
                }
                else {
                    Intent it = new Intent(getActivity(), LoginActivity.class);
                    startActivity(it);
//                    Intent it = new Intent(getActivity(),PersonalDataActivity.class);
//                    startActivity(it);
                    //关闭第一个Activit
                }
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
            case R.id.button_tuichu:
                AlertDialog.Builder  builder=new AlertDialog.Builder(getContext());
                builder.setMessage("确定要退出登录么？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogOut();
                    }
                }).create();             //创建AlertDialog对象
                //builder创建对话框对象AlertDialog
                AlertDialog simpledialog=builder.create();
                simpledialog.show();
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
    public void LogOut(){
        SharedPreferences sp =MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sp.edit();
        mEditor.remove("userId");
        mEditor.remove("userTel");
        mEditor.remove("userName");
        mEditor.remove("userPassword");
        mEditor.commit();
        TextView login_text = (TextView) getActivity().findViewById(R.id.login_text);
        login_text.setText("登录");
        tuichu.setVisibility(View.GONE);
    }

}
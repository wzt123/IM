package com.example.lsy.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class AddFriendsActivity extends AppCompatActivity {

    protected Handler handler;
    private  TextView add_friend_tel = (TextView) findViewById(R.id.add_friend_tel);
    private  TextView add_friend_name = (TextView) findViewById(R.id.add_friend_name);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        TabHost th_add=(TabHost)findViewById(R.id.tabhost_add_friends);
        th_add.setup();            //初始化TabHost容器
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        th_add.addTab(th_add.newTabSpec("tab1").setIndicator("找人",null).setContent(R.id.tab1));
        th_add.addTab(th_add.newTabSpec("tab2").setIndicator("找群",null).setContent(R.id.tab2));
        th_add.addTab(th_add.newTabSpec("tab3").setIndicator("找公众号",null).setContent(R.id.tab3));
        TabWidget tabWidget = th_add.getTabWidget();
        for(int i = 0; i <tabWidget.getChildCount(); i++) {
            final TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(16);
        }
    }

    public void findFriend()
    {
        EditText add_friend_edit = (EditText) findViewById(R.id.add_friend_edit);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                SharedPreferences sp =MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                if(sp.getInt("addingFriendId",0)!=0) {
                    add_friend_tel.setText(sp.getString("addingFriendTel", "假装有这个人的电话"));
                    add_friend_name.setText(sp.getString("addingFriendName", "假装有这个人的名字"));
                }
            }
        };
        connectMysql connection = new connectMysql();
        connection.findFriend(Integer.parseInt(add_friend_edit.getText().toString()),handler);
    }

    public void addFriend(){

    }
}

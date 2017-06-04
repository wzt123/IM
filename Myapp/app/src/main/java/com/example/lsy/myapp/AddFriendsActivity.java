package com.example.lsy.myapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class AddFriendsActivity extends AppCompatActivity {

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
}

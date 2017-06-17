package com.example.lsy.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup rg_tab_bar;
    private RadioButton rb1,rb2,rb3;
    private Context mContext;
    private MyFragment1 fg1;
    private MyFragment2 fg2;
    private MyFragment3 fg3;
    private FragmentManager fManager;
    private String friendName;
    private int sendTopic;

    //////测试MQTT服务

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent mqttServiceIntent = new Intent(this,MQTTService.class);
        startService(mqttServiceIntent);
        //bindService(mqttServiceIntent, mServiceConnection,Context.BIND_AUTO_CREATE);

        //mServiceConnection.onServiceConnected();
        //ibinder.intentPublish("hello","helloZet");
        //fManager = getFragmentManager();
        fManager =getSupportFragmentManager();
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb1 = (RadioButton) findViewById(R.id.rb_message);
        rb2 = (RadioButton) findViewById(R.id.rb_friends);
        rb3 = (RadioButton) findViewById(R.id.rb_personal);
        int temp=getIntent().getIntExtra("temp",0);
        friendName=getIntent().getStringExtra("friendName");
        sendTopic=getIntent().getIntExtra("sendTopic",0);
        if(temp==1||temp==2) {
            rb3.setChecked(true);
            rb3.setSelected(true);
        }
        else {
            rb1.setSelected(true);
            rb1.setChecked(true);
        }
    }


    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_message:
                if(fg1 == null){
                    fg1=new MyFragment1();
                    Bundle bundle = new Bundle();
                    bundle.putString("friendName",friendName);
                    bundle.putInt("sendTopic",sendTopic);
                    fg1.setArguments(bundle);
                    fTransaction.add(R.id.ly_content,fg1);
                }else {
                    fTransaction.show(fg1);
                    rb1.setSelected(true);
                    rb2.setSelected(false);
                    rb3.setSelected(false);
                }
                break;
            case R.id.rb_friends:
                if(fg2 == null){
                    fg2=new MyFragment2();
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                rb2.setSelected(true);
                rb1.setSelected(false);
                rb3.setSelected(false);
                break;
            case R.id.rb_personal:
                if(fg3 == null){
                    fg3=new MyFragment3();
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                rb3.setSelected(true);
                rb2.setSelected(false);
                rb1.setSelected(false);
                break;
        }
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }

}

package com.example.lsy.myapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyAttentionActivity extends AppCompatActivity {
    private FragmentManager Manager;
    private FragmentAttention1 attention_fg1;
    private FragmentAttention2 attention_fg2;
    public int attention_num=0;/////特别关心数量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attention);
        Manager =getSupportFragmentManager();
        Judge();
    }
    public void Judge() {
        FragmentTransaction fTransaction = Manager.beginTransaction();
        hideAllFragment(fTransaction);
        if(attention_num==0){
            attention_fg1=new FragmentAttention1();
            fTransaction.add(R.id.attention_content,attention_fg1);
        }
        else {
            attention_fg2=new FragmentAttention2();
            fTransaction.add(R.id.attention_content,attention_fg2);
        }
        fTransaction.commit();
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(attention_fg1 != null)fragmentTransaction.hide(attention_fg1);
        if(attention_fg2 != null)fragmentTransaction.hide(attention_fg2);
    }
}

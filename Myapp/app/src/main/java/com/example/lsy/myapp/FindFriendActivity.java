package com.example.lsy.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindFriendActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        Button friend_find_close=(Button)findViewById(R.id.friend_find_start);
        friend_find_close.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.friend_find_start:
                //finish();
                break;
            default:break;
        }
    }
}

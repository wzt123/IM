package com.example.lsy.myapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        String name=getIntent().getStringExtra("name");
        TextView chat_friend_name=(TextView)findViewById(R.id.chat_friend_name);
        chat_friend_name.setText(name);
    }
}

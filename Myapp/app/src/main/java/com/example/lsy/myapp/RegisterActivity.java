package com.example.lsy.myapp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText mTel;
    private EditText mName;
    private EditText mPasswordd_1;
    private EditText mPasswordd_2;
    protected Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button mRegisterButton = (Button) findViewById(R.id.register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
        Button register_close = (Button) findViewById(R.id.register_close);
        register_close.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent it = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

    private void attemptRegister() {
        mTel = (EditText) findViewById(R.id.tel);
        mName = (EditText) findViewById(R.id.name);
        mPasswordd_1 = (EditText) findViewById(R.id.pswd1);
        mPasswordd_2 = (EditText) findViewById(R.id.pswd2);
        final int rs = 2;
        connectMysql connection = new connectMysql();
        connection.userRegister(Integer.parseInt(mTel.getText().toString()),mName.getText().toString(),mPasswordd_1.getText().toString(),handler);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.arg1 == 1) {
                    connection.userLogin(Integer.parseInt(mTel.getText().toString()),mPasswordd_1.getText().toString(),handler);
                }
                else if(msg.arg1 == 2)
                {
                    new AlertDialog.Builder(RegisterActivity.this).setTitle("提示").setMessage("注册成功").setPositiveButton("确定", null).show();
                    Intent it = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(msg.obj != null)
                {
                    SharedPreferences sp = MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = sp.edit();
                    if(sp.getInt("userId",0)!=0)
                    {
                        connection.addFriendGroup(handler);
                    }
                }
                else
                {
                    new  AlertDialog.Builder(RegisterActivity.this).setTitle("提示" ).setMessage("注册失败" ).setPositiveButton("确定" ,  null ).show();
                }
            }
        };
    }
}

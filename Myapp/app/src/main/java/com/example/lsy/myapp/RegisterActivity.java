package com.example.lsy.myapp;


import android.app.AlertDialog;
import android.content.Intent;
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
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.arg1 == 1) {
                    new AlertDialog.Builder(RegisterActivity.this).setTitle("提示").setMessage("注册成功").setPositiveButton("确定", null).show();
                }
                else
                {
                    new  AlertDialog.Builder(RegisterActivity.this).setTitle("提示" ).setMessage("注册失败" ).setPositiveButton("确定" ,  null ).show();
                }
            }
        };
//        if(mPasswordd_1.getText().toString().equals(mPasswordd_2.getText().toString()))
//        {
//            new  AlertDialog.Builder(this).setTitle("错误" ).setMessage("两次输入密码不同" ).setPositiveButton("确定" ,  null ).show();
//        }
//        else
//        {
            connectMysql connection = new connectMysql();
            connection.userRegister(Integer.parseInt(mTel.getText().toString()),mName.getText().toString(),mPasswordd_1.getText().toString(),handler);
//        }

    }
}

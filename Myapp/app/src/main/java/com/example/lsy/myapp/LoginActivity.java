package com.example.lsy.myapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.ResultSet;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText login_tel;
    private EditText login_pswd;
    protected Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(this);
        Button login_close = (Button) findViewById(R.id.login_close);
        login_close.setOnClickListener(this);
        Button register_open = (Button) findViewById(R.id.register_open);
        register_open.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_button:
                attemptLogin();
                break;
            case R.id.login_close:
                Intent it = new Intent(LoginActivity.this,MainActivity.class);
                it.putExtra("temp",1);
                startActivity(it);
                finish();
                break;
            case R.id.register_open:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void attemptLogin() {
        login_tel = (EditText) findViewById(R.id.login_tel);
        login_pswd = (EditText) findViewById(R.id.login_pswd);
        Boolean rs = false;

        handler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                super.handleMessage(msg);
                if (msg.obj != null) {
                    ResultSet rs = (ResultSet) msg.obj;
                    new AlertDialog.Builder(LoginActivity.this).setTitle("提示").setMessage("登录成功").setPositiveButton("确定", null).show();
                    Intent it1 = new Intent(LoginActivity.this,MainActivity.class);
                    it1.putExtra("temp",1);
                    startActivity(it1);
                    finish();
                }
                else
                {
                    new  AlertDialog.Builder(LoginActivity.this).setTitle("提示" ).setMessage("登录失败" ).setPositiveButton("确定" ,  null ).show();
                }

            }
        };

        connectMysql connection = new connectMysql();
        connection.userLogin(Integer.parseInt(login_tel.getText().toString()),login_pswd.getText().toString(),handler);

    }
}

package com.example.lsy.myapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zet on 2017/6/1.
 */

public class MyAppLication extends Application {
    public  static MyAppLication im;
    public int userId =0;
    //这是Android程序的入口
    @Override
    public void onCreate() {
        super.onCreate();
        im = this;
        SharedPreferences sp =this.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        if(sp.getInt("userId", 0)!=0)
        {
            this.userId = sp.getInt("userId", 0);
            utilsOfSDCard friendGroupMemory = new utilsOfSDCard();
            friendGroupMemory.fileIsExists(this.getInstance().getApplicationContext(),String.valueOf(sp.getInt("userId",0))+"friendGroup");
            friendGroupMemory.fileIsExists(this.getInstance().getApplicationContext(),String.valueOf(sp.getInt("userId",0))+"friend");
        }
    }
    public static MyAppLication getInstance() {
        return im;
    }
}

package com.example.lsy.myapp;

import android.app.Application;

/**
 * Created by zet on 2017/6/1.
 */

public class MyAppLication extends Application {
    public  static MyAppLication im;
    //这是Android程序的入口
    @Override
    public void onCreate() {
        super.onCreate();
        im = this;

    }
    public static MyAppLication getInstance() {
        return im;
    }
}

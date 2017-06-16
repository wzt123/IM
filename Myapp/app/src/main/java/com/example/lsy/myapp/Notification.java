package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/6/4.
 */

public class Notification {
    private String aName;
    private String aSpeak;
    private int aIcon;
    public Notification() {
    }

    public Notification(String aName, String aSpeak,int aIcon) {
        this.aName = aName;
        this.aSpeak = aSpeak;
        this.aIcon = aIcon;
    }

    public String getaName() {
        return aName;
    }
    public String getaSpeak() {
        return aSpeak;
    }
    public int getaIcon() {
        return aIcon;
    }
    public void setaName(String aName) {
        this.aName = aName;
    }
    public void setaSpeak(String aSpeak) {
        this.aSpeak = aSpeak;
    }
    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }
}

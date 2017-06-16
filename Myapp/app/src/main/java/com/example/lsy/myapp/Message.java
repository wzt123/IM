package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/6/4.
 */

public class Message {
    private String aName;
    private String aSpeak;
    private int aIcon;
    private int topic;
    public Message() {
    }

    public Message(String aName, String aSpeak,int atopic, int aIcon) {
        this.aName = aName;
        this.aSpeak = aSpeak;
        this.aIcon = aIcon;
        this.topic = atopic;
    }

    public String getaName() {
        return aName;
    }
    public int getTopic() {
        return topic;
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

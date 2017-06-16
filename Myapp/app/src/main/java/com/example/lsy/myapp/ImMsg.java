package com.example.lsy.myapp;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

/**
 * Created by zet on 2017/6/16.
 */

public class ImMsg {
    private String host = "tcp://www.zetaoword.com:1883";
    private int i = 1;
    private MqttClient client;

    public ImMsg(MqttClient mclient){
        this.client = mclient;
    }
    public boolean getMqttConnectStatus()
    {
        boolean isSuccess =client.isConnected();
        return isSuccess;
    }
    public void publish(int ImTopic,String message)
    {
        String topic =String.valueOf(ImTopic);
        int qos = 1;
        boolean retained = true;
        try {
            client.publish(topic,message.getBytes(),qos,retained);
        }
        catch (MqttSecurityException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle ", e);
        }
        catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle ", e);
        }
    }

    public void subscribe(int ImTopic)
    {
        String topic = String.valueOf(ImTopic);
        int qos = 0;
        try {
            String[] topics = new String[1];
            topics[0] = topic;
            client.subscribe(topic,qos);
        }
        catch (MqttSecurityException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + topic + " the client with the handle ", e);
        }
        catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + topic + " the client with the handle ", e);
        }
    }
}

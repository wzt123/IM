package com.example.lsy.myapp.mqtt;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

/**
 * Created by zet on 2017/6/16.
 */

public class ImMsg {
    private String clientHandle = null;
    private Context context = null;
    private ConnectionDetails connectionDetails = null;
    public ImMsg(ConnectionDetails connectionDetails, String clientHandle){
        this.connectionDetails = connectionDetails;
        this.clientHandle = clientHandle;
        context = connectionDetails;
    }
    public void publish(int ImTopic,String message)
    {
        String topic =String.valueOf(ImTopic);
        int qos = 0;
        boolean retained = true;

        String[] args = new String[2];
        args[0] = message;
        args[1] = topic+";qos:"+qos+";retained:"+retained;

        try {
            Connections.getInstance(context).getConnection(clientHandle).getClient()
                    .publish(topic, message.getBytes(), qos, retained, null, new ActionListener(context, ActionListener.Action.PUBLISH, clientHandle, args));
        }
        catch (MqttSecurityException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientHandle, e);
        }
        catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientHandle, e);
        }

    }

    public void subscribe(int ImTopic)
    {
        String topic = String.valueOf(ImTopic);
        int qos = 0;
        try {
            String[] topics = new String[1];
            topics[0] = topic;
            Connections.getInstance(context).getConnection(clientHandle).getClient()
                    .subscribe(topic, qos, null, new ActionListener(context, ActionListener.Action.SUBSCRIBE, clientHandle, topics));
        }
        catch (MqttSecurityException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + topic + " the client with the handle " + clientHandle, e);
        }
        catch (MqttException e) {
            Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + topic + " the client with the handle " + clientHandle, e);
        }
    }
}

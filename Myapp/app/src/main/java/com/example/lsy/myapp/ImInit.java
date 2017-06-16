package com.example.lsy.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.*;
import android.os.Message;

import com.example.lsy.myapp.mqtt.ConnectionDetails;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zet on 2017/6/17.
 */

public class ImInit {
    private String clientHandle = null;
    private Context context = null;
    private ConnectionDetails connectionDetails = null;

    private String host = "tcp://www.zetaoword.com:1883";
    private String userName = "admin";
    private String passWord = "password";
    private int i = 1;
    private String clientId;
    //private Handler handler;

    private MqttClient client;

    private String myTopic;
    private ScheduledExecutorService scheduler;
    private MqttConnectOptions options;
    private void ImInit(int topic,Handler handler) {
        SharedPreferences sp =MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        this.clientId=String.valueOf(sp.getInt("userId",0));
        this.myTopic = String.valueOf(topic);
        try {
            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(host, clientId, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            //options.setUserName(userName);
            // 设置连接的密码
            //options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            client.connect(options);
            // 设置回调
            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    // 连接丢失后，一般在这里面进行重连
                    //System.out.println("connectionLost----------");
                    startReconnect(handler);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // publish后会执行到这里
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }

                @Override
                public void messageArrived(String topicName, MqttMessage message) throws Exception {
                    // subscribe后得到的消息会执行到这里面
                    //System.out.println("messageArrived----------");
                    android.os.Message msg = new android.os.Message();
                    msg.what = 1;
                    msg.obj = topicName + "---" + message.toString();
                    handler.sendMessage(msg);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startReconnect(Handler handler) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                if (!client.isConnected()) {
                    connect(handler);
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }

    private void connect(Handler handler) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    client.connect(options);
                    android.os.Message msg = new android.os.Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    android.os.Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }


}

package com.example.lsy.myapp;

/**
 * Created by zet on 2017/5/25.
 */

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectMysql {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://115.159.189.144:3306/android";

    static final String USER = "wzt";
    static final String PASS = "123456";
    public connectMysql(){

    }
    public void userRegister(int tel, String name, String pswd,Handler handler)
    {
        new Thread()
        {
            public void run() {
                try {
                    //注册驱动
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    Statement stmt = conn.createStatement();
                    String sql = "insert into  personal(tel,name,pswd) values("+String.valueOf(tel)+","+name+","+pswd+");";
                    int rs = stmt.executeUpdate(sql);
                    Message message = Message.obtain();
                    message.arg1 = rs;
                    handler.sendMessage(message);

                    //rs.close();
                    stmt.close();
                    conn.close();
                    Log.v("yzy", "success to connect!");
                }catch(ClassNotFoundException e)
                {
                    Log.v("yzy", "fail to connect!"+"  "+e.getMessage());
                } catch (SQLException e)
                {
                    Log.v("yzy", "fail to connect!"+"  "+e.getMessage());
                }
            };
        }.start();
    }

    public void userLogin(int tel, String pswd,Handler handler){
        new Thread()
        {
            public void run() {
                try {
                    //注册驱动
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT * FROM personal WHERE tel='" + String.valueOf(tel)+"' AND pswd='"+pswd+" ';";
                    ResultSet rs = stmt.executeQuery(sql);
                    Message message = Message.obtain();
                    message.obj = rs;
                    handler.sendMessage(message);

                    rs.close();
                    stmt.close();
                    conn.close();
                    Log.v("yzy", "success to connect!");
                }catch(ClassNotFoundException e)
                {
                    Log.v("yzy", "fail to connect!"+"  "+e.getMessage());
                } catch (SQLException e)
                {
                    Log.v("yzy", "fail to connect!"+"  "+e.getMessage());
                }
            };
        }.start();

    }

    public void addFriend(int tel,Handler handler)
    {
        new Thread()
        {
            public void run() {
                try {
                    //注册驱动
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    Statement stmt = conn.createStatement();
                    String sql ="SELECT * FROM personal WHERE tel='" + String.valueOf(tel)+" ';";
                    ResultSet rs = stmt.executeQuery(sql);
                    Message message = Message.obtain();
                    message.obj = rs;
                    handler.sendMessage(message);

                    rs.close();
                    stmt.close();
                    conn.close();
                    Log.v("yzy", "success to connect!");
                }catch(ClassNotFoundException e)
                {
                    Log.v("yzy", "fail to connect!"+"  "+e.getMessage());
                } catch (SQLException e)
                {
                    Log.v("yzy", "fail to connect!"+"  "+e.getMessage());
                }
            };
        }.start();
    }

}

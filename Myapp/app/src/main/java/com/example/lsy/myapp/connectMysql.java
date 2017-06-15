package com.example.lsy.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.*;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zet on 2017/6/14.
 */

public class connectMysql {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://115.159.189.144:3306/android?characterEncoding=utf-8";

    static final String USER = "wzt";
    static final String PASS = "123456";

    private int userId;

    public connectMysql()
    {
        SharedPreferences sp =MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        if(sp.getInt("userId",0)!=0)
        {
            userId = sp.getInt("userId",0);
        }
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
                    String sql = "insert into  personal(tel,name,pswd) values("+String.valueOf(tel)+","+"'"+name+"'"+","+"'"+pswd+"'"+");";
                    int rs = 0;
                    rs = stmt.executeUpdate(sql);
                    android.os.Message message = android.os.Message.obtain();
                    message.arg1 = rs;
                    handler.sendMessage(message);
                    //rs.close();
                    stmt.close();
                    conn.close();
                    Log.e("yzy", "success to connect!");
                }catch(ClassNotFoundException e)
                {
                    Log.e("yzy", "fail to connect!"+"  "+e.getMessage());
                } catch (SQLException e)
                {
                    Log.e("yzy", "fail to connect!"+"  "+e.getMessage());
                }
            };
        }.start();
    }

    public void userLogin(int tel, String pswd,Handler handler)
    {
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
                    android.os.Message message = android.os.Message.obtain();
                    message.obj = rs;

                    SharedPreferences sp = MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = sp.edit();
                    if(rs.next()) {
                        mEditor.putInt("userId", rs.getInt("id"));
                        mEditor.putInt("userTel", rs.getInt("tel"));
                        mEditor.putString("userName", rs.getString("name"));
                        mEditor.putString("userPassword", rs.getString("pswd"));
                    }
                    mEditor.commit();

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

    public void findFriend(int tel,Handler handler)
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

                    SharedPreferences sp = MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = sp.edit();
                    if(rs.next()) {
                        mEditor.putInt("addingFriendId", rs.getInt("id"));
                        mEditor.putInt("addingFriendTel", rs.getInt("tel"));
                        mEditor.putString("addingFriendName", rs.getString("name"));
                    }
                    ///mEditor.putString("userPassword",rs.getString("pswd"));
                    mEditor.commit();

                    android.os.Message message = android.os.Message.obtain();
                    message.obj = rs;
                    rs.close();
                    stmt.close();
                    conn.close();
                    handler.sendMessage(message);
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

    public void addFriend(int friendId,String friendGroup,Handler handler)
    {
        new Thread()
        {
            public void run() {
                try {
                    //注册驱动
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    Statement stmt = conn.createStatement();
                    String sql = "insert into  addFriendMsg(requester,accepter,requesterGroup) values("+String.valueOf(userId)+","+String.valueOf
                            (friendId)+",'"+friendGroup+"');";
                    int rs = stmt.executeUpdate(sql);
                    android.os.Message message = android.os.Message.obtain();
                    message.arg1 = rs;
                    //rs.close();
                    stmt.close();
                    conn.close();
                    handler.sendMessage(message);
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

    public void addFriendGroup(String groupName, Handler handler)
    {
        new Thread()
        {
            public void run() {
                try {
                    //注册驱动
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    Statement stmt = conn.createStatement();
                    String sql = "insert into  friendGroup(ownerId,groupName,groupOrder) values("+String.valueOf(userId)+","+groupName+","+String.valueOf
                            (1)+");";
                    int rs = stmt.executeUpdate(sql);

                    android.os.Message message = android.os.Message.obtain();
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

    public void addFriendGroup(Handler handler)
    {

        new Thread()
        {
            public void run() {
                try {
                    //注册驱动
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    Statement stmt = conn.createStatement();
                    String sql = "insert into  friendGroup(ownerId,groupName,groupOrder) values("+String.valueOf(userId)+","+"'我的好友'"+","+String.valueOf
                            (0)+");";
                    int rs = stmt.executeUpdate(sql);
                    if(rs==1)
                    {
                        android.os.Message message = android.os.Message.obtain();
                        message.arg1 = 2;
                        handler.sendMessage(message);
                    }
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

    public void findFriendGroup(Handler handler,int userId)
    {
        new Thread()
        {
            public void run() {
                try {
                    //注册驱动
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT * FROM friendGroup WHERE ownerId=" + String.valueOf(userId)+ ";";
                    ResultSet rs = stmt.executeQuery(sql);
                    utilsOfSDCard mSDCardMemory = new utilsOfSDCard();
                    while (rs.next()) {
                        mSDCardMemory.SaveFriendGroup(MyAppLication.getInstance().getApplicationContext(), rs.getString("groupName"),userId);
                    }
                    //Message message = Message.obtain();
                    //message.obj = rs;
                    //handler.sendMessage(message);
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
}

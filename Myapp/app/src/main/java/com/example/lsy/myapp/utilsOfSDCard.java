package com.example.lsy.myapp;

/**
 * Created by zet on 2017/6/13.
 */

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * 保存数据到SD卡
 * 通过Environment.getExternalStorageDirectory();
 * @author Caesar
 *
 */
public class utilsOfSDCard {
    /**
     * 保存到SD卡
     * @param context
     * @param number
     * @param psw
     * @return
     */
    public static boolean SaveUserInfo(Context context, String number,
                                       String psw) {

        try {
            File SDCardFile = Environment.getExternalStorageDirectory();
            File file = new File(SDCardFile, "data.txt");
            FileOutputStream fos;

            fos = new FileOutputStream(file);

            String data = number + "##" + psw;
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 存储好友分组
    */
    public boolean SaveFriendGroup(Context context,String friendGroup,int userId)
    {
        try {
            File SDCardFile = Environment.getExternalStorageDirectory();
            File file = new File(SDCardFile, Integer.toString(userId)+"friendGroup.txt");
//            File SDCardFile = Environment.getExternalStorageDirectory();
//            File file = new File(SDCardFile, Integer.toString(userId)+"friend.txt");
            FileOutputStream fos;

            fos = new FileOutputStream(file);

            String data = "###"+friendGroup ;
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 存储好友
     */
    public boolean SaveFriend(Context context,String friendname,String friendGroup,int userId)
    {
        try {
            File SDCardFile = Environment.getExternalStorageDirectory();
            File file = new File(SDCardFile, Integer.toString(userId)+"friend1.txt");
            FileOutputStream fos;

            fos =new FileOutputStream(file);

            String data ="###"+friendGroup+"##"+friendname ;
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    /**
     *
     * */
    public String[] GetFriendGroup(Context context,int userId) {
        try {
            File sDCardFile = Environment.getExternalStorageDirectory();
            File file = new File(sDCardFile, Integer.toString(userId)+"friendGroup.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            String readLine = br.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                String split[] = readLine.split("###");
                String friendGroup[] = new String[split.length];
                for(int i=0;i<split.length-1;i++)
                {
                    friendGroup[i] = split[i+1];
                }
                return friendGroup;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    /**
     *获取好友
     * */
    public String[] GetFriend(Context context, int userId) {
        try {
            File sDCardFile = Environment.getExternalStorageDirectory();
            File file = new File(sDCardFile, Integer.toString(userId)+"friend1.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            String readLine = br.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                String data[] = readLine.split("###");
                //HashMap[] friendMap = new HashMap[data.length];
                String[] friendMap = new String[data.length];
                for(int i=0;i<data.length;i++) {

                    String[] friend = data[i].split("##");
                    friendMap[i]=friend[1];
                }

                return friendMap;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 从SD卡中读取数据
     * @param context
     * @return
     */
    public static Map<String, String> GetUserInfo(Context context) {

        try {
            File sDCardFile = Environment.getExternalStorageDirectory();
            File file = new File(sDCardFile, "data.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            String readLine = br.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                Map<String, String> userInfoMap = new HashMap<String, String>();
                String split[] = readLine.split("##");
                userInfoMap.put("number", split[0]);
                userInfoMap.put("psw", split[1]);
                return userInfoMap;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public boolean fileIsExists(Context context,String fileName){
        try{
            File f = context.getFilesDir();
            String filePath = f.getAbsolutePath()+fileName;
            File mfile=new File(filePath);
            if (!mfile.exists())
            {
                if (mfile.mkdir())
                {
                    return true;
                }
                else
                    return false;
            }
        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }
}
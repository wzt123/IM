package com.example.lsy.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class NewFriendActivity extends AppCompatActivity implements View.OnClickListener {
    private LinkedList<Notification> mData = null;
    private NotificationAdapter mAdapter = null;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        context=NewFriendActivity.this;
        ListView notification_list = (ListView)findViewById(R.id.notification_list);

        mData = new LinkedList<Notification>();
        mData.add(new Notification("简单回忆","人 生若只如初见",R.mipmap.ic_launcher_round));
        mData.add(new Notification("简单遗忘","何事秋风悲画扇",R.mipmap.ic_launcher_round));
        mAdapter = new NotificationAdapter((LinkedList<Notification>) mData,context);
        notification_list.setAdapter(mAdapter);
        notification_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
////                String name=mData.get(position).getaName();
////                Intent it1=new Intent(GroupSettingActivity.this,ChatActivity.class);
////                it1.putExtra("name",name);
////                startActivity(it1);
//                String str=mData.get(position).getaName();
//                //new AlertDialog.Builder(GroupSettingActivity.this).setTitle(str).show();
//                new AlertDialog.Builder(GroupSettingActivity.this).setTitle("提示").setMessage("删除分组"+str).setPositiveButton("确定", null).setNegativeButton("取消",null).show();
////                ImageView delete=(ImageView)findViewById(R.id.img_delete);
////                delete.setOnClickListener(new View.OnClickListener(){
////                    @Override
////                    public void onClick(View v) {
////                        new AlertDialog.Builder(GroupSettingActivity.this).setTitle(str).show();
////                    }
////                });
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tianjia_group:
               // showInputDialog();
                break;
            case R.id.done:
                ////////添加完成代码
                break;
            default:break;
        }
    }
//    private void showInputDialog() {
//    /*@setView 装入一个EditView
//     */
////        //new AlertDialog.Builder(GroupSettingActivity.this).setTitle("提示").setMessage("删除分组").show();
////        final EditText editText = new EditText(GroupSettingActivity.this);
////        AlertDialog.Builder inputDialog =
////                new AlertDialog.Builder(GroupSettingActivity.this);
////        inputDialog.setTitle("添加分组").setMessage("请输入新的分组名称").setView(editText);
////        inputDialog.setPositiveButton("确定",
////                new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        Toast.makeText(GroupSettingActivity.this,
////                                editText.getText().toString(),
////                                Toast.LENGTH_SHORT).show();
////                    }
////                }).setNegativeButton("取消",null).show();
//    }
}

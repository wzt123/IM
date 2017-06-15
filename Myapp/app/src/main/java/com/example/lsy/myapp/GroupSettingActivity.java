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

public class GroupSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private LinkedList<Group> mData = null;
    private GroupAdapter mAdapter = null;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_setting);
        context=GroupSettingActivity.this;
        ListView grouplist = (ListView)findViewById(R.id.group_list);
        TextView done=(TextView)findViewById(R.id.done);
        done.setOnClickListener(this);
        RelativeLayout tianjia=(RelativeLayout)findViewById(R.id.tianjia_group);
        tianjia.setOnClickListener(this);

        mData = new LinkedList<Group>();
        mData.add(new Group("简单回忆"));
        mData.add(new Group("简单遗忘"));
        mData.add(new Group("原来"));
        mData.add(new Group("我们的爱情"));
        mData.add(new Group("是那么的纯粹"));
        mAdapter = new GroupAdapter((LinkedList<Group>) mData,context);
        grouplist.setAdapter(mAdapter);
        grouplist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
//                String name=mData.get(position).getaName();
//                Intent it1=new Intent(GroupSettingActivity.this,ChatActivity.class);
//                it1.putExtra("name",name);
//                startActivity(it1);
                String str=mData.get(position).getaName();
                //new AlertDialog.Builder(GroupSettingActivity.this).setTitle(str).show();
                new AlertDialog.Builder(GroupSettingActivity.this).setTitle("提示").setMessage("删除分组"+str).setPositiveButton("确定", null).setNegativeButton("取消",null).show();
//                ImageView delete=(ImageView)findViewById(R.id.img_delete);
//                delete.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        new AlertDialog.Builder(GroupSettingActivity.this).setTitle(str).show();
//                    }
//                });
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tianjia_group:
                showInputDialog();
                break;
            case R.id.done:
                ////////添加完成代码
                break;
            default:break;
        }
    }
    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        //new AlertDialog.Builder(GroupSettingActivity.this).setTitle("提示").setMessage("删除分组").show();
        final EditText editText = new EditText(GroupSettingActivity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(GroupSettingActivity.this);
        inputDialog.setTitle("添加分组").setMessage("请输入新的分组名称").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(GroupSettingActivity.this,
                                editText.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消",null).show();
    }
}

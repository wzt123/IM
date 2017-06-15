package com.example.lsy.myapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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
                
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tianjia_group:
                ////////添加分组代码
                break;
            case R.id.done:
                ////////添加完成代码
                break;
            default:break;
        }
    }
}

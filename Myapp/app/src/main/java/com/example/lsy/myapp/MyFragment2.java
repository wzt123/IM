package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/5/27.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


public class MyFragment2 extends Fragment implements OnClickListener,AdapterView.OnItemLongClickListener{
    private PinnedHeaderExpandableListView explistview;
    private int expandFlag = -1;//控制列表的展开
    private PinnedHeaderExpandableAdapter adapter;
    private Context mContext;
    ///////
    public MyFragment2(){};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.frg_content2,container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton add_friends = (ImageButton) getActivity().findViewById(R.id.add_friends);
        add_friends.setOnClickListener(this);
        Button friend_sousuo=(Button) getActivity().findViewById(R.id.friend_sousuo);
        friend_sousuo.setOnClickListener(this);
        //////////////////////////////tabHost/////////////////////
        TabHost th=(TabHost)getActivity().findViewById(R.id.tabhost);
        th.setup();            //初始化TabHost容器
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        th.addTab(th.newTabSpec("tab1").setIndicator("好友",null).setContent(R.id.tab1));
        th.addTab(th.newTabSpec("tab2").setIndicator("群组",null).setContent(R.id.tab2));
        th.addTab(th.newTabSpec("tab3").setIndicator("公众号",null).setContent(R.id.tab3));
        TabWidget tabWidget = th.getTabWidget();
        for(int i = 0; i <tabWidget.getChildCount(); i++) {
            final TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(18);
        }
        ///init view
        explistview = (PinnedHeaderExpandableListView)getActivity().findViewById(R.id.explistview);
        explistview.setOnItemLongClickListener(this);
        //////init data
        SharedPreferences sp =MyAppLication.getInstance().getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        utilsOfSDCard friendGroup = new utilsOfSDCard();
        if(sp.getInt("userId",0)!=0) {
            String Group[] = friendGroup.GetFriendGroup(MyAppLication.getInstance().getApplicationContext(), sp.getInt("userId", 0));
            //HashMap friend[] = friendGroup.GetFriend(MyAppLication.getInstance().getApplicationContext(), sp.getInt("userId", 0));
            String[] friend = friendGroup.GetFriend(MyAppLication.getInstance().getApplicationContext(), sp.getInt("userId", 0));
            String[][] childrenData = new String[Group.length-1][friend.length-1];
            String[] groupData = new String[Group.length-1];
            for (int i = 0; i < Group.length-1; i++) {
                groupData[i] = Group[i];
                for(int j=0;j<friend.length-1;j++)
                {
                    childrenData[i][j] = friend[j];
                }
            }


//            for(int j=0;j<Group.length;j++)
//            {
//                int z=0;
//                for(int i=0;i<friend.length;i++)
//                {
//                    if(friend[i].get(Group[j])!=null){
//                        childrenData[j][z++]= (String) friend[i].get(Group[j]);
//                    }
//                }
//            }

            //设置悬浮头部VIEW
            explistview.setHeaderView(getActivity().getLayoutInflater().inflate(R.layout.group_head,explistview, false));
            adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData,getActivity().getApplicationContext(),explistview);
            explistview.setAdapter(adapter);
        }
        else
        {
            String[][] childrenData = new String[1][1];
            String[] groupData = new String[1];
            explistview.setHeaderView(getActivity().getLayoutInflater().inflate(R.layout.group_head,explistview, false));
            adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData,getActivity().getApplicationContext(),explistview);
            explistview.setAdapter(adapter);
            new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("请登录").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent it = new Intent(getActivity(), LoginActivity.class);
                    startActivity(it);
                }
            }).show();
        }
        //////////////child点击事件///////////////
        explistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getActivity(),PersonalDataActivity.class);
                String[][] ChildData=new String[1][1];
                startActivity(intent);
                return true;
            }
        });

    }
    ////////
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_friends:
                Intent it1 = new Intent(getActivity(),AddFriendsActivity.class);
                startActivity(it1);
                break;
            case R.id.friend_sousuo:
                Intent it2 = new Intent(getActivity(),FindFriendActivity.class);
                startActivity(it2);
                break;
            default:break;
        }
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent=new Intent(getActivity(),GroupSettingActivity.class);
        startActivity(intent);
        return true;
    }

//    class GroupClickListener implements OnGroupClickListener{
//        @Override
//        public boolean onGroupClick(ExpandableListView parent, View v,
//                                    int groupPosition, long id) {
//            if (expandFlag == -1) {
//                // 展开被选的group
//                explistview.expandGroup(groupPosition);
//                // 设置被选中的group置于顶端
//                explistview.setSelectedGroup(groupPosition);
//                expandFlag = groupPosition;
//            } else if (expandFlag == groupPosition) {
//                explistview.collapseGroup(expandFlag);
//                expandFlag = -1;
//            } else {
//                explistview.collapseGroup(expandFlag);
//                // 展开被选的group
//                explistview.expandGroup(groupPosition);
//                // 设置被选中的group置于顶端
//                explistview.setSelectedGroup(groupPosition);
//                expandFlag = groupPosition;
//            }
//            return true;
//        }
//    }
//    private void initPopWindow(View v,int position) {
//        View view = LayoutInflater.from(getParentFragment().getContext()).inflate(R.layout.item_pop, null, false);
//        Button btn_xixi = (Button) view.findViewById(R.id.group_setting);
//        //1.构造一个PopupWindow，参数依次是加载的View，宽高
//        final PopupWindow popWindow = new PopupWindow(view,
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//
//        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画
//
//        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
//        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
//        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
//        popWindow.setTouchable(true);
//        popWindow.setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//            }
//        });
//        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效
//        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
//        popWindow.showAsDropDown(v, 50, 0);
//
//        //设置popupWindow里的按钮的事件
//        btn_xixi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(),LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
}
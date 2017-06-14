package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/5/27.
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


public class MyFragment2 extends Fragment implements OnClickListener{
    /////
    private PinnedHeaderExpandableListView explistview;
    private String[][] childrenData = new String[10][10];
    private String[] groupData = new String[10];
    private int expandFlag = -1;//控制列表的展开
    private PinnedHeaderExpandableAdapter adapter;
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
        Button button_sousuo=(Button) getActivity().findViewById(R.id.button_sousuo);
        button_sousuo.setOnClickListener(this);

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
        //////init data
        MyFileMemory mFileMemory = new MyFileMemory();
        utilsOfSDCard friendGroup = new utilsOfSDCard();
        for(int i=0;i<10;i++){
            groupData[i] = "分组"+i;
        }

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                childrenData[i][j] = "好友"+i+"-"+j;
            }
        }
        //设置悬浮头部VIEW
        explistview.setHeaderView(getActivity().getLayoutInflater().inflate(R.layout.group_head,explistview, false));
        adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData,getActivity().getApplicationContext(),explistview);
        explistview.setAdapter(adapter);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_friends:
                Intent it1 = new Intent(getActivity(),AddFriendsActivity.class);
                startActivity(it1);
                break;
            case R.id.button_sousuo:
                Intent it2 = new Intent(getActivity(),FindFriendActivity.class);
                startActivity(it2);
                break;
            default:break;
        }
    }


    class GroupClickListener implements OnGroupClickListener{
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
            if (expandFlag == -1) {
                // 展开被选的group
                explistview.expandGroup(groupPosition);
                // 设置被选中的group置于顶端
                explistview.setSelectedGroup(groupPosition);
                expandFlag = groupPosition;
            } else if (expandFlag == groupPosition) {
                explistview.collapseGroup(expandFlag);
                expandFlag = -1;
            } else {
                explistview.collapseGroup(expandFlag);
                // 展开被选的group
                explistview.expandGroup(groupPosition);
                // 设置被选中的group置于顶端
                explistview.setSelectedGroup(groupPosition);
                expandFlag = groupPosition;
            }
            return true;
        }
    }

}
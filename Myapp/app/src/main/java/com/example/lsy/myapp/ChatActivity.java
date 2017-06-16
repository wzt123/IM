package com.example.lsy.myapp;
/**
 * Created by lsy on 2017/6/5.
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private EditText et;
    private TextView tvSend;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView chatfriendname=(TextView) findViewById(R.id.chat_friend_name) ;
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        int topic = intent.getIntExtra("topic",0);
        chatfriendname.setText(name);

        ImageButton chat_setting =(ImageButton) findViewById(R.id.chat_setting);
        chat_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(ChatActivity.this, ChatSettingActivity.class);
                startActivity(it);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        et = (EditText) findViewById(R.id.et);
        tvSend = (TextView) findViewById(R.id.btsend);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter = new ChatAdapter());
        adapter.replaceAll(TestData.getTestAdData());
        initData();
    }

    private void initData() {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                content = s.toString().trim();
            }
        });

        tvSend.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ArrayList<ItemModel> data = new ArrayList<>();
                ChatModel model = new ChatModel();
                model.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
                model.setContent(content);
                data.add(new ItemModel(ItemModel.CHAT_B, model));
                adapter.addAll(data);
                et.setText("");
                hideKeyBorad(et);
            }
        });

    }

    private void hideKeyBorad(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }
    public static class TestData {

        public static ArrayList<ItemModel> getTestAdData() {
            ArrayList<ItemModel> models = new ArrayList<>();
            ChatModel model = new ChatModel();
            model.setContent("你好？我们交个朋友吧！");
            model.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg");
            models.add(new ItemModel(ItemModel.CHAT_A, model));
            ChatModel model2 = new ChatModel();
            model2.setContent("我是隔壁小王，你是谁？");
            model2.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
            models.add(new ItemModel(ItemModel.CHAT_B, model2));
            ChatModel model3 = new ChatModel();
            model3.setContent("what？你真不知道我是谁吗？哭~");
            model3.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg");
            models.add(new ItemModel(ItemModel.CHAT_A, model3));
            ChatModel model4 = new ChatModel();
            model4.setContent("大哥，别哭，我真不知道");
            model4.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
            models.add(new ItemModel(ItemModel.CHAT_B, model4));
            ChatModel model5 = new ChatModel();
            model5.setContent("卧槽，你不知道你来撩妹？");
            model5.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg");
            models.add(new ItemModel(ItemModel.CHAT_A, model5));
            ChatModel model6 = new ChatModel();
            model6.setContent("你是妹子，卧槽，我怎么没看出来？");
            model6.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
            models.add(new ItemModel(ItemModel.CHAT_B, model6));
            return models;
        }

    }

}

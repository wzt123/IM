package com.example.lsy.myapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;



public class FriendDataActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    private ImageButton headphoto;
    private Dialog dialog;
    private Uri imageUri;
    private Uri localUri = null;
    public static File tempFile;
    private String friendName;
    private int sendTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_data);
//        headphoto=(ImageButton)findViewById(R.id.head_photo);
//        headphoto.setOnClickListener(this);

        Button send_message=(Button)findViewById(R.id.btn_send);
        send_message.setOnClickListener(this);

        friendName=getIntent().getStringExtra("friendName");
        TextView friendname=(TextView)findViewById(R.id.friendname);
        friendname.setText(friendName);
        sendTopic=getIntent().getIntExtra("sendTopic",0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.head_photo:
//                dianji();
//                break;
//            case R.id.button_tuku:
//                getPicFromPhoto();
//                dialog.cancel();
//                break;
//            case R.id.button_paizhao:
//                getPicFromCamera();
//                dialog.cancel();
//                break;
            case R.id.btn_send:
                Intent intent=new Intent(FriendDataActivity.this,ChatActivity.class);
                intent.putExtra("friendName",friendName);
                intent.putExtra("sendTopic",sendTopic);
                startActivity(intent);
                break;

            default:break;
        }
    }


}

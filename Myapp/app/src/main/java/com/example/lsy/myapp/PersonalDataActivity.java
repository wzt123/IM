package com.example.lsy.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.File;

public class PersonalDataActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        ImageButton headphoto=(ImageButton)findViewById(R.id.head_photo);
        headphoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_photo:
                getPicFromCamera();
                break;
            default:break;
        }
    }
//    private void getPicFromPhoto() {
//        Intent intent = new Intent(Intent.ACTION_PICK, null);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                "image/*");
//        startActivityForResult(intent, PHOTO_REQUEST);
//    }

    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "test.jpg")));
        startActivityForResult(intent,CAMERA_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1://-1表示拍照成功
                        File file = new File(Environment.getExternalStorageDirectory()
                                + "/test.jpg");
                        if (file.exists()) {
                            photoClip(Uri.fromFile(file));
                        }
                        break;
                    default:
                        break;
                }
                break;
            case PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case PHOTO_CLIP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Log.w("test", "data");
                        Bitmap photo = extras.getParcelable("data");
                        mImageView.setImageBitmap(photo);
                    }
                }
                break;
            default:
                break;
        }

    }
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }
}

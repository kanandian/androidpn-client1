package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.androidpn.net.PostUploadRequest;
import org.androidpn.net.ResponseListener;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.FormImage;
import org.androidpn.utils.VolleyUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UploadImageActivity extends BaseActivity {

//    private static final int TAKE_PHOEO = 1;
//    private static final int CHOOSE_PHOEO = 2;
    private static final int IMAGE = 1;

    private ImageView imageView;

    private TextView chooseImageButton;
    private TextView uploadImageButton;

    private Uri imageUri;

    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload_image);

        initData();
    }

    public void initData() {
        imageView = (ImageView) findViewById(R.id.image_bussiness);
        chooseImageButton = (TextView) findViewById(R.id.btn_choose_image);
        uploadImageButton = (TextView) findViewById(R.id.btn_upload_image);


        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                File outputImage = new File(getExternalCacheDir(), "image_bussiness.jpg");
//
//                try {
//                    if (outputImage.exists()) {
//                        outputImage.delete();
//                    }
//                    outputImage.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if (Build.VERSION.SDK_INT >= 24) {
//                    imageUri = FileProvider.getUriForFile(UploadImageActivity.this, "com.example.cameraalbumtest.fileprovider", outputImage);
//                } else {
//                    imageUri = Uri.fromFile(outputImage);
//                }
//
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent, TAKE_PHOEO);



                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hostURL = ActivityHolder.getInstance().getConnection().getHost()+":8888/image.do";
                List<FormImage> imageList = new ArrayList<FormImage>() ;
                imageList.add(new FormImage(bitmap)) ;
                Request request = new PostUploadRequest(hostURL, imageList, new ResponseListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                    @Override
                    public void onResponse(Object response) {

                    }
                }) ;
                VolleyUtil.getInstance().getRequestQueue().add(request) ;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        bitmap = BitmapFactory.decodeFile(imaePath);
//        ((ImageView)findViewById(R.id.image)).setImageBitmap(bm);

        imageView.setImageBitmap(bitmap);


    }
}

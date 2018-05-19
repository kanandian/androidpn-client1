package org.androidpn.demoapp;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.androidpn.net.PostUploadRequest;
import org.androidpn.net.ResponseListener;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.FormImage;
import org.androidpn.utils.UserInfoHolder;
import org.androidpn.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;


public class UploadImageActivity extends BaseActivity {

//    private static final int TAKE_PHOEO = 1;
    private static final int CHOOSE_PHOEO = 2;
    private static final int IMAGE = 1;

    private ImageView imageView;

    private TextView chooseImageButton;
    private TextView uploadImageButton;

    private Uri imageUri;

    Bitmap bitmap = null;

    private String imageName;
    private String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload_image);

        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        imageName = intent.getStringExtra("imageName");
        target = intent.getStringExtra("target");

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


//                Intent intent = new Intent(
//                        Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, IMAGE);

                openAlbum();
            }
        });

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String hostURL = ""+ActivityHolder.getInstance().getConnection().getHost()+":8888/image.do";
                String hostURL = "http://"+ActivityHolder.getInstance().getConnection().getHost()+":8080/image.do";
                List<FormImage> imageList = new ArrayList<FormImage>() ;
                imageList.add(new FormImage(bitmap, target+":"+imageName +".png")) ;
                Request request = new PostUploadRequest(hostURL, imageList, new ResponseListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UploadImageActivity.this, "上传失败，请稍后再试！", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(Object response) {
                        if ("user".equals(target)) {
                            UserInfoHolder.getInstance().setImageURL("http://localhost:8080/bussinessimage/"+target+":"+imageName +".png");
                        }
                        UploadImageActivity.this.finish();
                    }
                }) ;
                VolleyUtil.getInstance().getRequestQueue().add(request) ;
            }
        });
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, CHOOSE_PHOEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
//        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumns = {MediaStore.Images.Media.DATA};
//            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
//            c.moveToFirst();
//            int columnIndex = c.getColumnIndex(filePathColumns[0]);
//            String imagePath = c.getString(columnIndex);
//            showImage(imagePath);
//            c.close();
//        }
        if (requestCode == CHOOSE_PHOEO) {
            if (resultCode == RESULT_OK) {
                handleImageOnKitKat(data);
            }
        }
    }

    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract. getDocumentId(uri);
            if ("com.android.providers.media.documents". equals(uri. getAuthority())) {
                String id = docId.split(":") [1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId) );
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        showImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;

        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
//        if (cursor != null) (
//
//            if (cursor.moveToFirst())(
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            )
//        cursor. close();
//        )

        return path;
    }

    //加载图片
    private void showImage(String imagePath){
        if (imagePath != null) {
            bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        }
    }
}

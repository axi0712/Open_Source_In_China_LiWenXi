package com.example.administrator.open_source_in_china_liwenxi.model.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.Activity_Retrofit;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class Activity_Build extends BaseActivity {
    private LinearLayout mFraBuildLin;
    private File file;
    private ImageView mFraBuildCanel;
    private TextView mFraBuildSend;
    private EditText mFraBuildEditContent;
    private ImageView mFraBuildPicture;
    private INewModel model;
    private SharedPreferences share;
    private void assignViews() {
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/head.jpg");
        share = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        model = new NewsModelImple();
        mFraBuildLin = (LinearLayout) findViewById(R.id.fra_build_lin);
        mFraBuildCanel = (ImageView) findViewById(R.id.fra_build_canel);
        mFraBuildCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mFraBuildSend = (TextView) findViewById(R.id.fra_build_send);
        mFraBuildSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetrofit();
            }
        });
        mFraBuildEditContent = (EditText) findViewById(R.id.fra_build_edit_content);
        mFraBuildPicture = (ImageView) findViewById(R.id.fra_build_picture);
        mFraBuildPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getImage();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_build;
    }

    @Override
    protected void getID() {
        assignViews();
    }

    @Override
    protected int getZujian() {
        return 0;
    }

    @Override
    protected void getOnClick() {

    }

    @Override
    protected void initDatas() {
        getRetrofit();
    }

    private void getRetrofit() {
        Toast.makeText(this, "正在发表", Toast.LENGTH_SHORT).show();
        if(test.isEmpty()){
            model.send_move(share.getString("uid",""),mFraBuildEditContent.getText().toString(),mFraBuildEditContent.getText().toString(),mFraBuildEditContent.getText().toString(), new MyCallBack() {
                @Override
                public void onErro(String strErro) {

                    Toast.makeText(Activity_Build.this, "发表失败", Toast.LENGTH_SHORT).show();
                    Log.i("fail_",strErro);
                }

                @Override
                public void onSuccess(String strSuccess) {
                    Toast.makeText(Activity_Build.this, "发表成功", Toast.LENGTH_SHORT).show();
                    Log.i("chengg ",strSuccess);
                }
            });
        }else{
            Map<String,String> map = new HashMap<>();
            map.put("uid",share.getString("uid",""));
            map.put("msg",mFraBuildEditContent.getText().toString());
            Activity_Retrofit.getInstance().Filed(map, file, "img", new MyCallBack() {
                @Override
                public void onErro(String strErro) {
                    Toast.makeText(Activity_Build.this, "发表失败", Toast.LENGTH_SHORT).show();
                    Log.i("fabiao",strErro);

                }


                @Override
                public void onSuccess(String strSuccess) {
                    Toast.makeText(Activity_Build.this, "发表图片成功", Toast.LENGTH_SHORT).show();
                    Log.i("chenggong",strSuccess);
                    finish();
                }
            });


        }


    }
    private void crop(Uri uri){
        Intent in = new Intent("com.android.camera.action.CROP");
        in.setDataAndType(uri,"image/*");
//        in.putExtra("output",Uri.fromFile(hea))
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           if(requestCode==200){
               getCaiJian(data.getData());

           }else if(requestCode==300){
//               Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
               test = file.toString();
           }

    }

    /**
     * 获取手机中的图片信息
     * @description：
     */
    private void getPicsFromPhone() {

        Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver cr = this.getContentResolver();
        Cursor cursor = cr.query(mImgUri, null, MediaStore.Images.Media.MIME_TYPE + "=?+ or" + MediaStore.Images.Media.MIME_TYPE + "=?", new String[] { "image/jpeg", "image/png" }, MediaStore.Images.Media.DATE_MODIFIED);
        while (cursor.moveToNext()) {
            String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));//图片路径
            File parentFile=new File(path).getParentFile();//图片所在文件
            if(parentFile==null){
                continue;
            }
            String dirPath=parentFile.getAbsolutePath();//文件路径
//接下来可以对图片或图片文件进行操作啦
        }


    }
private void getImage(){
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent,200);
}
private  void getCaiJian(Uri uri){
    Intent intent = new Intent("com.android.camera.action.CROP");
    intent.setDataAndType(uri,"image/*");
    intent.putExtra("crop",true);
    intent.putExtra("aspectX",1);
    intent.putExtra("aspectY",1);
    intent.putExtra("output", Uri.fromFile(file));
    startActivityForResult(intent,300);





}
private String test = "";
}

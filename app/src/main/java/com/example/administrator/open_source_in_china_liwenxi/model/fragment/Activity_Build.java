package com.example.administrator.open_source_in_china_liwenxi.model.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.open_source_in_china_liwenxi.MainActivity;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class Activity_Build extends BaseActivity {
    private LinearLayout mFraBuildLin;
    private ImageView mFraBuildCanel;
    private TextView mFraBuildSend;
    private EditText mFraBuildEditContent;
    private ImageView mFraBuildPicture;
    private INewModel model;
    private SharedPreferences share;

    private void assignViews() {
        share = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        model = new NewsModelImple();
        mFraBuildLin = (LinearLayout) findViewById(R.id.fra_build_lin);
        mFraBuildCanel = (ImageView) findViewById(R.id.fra_build_canel);
        mFraBuildCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Activity_Build.this, MainActivity.class);
                startActivity(in);
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
        model.send_move(share.getString("uid",""),mFraBuildEditContent.getText().toString(),mFraBuildEditContent.getText().toString(),mFraBuildEditContent.getText().toString(), new MyCallBack() {
            @Override
            public void onErro(String strErro) {
                Log.i("fail_",strErro);
            }

            @Override
            public void onSuccess(String strSuccess) {
                Log.i("chengg ",strSuccess);
            }
        });

    }

}

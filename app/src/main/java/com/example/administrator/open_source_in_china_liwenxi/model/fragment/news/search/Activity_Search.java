package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;


/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Activity_Search extends BaseActivity {
    private EditText mSearchEdit;
//    private Button mSearchBtn;
    private FrameLayout fram;
    private android.support.v4.app.FragmentManager man;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private MyManager mDB;
    private TextView mCancel;
    private void assignViews() {
        man = getSupportFragmentManager();
        mShared = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mShared.edit();
        mSearchEdit = (EditText) findViewById(R.id.search_edit);
//        mSearchBtn = (Button) findViewById(R.id.search_btn);
        fram = (FrameLayout) findViewById(R.id.fragment_frame);
        data();
        mDB = new MyManager(Activity_Search.this);
        mCancel = (TextView) findViewById(R.id.search_cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString("name",mSearchEdit.getText().toString());
                mEditor.commit();
                String name = mShared.getString("name", "");
                Boolean boo = mDB.insert(name);
                if(boo){
                    Log.i("添加++++++", name);
                }else{
                    Toast.makeText(Activity_Search.this, "失败", Toast.LENGTH_SHORT).show();
                }
                FragmentTransaction tra = man.beginTransaction();
                tra.replace(R.id.fragment_frame, new Fragment_Search_JieGuo());
                tra.commit();
                Toast.makeText(Activity_Search.this, "李文茜", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void data() {
        FragmentTransaction tra = man.beginTransaction();
        tra.replace(R.id.fragment_frame, new Fragment_Search_ListView());
        tra.commit();

    }


    @Override
    protected int getLayout() {
        return R.layout.activity_search;
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



    }
}

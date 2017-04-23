package com.example.administrator.open_source_in_china_liwenxi.model.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;


/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class Fragment_Mine extends BaseFragment {

    private ImageView mMineImageTouxiang;
    private RelativeLayout mMineMessage;
    private RelativeLayout mMineBlog;
    private RelativeLayout mMineAnswer;
    private RelativeLayout mMineAction;
    private RelativeLayout mMineTeam;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private TextView name;
    private String count;
    private INewModel model;
    private void assignViews() {

    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        model = new NewsModelImple();
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor = mShared.edit();
        name = (TextView) view.findViewById(R.id.mine_text_name);

        mMineImageTouxiang = (ImageView) view.findViewById(R.id.mine_image_touxiang);


        mMineMessage = (RelativeLayout) view.findViewById(R.id.mine_message);
        mMineBlog = (RelativeLayout) view.findViewById(R.id.mine_blog);
        mMineAnswer = (RelativeLayout) view.findViewById(R.id.mine_answer);
        mMineAction = (RelativeLayout)view.findViewById(R.id.mine_action);
        mMineTeam = (RelativeLayout) view.findViewById(R.id.mine_team);
    }

    @Override
    protected void initData() {

        Log.i("uid=====",mShared.getString("uid",""));
        Toast.makeText(getActivity(), mShared.getString("uid",""), Toast.LENGTH_SHORT).show();

       mMineImageTouxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent in = new Intent(getActivity().getApplicationContext(), Activity_Login.class);
                        startActivity(in);
                }
            });
    }

    private void initInfo() {
        Glide.with(getActivity()).load(mShared.getString("image","")).asBitmap().centerCrop().into(new BitmapImageViewTarget(mMineImageTouxiang) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                ciDrawable.setCircular(true);
                mMineImageTouxiang.setImageDrawable(ciDrawable);
            }
        });
        name.setText(mShared.getString("name",""));
        getInfo();
    }

    @Override
    protected void updateTitleBar() {
//        if (App.base instanceof MainActivity) {
//            ((MainActivity) App.base).getmMainRela().setVisibility(View.GONE);
//        }


    }
    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void onShow() {
        Glide.with(getActivity()).load(mShared.getString("image","")).asBitmap().centerCrop().into(new BitmapImageViewTarget(mMineImageTouxiang) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                ciDrawable.setCircular(true);
                mMineImageTouxiang.setImageDrawable(ciDrawable);
            }
        });

        getInfo();
    }
    private void getInfo() {
        model.login_Info(mShared.getString("uid",""), new MyCallBack() {
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

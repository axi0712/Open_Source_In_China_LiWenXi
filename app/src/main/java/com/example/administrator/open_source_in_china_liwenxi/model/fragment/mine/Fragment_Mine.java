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
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.MainActivity;
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
    private ImageView mView;
    private RelativeLayout mRela;
    private RelativeLayout mDongTan;
    private TextView mDongTanText, mShouCangText, mGuanZhuText, mFenSiText;
    private RelativeLayout mRleGuanZhu;

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
        mView = (ImageView) view.findViewById(R.id.login_setting);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.remove("uid");
                mEditor.commit();
                Toast.makeText(App.base, "注销", Toast.LENGTH_LONG).show();
                mMineImageTouxiang.setImageResource(R.mipmap.ic_launcher);
                name.setText("点击头像登录");
                mMineImageTouxiang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mShared.getString("uid", "").isEmpty()) {
                            Intent in = new Intent(getActivity().getApplicationContext(), Activity_Login.class);
                            startActivity(in);
                        }
                    }
                });
            }
        });
        mMineImageTouxiang = (ImageView) view.findViewById(R.id.mine_image_touxiang);
        mDongTanText = (TextView) view.findViewById(R.id.mine_dongtan);
        mShouCangText = (TextView) view.findViewById(R.id.mine_shoucang);
        mGuanZhuText = (TextView) view.findViewById(R.id.mine_guanzhu);
        mFenSiText = (TextView) view.findViewById(R.id.mine_fensi);
        mMineMessage = (RelativeLayout) view.findViewById(R.id.mine_message);
        mMineBlog = (RelativeLayout) view.findViewById(R.id.mine_blog);
        mMineAnswer = (RelativeLayout) view.findViewById(R.id.mine_answer);
        mMineAction = (RelativeLayout) view.findViewById(R.id.mine_action);
        mMineTeam = (RelativeLayout) view.findViewById(R.id.mine_team);
        mRela = (RelativeLayout) view.findViewById(R.id.mine_fensi_zong);
        mRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(App.base, Activity_FenSi.class);
                startActivity(in);
            }
        });
        mDongTan = (RelativeLayout) view.findViewById(R.id.dongtan_rela);
        mDongTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in = new Intent(App.base,Activity_DongTan.class);
                startActivity(in);
            }
        });
        mRleGuanZhu = (RelativeLayout) view.findViewById(R.id.item_guanzhu_rela);
        mRleGuanZhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(App.base,Activity_GuanZhu.class);
                startActivity(in);
            }
        });

    }

    //// TODO: 2017/4/26 0026  设置值
    private void initDatas() {
        mDongTanText.setText("11");
        mShouCangText.setText("0");
        mGuanZhuText.setText("5");
        mFenSiText.setText("7");
    }

    private void initKong() {
        mDongTanText.setText("0");
        mShouCangText.setText("0");
        mGuanZhuText.setText("0");
        mFenSiText.setText("0");
    }

    @Override
    protected void initData() {

        Log.i("uid=====", mShared.getString("uid", ""));
        Toast.makeText(getActivity(), mShared.getString("uid", ""), Toast.LENGTH_SHORT).show();
        if (mShared.getString("uid", "").isEmpty()) {
            mMineImageTouxiang.setImageResource(R.mipmap.ic_launcher);
            name.setText("点击头像登录");
            Toast.makeText(App.base, "请登录", Toast.LENGTH_SHORT).show();
            mMineImageTouxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mShared.getString("uid", "").isEmpty()) {
                        Intent in = new Intent(getActivity().getApplicationContext(), Activity_Login.class);
                        startActivity(in);
                        initKong();

                    } else {
                        initInfo();
                        initDatas();
                    }
                }
            });

        } else {
            Log.i("uid", "+++++" + mShared.getString("uid", ""));
            initInfo();
        }

    }

    private void initInfo() {
        Glide.with(getActivity()).load(mShared.getString("image", "")).asBitmap().centerCrop().into(new BitmapImageViewTarget(mMineImageTouxiang) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                ciDrawable.setCircular(true);
                mMineImageTouxiang.setImageDrawable(ciDrawable);
            }
        });
        Log.i("name_______", mShared.getString("names", ""));
        name.setText(mShared.getString("names", ""));
        getInfo();
    }

    @Override
    protected void updateTitleBar() {
        if (App.base instanceof MainActivity) {
            ((MainActivity) App.base).getmMainRela().setVisibility(View.GONE);
        }


    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void onHidden() {
        super.onHidden();
        updateTitleBar();
    }

    @Override
    public void onShow() {
        updateTitleBar();
        if (mShared.getString("uid", "").isEmpty()) {
            mMineImageTouxiang.setImageResource(R.mipmap.ic_launcher);
            name.setText("点击头像登录");
            Toast.makeText(App.base, "请登录", Toast.LENGTH_SHORT).show();
            mMineImageTouxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mShared.getString("uid", "").isEmpty()) {
                        Intent in = new Intent(getActivity().getApplicationContext(), Activity_Login.class);
                        startActivity(in);
                        initKong();


                    } else {
                        initInfo();
                        initDatas();
                    }
                }
            });

        } else {
            Log.i("uid", "+++++" + mShared.getString("uid", ""));
            initDatas();
            initInfo();
        }


//        Glide.with(getActivity()).load(mShared.getString("image","")).asBitmap().centerCrop().into(new BitmapImageViewTarget(mMineImageTouxiang) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
//                ciDrawable.setCircular(true);
//                mMineImageTouxiang.setImageDrawable(ciDrawable);
//            }
//        });
//        Log.i("name_______",mShared.getString("names",""));
//        name.setText(mShared.getString("names",""));
//        getInfo();
    }

    private void getInfo() {
        model.login_Info(mShared.getString("uid", ""), new MyCallBack() {
            @Override
            public void onErro(String strErro) {
                Log.i("fail_", strErro);
            }

            @Override
            public void onSuccess(String strSuccess) {
                Log.i("chengg ", strSuccess);
            }
        });

    }

}

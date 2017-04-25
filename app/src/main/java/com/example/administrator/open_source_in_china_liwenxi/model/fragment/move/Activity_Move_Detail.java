package com.example.administrator.open_source_in_china_liwenxi.model.fragment.move;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class Activity_Move_Detail extends BaseActivity{
    private LinearLayout mFraMoveDetailLin;
    private ImageView mFraNewsBlogCanel;
    private ImageView mMoveDetailsImage;
    private TextView mMoveDetailsTitle;
    private TextView mMoveDetailsBody;
    private TextView mMoveDetailTime;
    private TextView mMoveDetailZhunfa;
    private TextView mMoveDetailPinlun;
    private TextView mMoveDetailZan;
    private TabLayout mMoveDetailTab;
    private ViewPager mMoveDetailViewPager;
    private ImageView mBigImage;
    private void assignViews() {
        mFraMoveDetailLin = (LinearLayout) findViewById(R.id.fra_move_detail_lin);
        mFraNewsBlogCanel = (ImageView) findViewById(R.id.fra_news_blog_canel);
        mMoveDetailsImage = (ImageView) findViewById(R.id.move_details_image);
        mMoveDetailsTitle = (TextView) findViewById(R.id.move_details_title);
        mMoveDetailsBody = (TextView) findViewById(R.id.move_details_body);
        mMoveDetailTime = (TextView) findViewById(R.id.move_detail_time);
        mMoveDetailZhunfa = (TextView) findViewById(R.id.move_detail_zhunfa);
        mMoveDetailPinlun = (TextView) findViewById(R.id.move_detail_pinlun);
        mMoveDetailZan = (TextView) findViewById(R.id.move_detail_zan);
        mMoveDetailTab = (TabLayout) findViewById(R.id.move_detail_tab);
        mMoveDetailViewPager = (ViewPager) findViewById(R.id.move_detail_viewPager);
        mBigImage = (ImageView) findViewById(R.id.move_detail_BigImage);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_move_detail;
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
        SharedPreferences share = getSharedPreferences("data",MODE_PRIVATE);
        mMoveDetailsBody.setText(share.getString("body",""));
        mMoveDetailsTitle.setText(share.getString("name",""));
        mMoveDetailTime.setText(share.getString("time",""));
        mMoveDetailZan.setText(share.getString("zan",""));
       String image = share.getString("images","");
        Log.i("foodsviodsoivoi",image);
        Glide.with(this).load(image).asBitmap().centerCrop().into(new BitmapImageViewTarget(mMoveDetailsImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                ciDrawable.setCircular(true);
                mMoveDetailsImage.setImageDrawable(ciDrawable);
            }
        });
        String BigImage = share.getString("BigImage","");
        Glide.with(this).load(BigImage).diskCacheStrategy(DiskCacheStrategy.ALL).into(mBigImage);
    }
}

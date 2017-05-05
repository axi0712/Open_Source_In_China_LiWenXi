package com.example.administrator.open_source_in_china_liwenxi.model.fragment.move;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Move_NewJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.Fragment_NewsAdapter;

import java.util.ArrayList;
import java.util.List;

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
    private TabLayout mNewsTab;
    private ViewPager mView;
    private ImageView mBigImage;
    private SharedPreferences share;
    private List<Fragment> mFraList = new ArrayList<>();
    private List<String> mStrList = new ArrayList<>();
    private Fragment_Detail_Zan blog;

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
        mNewsTab = (TabLayout) findViewById(R.id.move_detail_tab);
        mView = (ViewPager) findViewById(R.id.move_detail_viewPager);
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
        share = getSharedPreferences("data",MODE_PRIVATE);
        Fragment_Detail_Pinlun soft = new Fragment_Detail_Pinlun();
        blog = new Fragment_Detail_Zan();
        initChuan();

        mFraList.add(soft);
        mFraList.add(blog);

        mStrList.add("评论");
        mStrList.add("赞");

        mNewsTab.setTabMode(TabLayout.MODE_FIXED);
        Fragment_NewsAdapter mAdapter = new Fragment_NewsAdapter(getSupportFragmentManager(), mFraList, mStrList);
        mView.setAdapter(mAdapter);

        mNewsTab.setupWithViewPager(mView);

    }
    //TODO 传值
    private void initChuan() {
        Intent in = getIntent();
        ArrayList<Move_NewJavaBean.TweetBean.UserBean> list= in.getParcelableArrayListExtra("listlk");
        blog.setmList(list);

        mMoveDetailsBody.setText(share.getString("body",""));
        mMoveDetailsTitle.setText(share.getString("name",""));
        mMoveDetailTime.setText(share.getString("time",""));
        mMoveDetailZan.setText(share.getString("zan",""));
        String image = share.getString("TouXiang","");
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

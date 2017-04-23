package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.soft;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.open_source_in_china_liwenxi.MainActivity;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16 0016.
 */

public class Activity_Soft extends BaseActivity {
    private ImageView mSoftCanel;
    private TabLayout mTab;
    private ViewPager mView;
    private List<Fragment> mFraList = new ArrayList<>();
    private List<String> mStrList = new ArrayList<>();
    private void assignViews() {
        mSoftCanel = (ImageView) findViewById(R.id.soft_canel);
        mSoftCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Activity_Soft.this, MainActivity.class);
                startActivity(in);

            }
        });
        mTab = (TabLayout) findViewById(R.id.soft_tab);
        mView = (ViewPager) findViewById(R.id.soft_view);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_soft;
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
        Fragment_fenlei_zong fenlei = new Fragment_fenlei_zong();
        Fragment_Recommend recommend = new Fragment_Recommend();
        Fragment_News news = new Fragment_News();
        Fragment_Hot hot = new Fragment_Hot();
        Fragment_china china = new Fragment_china();
        mFraList.add(fenlei);
        mFraList.add(recommend);
        mFraList.add(news);
        mFraList.add(hot);
        mFraList.add(china);
        mStrList.add("分类");
        mStrList.add("推荐");
        mStrList.add("最新");
        mStrList.add("热门");
        mStrList.add("国产");
        Fragment_SoftAdapter mAdapter = new Fragment_SoftAdapter(getSupportFragmentManager(), mFraList, mStrList);
        mView.setAdapter(mAdapter);
        mView.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        mTab.setupWithViewPager(mView);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    public void onBackPressed() {
        if(mView.getCurrentItem()!=0){
         finish();
        }else{
          super.onBackPressed();
        }
    }
}

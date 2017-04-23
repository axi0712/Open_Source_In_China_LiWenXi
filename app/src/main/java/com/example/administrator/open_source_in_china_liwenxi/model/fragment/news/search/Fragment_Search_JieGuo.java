package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.Fragment_NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Fragment_Search_JieGuo extends BaseFragment {
    private TabLayout mNewsTab;
    private ViewPager mView;
    private List<Fragment> mFraList = new ArrayList<>();
    private List<String> mStrList = new ArrayList<>();


    @Override
    protected int layoutId() {
        return R.layout.fragment_search_jieguo;
    }

    @Override
    protected void initView(View view) {
        mNewsTab = (TabLayout) view.findViewById(R.id.fragment_search_jieguo_tab);
        mView = (ViewPager) view.findViewById(R.id.fragment_search_jieguo_viewpager);
    }

    @Override
    protected void initData() {
        Fragment_Search_SoftWare soft = new Fragment_Search_SoftWare();
        Fragment_Search_blog blog = new Fragment_Search_blog();
        Fragment_Search_Post point = new Fragment_Search_Post();
        Fragment_Search_News recommed = new Fragment_Search_News();
        Fragment_search_person person = new Fragment_search_person();
        mFraList.add(soft);
        mFraList.add(blog);
        mFraList.add(point);
        mFraList.add(recommed);
        mFraList.add(person);
        mStrList.add("软件");
        mStrList.add("博客");
        mStrList.add("问答");
        mStrList.add("资讯");
        mStrList.add("找人");
        Fragment_NewsAdapter mAdapter = new Fragment_NewsAdapter(getActivity().getSupportFragmentManager(), mFraList, mStrList);
        mView.setAdapter(mAdapter);
        mView.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mNewsTab) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        mNewsTab.setupWithViewPager(mView);
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
}

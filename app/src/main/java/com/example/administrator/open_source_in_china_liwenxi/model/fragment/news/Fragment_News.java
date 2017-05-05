package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.MainActivity;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class Fragment_News extends BaseFragment {
    private TabLayout mNewsTab;
    private ViewPager mView;
    private List<Fragment> mFraList = new ArrayList<>();
    private List<String> mStrList = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View v) {

        mNewsTab = (TabLayout) v.findViewById(R.id.news_tab);
        mView = (ViewPager) v.findViewById(R.id.news_view);

    }

    @Override
    protected void initData() {

//        mStrList.add("开源咨询");
//        mStrList.add("开源咨询");
//        mStrList.add("技术问答");
//        mStrList.add("每日一博");
//        Toast.makeText(getActivity().getApplication().getApplicationContext(), "这是我的fragment", Toast.LENGTH_SHORT).show();
//        mNewsTab.setTabMode(TabLayout.MODE_SCROLLABLE);
//        mNewsTab.addTab(mNewsTab.newTab().setText(mStrList.get(0)));
//        mNewsTab.addTab(mNewsTab.newTab().setText(mStrList.get(1)));
//        mNewsTab.addTab(mNewsTab.newTab().setText(mStrList.get(2)));
//        mNewsTab.addTab(mNewsTab.newTab().setText(mStrList.get(3)));
//        mView.setAdapter(new Fragment_NewsAdapter(getActivity().getSupportFragmentManager(),mFraList,mStrList));
//        mNewsTab.setupWithViewPager(mView);

        Fragment_News_OpenSource soft = new Fragment_News_OpenSource();
        Fragment_news_Blog blog = new Fragment_news_Blog();
        Fragment_News_Point point = new Fragment_News_Point();
        Fragment_News_Recommed recommed = new Fragment_News_Recommed();
        Fragment_News_Answer answer = new Fragment_News_Answer();
        mFraList.add(soft);
        mFraList.add(blog);
        mFraList.add(point);
        mFraList.add(recommed);
        mFraList.add(answer);
        mStrList.add("资讯");
        mStrList.add("博客");
        mStrList.add("热点");
        mStrList.add("推荐");
        mStrList.add("技术问答");
        mNewsTab.setTabMode(TabLayout.MODE_SCROLLABLE);
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

//    @Override
//    protected void updateTitleBar() {
//        if (App.base instanceof MainActivity)
//            ((MainActivity) App.base).getTitlt().setText("综合");
//    }
@Override
protected void updateTitleBar() {

    if (App.base instanceof MainActivity) {
        //显示
        ((MainActivity) App.base).getTitlt().setVisibility(View.VISIBLE);
        ((MainActivity) App.base).getmMainRela().setVisibility(View.VISIBLE);

    }
    if (App.base instanceof MainActivity) {
        ((MainActivity) App.base).getTitlt().setText("综合");
    }

}

    @Override
    public void onShow() {
        super.onShow();
        updateTitleBar();
    }

    @Override
    public void onHidden() {
        super.onHidden();
        updateTitleBar();
    }

    @Override
    public void setParams(Bundle bundle) {
        String test = bundle.getString("test");
        Log.d("NewsFragment", test);
    }


}

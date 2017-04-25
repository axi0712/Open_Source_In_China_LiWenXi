package com.example.administrator.open_source_in_china_liwenxi.model.fragment.move;

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
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.Fragment_NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class Fragment_Move extends BaseFragment {
    private TabLayout mNewsTab;
    private ViewPager mView;
    private List<Fragment> mFraList = new ArrayList<>();
    private List<String> mStrList = new ArrayList<>();
    @Override
    protected int layoutId() {
        return R.layout.fragment_move;
    }

    @Override
    protected void initView(View v) {
        mNewsTab = (TabLayout) v.findViewById(R.id.move_tab);
        mView = (ViewPager) v.findViewById(R.id.move_view);
    }

    @Override
    protected void initData() {
        Fragment_move_News point = new Fragment_move_News();
        Fragment_Move_Mine hot = new Fragment_Move_Mine();
        Fragment_move_Hots news = new Fragment_move_Hots();
        mFraList.add(point);
        mFraList.add(news);
        mFraList.add(hot);
        mStrList.add("最新动弹");
        mStrList.add("热点动弹");
        mStrList.add("我的动弹");
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
        if(App.base instanceof MainActivity)
            ((MainActivity)App.base).getTitlt().setText("动弹");
    }

    @Override
    public void setParams(Bundle bundle) {
        String test = bundle.getString("test");
        Log.d("NewsFragment", test);
    }
}

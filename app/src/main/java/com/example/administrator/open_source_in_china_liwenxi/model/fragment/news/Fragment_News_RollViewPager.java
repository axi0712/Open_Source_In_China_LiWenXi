package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class Fragment_News_RollViewPager extends BaseFragment {
    private RollPagerView mRoll;
    @Override
    protected int layoutId() {
        return R.layout.fragemnt_news_rollviewpager;
    }

    @Override
    protected void initView(View view) {
        mRoll = (RollPagerView) view.findViewById(R.id.fragment_news_viewPager);
        RollViewPager();
    }
    private void RollViewPager() {
        //设置播放时间间隔
        mRoll.setPlayDelay(1000);
        //设置透明度
        mRoll.setAnimationDurtion(500);
        //设置适配器
        mRoll.setAdapter(new RollAdapter());
        //自定义只是图片
        mRoll.setHintView(new ColorPointHintView(getActivity().getApplicationContext(), Color.YELLOW,Color.WHITE));

    }
    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    class RollAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.mipmap.purple_sky,
                R.mipmap.blue_windy,
                R.mipmap.sea,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }

    }
}

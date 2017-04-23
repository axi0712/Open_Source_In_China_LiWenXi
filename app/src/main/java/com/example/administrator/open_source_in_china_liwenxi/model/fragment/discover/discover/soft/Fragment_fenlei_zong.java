package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.soft;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;


/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class Fragment_fenlei_zong extends BaseFragment {
    private FrameLayout mFrame;
    private FragmentManager man;

    @Override
    protected int layoutId() {
        return R.layout.fragment_fenlei_zong;
    }

    @Override
    protected void initView(View view) {

        mFrame = (FrameLayout) view.findViewById(R.id.fenlei_frame);
        man = getActivity().getSupportFragmentManager();
        FragmentTransaction tra = man.beginTransaction();
        tra.replace(R.id.fenlei_frame,new Fragment_fenlei());
        tra.commit();
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
}

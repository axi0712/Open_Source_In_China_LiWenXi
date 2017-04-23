package com.example.administrator.open_source_in_china_liwenxi.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class FragmentBuilder {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BaseFragment fragment;

    private FragmentBuilder() {
        init();
    }

    ;
    private static FragmentBuilder fragmentBuilder;

    public synchronized static FragmentBuilder getInstance() {
        if (fragmentBuilder == null) {
            fragmentBuilder = new FragmentBuilder();

        }
        return fragmentBuilder;
    }

    private void init() {

        fragmentManager = App.base.getSupportFragmentManager();

    }

    public FragmentBuilder start(Class<? extends BaseFragment> fragmentClass) {
        transaction = fragmentManager.beginTransaction();
        //使用Fragment类名做tag；
        String simpleName=fragmentClass.getSimpleName();
        //根据tag查找fragment 如果能找到就代表fragment已经实例化了，否则动态实例化；
        fragment  = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);
        if (fragment==null){
            try {
                fragment=fragmentClass.newInstance();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.main_frame,fragment,simpleName);
        }
        //隐藏上一个fragment
        if(App.lastFragment!=null){
            transaction.hide(App.lastFragment);
        }
        //判断是否已经添加 如果没有添加过就添加


        //已经添加就调用show方法显示；
        transaction.addToBackStack(simpleName);
        transaction.show(fragment);
        return this;
    }

    public FragmentBuilder params(Bundle params) {
        if (params != null) {
            fragment.setParams(params);
        }

        return this;
    }


    public BaseFragment build() {
        App.lastFragment = fragment;
        transaction.commit();
        return fragment;
    }
}

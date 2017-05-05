package com.example.administrator.open_source_in_china_liwenxi.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.open_source_in_china_liwenxi.App;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Fragment Lastfra;//用于上一次显示的fragment
    private FragmentManager man;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        App.base = this;
        man = getSupportFragmentManager();
        getID();
        getOnClick();
        initDatas();
    }
    //找布局
    protected abstract int getLayout();
    //找布局id
    protected  abstract  void getID();
    //初始化组件
    protected  abstract  int getZujian()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             ;
    //实现监听
    protected abstract void getOnClick();
    //加载数据
    protected abstract void initDatas();
    //把你的fragment加到视图上  你要现实的视图    要传递的数据   是否可以返回  要显示在那布局上
    public void getFragment(Fragment fra, Bundle bun, Boolean isBack, int ViewID) {
        //你得判断这个Fragment是否为空
        if(fra==null){
            Toast.makeText(this, "为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //实现管理者

        //开启Fragment事务
        FragmentTransaction tra = man.beginTransaction();
//        判断是否为空
        //不为空  就隐藏他   为空就添加
        if(Lastfra != null){
            Toast.makeText(this, "隐藏", Toast.LENGTH_SHORT).show();
            tra.hide(Lastfra);
        }
        //开始 判断当前的fragment  有没有被添加到这个事务里
//         如果没有就添加进去  并显示   如果有直接显示
        if(!fra.isAdded()){
            tra.add(ViewID,fra,null);
            tra.show(fra);
            Toast.makeText(this, "我添加进去了", Toast.LENGTH_SHORT).show();
        }else{
            tra.show(fra);
            Toast.makeText(this, "我重新显示", Toast.LENGTH_SHORT).show();
        }
        //把所提交的事务发给管理者
        tra.commit();
        Lastfra = fra;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        App.base = this;
//        initDatas();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

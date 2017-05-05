package com.example.administrator.open_source_in_china_liwenxi.model.fragment.mine;

import android.content.SharedPreferences;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.adapter.NewsDongTanAdapter;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Move_NewJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class Activity_DongTan extends BaseActivity {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<Move_NewJavaBean.TweetBean> mList = new ArrayList<>();
    private int pageIndex = 1;
    private INewModel model = null;
    private ImageView mCancle;
    private NewsDongTanAdapter newsDongTanAdapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_dongtan;
    }

    @Override
    protected void getID() {
        mView = (PullToRefreshRecyclerView) findViewById(R.id.activity_dongtan_recycle);
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();
        model = new NewsModelImple();
        mCancle = (ImageView) findViewById(R.id.activity_dongtan_canel);
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newsDongTanAdapter = new NewsDongTanAdapter(App.base,mList);
//        pageIndex = mShared.getInt("Index", 1);
        Log.e("knakn",pageIndex+"");
        loadMode();

//        pageIndex++;
//        mEditor.putInt("Index", pageIndex);
//        mEditor.commit();
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
//分割线
        mView.addItemDecoration(new DividerItemDecoration(App.base,DividerItemDecoration.VERTICAL));

        mView.setLayoutManager(layout);
        mView.setPullRefreshEnabled(true);//下拉刷新
        mView.setLoadingMoreEnabled(true);//上拉加载
        mView.displayLastRefreshTime(true);//显示上次刷新的时间
        mView.setAdapter( newsDongTanAdapter);
        //设置刷新回调
        mView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                mView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex = 0;
                        mList.clear();
//                        for (int i = 1; i <= pageIndex; i++) {
                        loadMode();
//                        }
                        mView.setRefreshComplete();

//                        mEditor.putInt("Index", pageIndex);
//                        mEditor.commit();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex++;


                        loadMode();
                        mView.setLoadMoreComplete();
//                        mEditor.putInt("Index", pageIndex);
//                        Log.i("加载", pageIndex + "");
//                        mEditor.commit();
                    }
                }, 2000);
            }
        });
    }
    private void loadMode() {
        if (mShared.getString("uid", "").isEmpty()) {
            Toast.makeText(App.base, "请先登录", Toast.LENGTH_LONG).show();
        } else {
            model.move_mine(mShared.getString("uid", ""), "0", new MyCallBack() {
                @Override
                public void onErro(String strErro) {

                }

                //
                @Override
                public void onSuccess(String strSuccess) {
                    Log.e("shitr", strSuccess);
                    XStream xs = new XStream();
                    xs.alias("oschina", Move_NewJavaBean.class);
                    xs.alias("tweet", Move_NewJavaBean.TweetBean.class);
                    xs.alias("user", Move_NewJavaBean.TweetBean.UserBean.class);
                    Move_NewJavaBean homeListBean = (Move_NewJavaBean) xs.fromXML(strSuccess);
                    mList.addAll(homeListBean.getTweets());
                    Log.i("mine+++++++", homeListBean.getTweets().toString());
                    newsDongTanAdapter.notifyDataSetChanged();
                }
            });
        }
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

    }
}

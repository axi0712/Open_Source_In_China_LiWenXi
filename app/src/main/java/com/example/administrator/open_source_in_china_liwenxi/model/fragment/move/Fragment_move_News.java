package com.example.administrator.open_source_in_china_liwenxi.model.fragment.move;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.adapter.MyContentLinearLayoutManager;
import com.example.administrator.open_source_in_china_liwenxi.model.adapter.NewsDongTanAdapter;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Move_NewJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class Fragment_move_News extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<Move_NewJavaBean.TweetBean> mList ;
    private int pageIndex = 0;
    private INewModel model = null;
    private String isLike;
    private ImageView zanImageView;
private NewsDongTanAdapter newsDongTanAdapter;
    @Override
    protected int layoutId() {
        return R.layout.fragment_move_point;
    }

    @Override
    protected void initView(View view) {
        mView = (PullToRefreshRecyclerView) view.findViewById(R.id.move_point_recycle);
        mShared = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();
        mList = new ArrayList<>();
        newsDongTanAdapter = new NewsDongTanAdapter(App.base,mList);
        model = new NewsModelImple();
//        pageIndex = mShared.getInt("Index", 1);
        Log.e("knakn", pageIndex + "");
        loadMode();

//        pageIndex++;
//        mEditor.putInt("Index", pageIndex);
//        mEditor.commit();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity().getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
//分割线
        mView.addItemDecoration(new DividerItemDecoration(App.base,DividerItemDecoration.VERTICAL));
        mView.setLayoutManager(new MyContentLinearLayoutManager(mView.getContext()));
        mView.setPullRefreshEnabled(true);//下拉刷新
        mView.setLoadingMoreEnabled(true);//上拉加载
        mView.displayLastRefreshTime(true);//显示上次刷新的时间
        mView.setAdapter( newsDongTanAdapter);
        //设置刷新回调
        mView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                mView.post(new Runnable() {
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
                });
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
        model.move_new(String.valueOf(pageIndex), new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                Log.e("shitr", strSuccess);
                XStream xs = new XStream();
                xs.alias("oschina", Move_NewJavaBean.class);
                xs.alias("tweet", Move_NewJavaBean.TweetBean.class);
                xs.alias("user",Move_NewJavaBean.TweetBean.UserBean.class);
                Move_NewJavaBean homeListBean = (Move_NewJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getTweets());
                Log.i("mine+++++++",homeListBean.getTweets().toString());
             newsDongTanAdapter.notifyDataSetChanged();
                Log.d("wodedededde",strSuccess);
             }
        });
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

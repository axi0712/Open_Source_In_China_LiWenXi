package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Search_SoftWareJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Fragment_Search_Post extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<Search_SoftWareJavaBean.ResultBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private int pageIndex = 1;
    private INewModel model = null;
    @Override
    protected int layoutId() {
        return R.layout.fragment_search_post;
    }

    @Override
    protected void initView(View view) {
        mView = (PullToRefreshRecyclerView) view.findViewById(R.id.search_post_recycle);
        model = new NewsModelImple();
    }

    @Override
    protected void initData() {
        mShared = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();

        loadMode();
        mShared.getString("name","");
        Log.i("name",mShared.getString("name",null));
//        pageIndex++;
//        mEditor.putInt("Index", pageIndex);
//        mEditor.commit();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity().getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        mView.setLayoutManager(layout);
        mView.addItemDecoration(new DividerItemDecoration(App.base, DividerItemDecoration.VERTICAL));
//        mView.setPullRefreshEnabled(true);//下拉刷新
//        mView.setLoadingMoreEnabled(true);//上拉加载
//        mView.displayLastRefreshTime(true);//显示上次刷新的时间
//        //设置刷新回调
//        mView.setPullToRefreshListener(new PullToRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.setRefreshComplete();
//                        mList.clear();
//                        for(int i = 1;i<=pageIndex;i++){
//                            loadMode();
//                        }
//
//
//                        mEditor.putInt("Index",pageIndex);
//                        mEditor.commit();
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public void onLoadMore() {
//                mView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        pageIndex++;
//                        mView.setLoadMoreComplete();
//
//                        loadMode();
//                        mEditor.putInt("Index",pageIndex);
//                        Log.i("加载",pageIndex+"");
//                        mEditor.commit();
//                    }
//                }, 2000);
//            }
//        });
    }
    private void loadMode() {
        model.search_News(mShared.getString("name",""),"2", new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Search_SoftWareJavaBean.class);
                xs.alias("result",Search_SoftWareJavaBean.ResultBean.class);
                Search_SoftWareJavaBean homeListBean = (Search_SoftWareJavaBean) xs.fromXML(strSuccess);
//                BlogJavaBean homeListBean = (BlogJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getResults());
                mAdapter = new MyAdapter(getActivity().getApplicationContext(),R.layout.fragment_search_item,mList);
                mView.setAdapter(mAdapter);
                Log.i("多少",mAdapter.getItemCount()+"");
                Log.i("KANKANPOST",homeListBean.getResults().toString());
            }
        });
    }
    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    class MyAdapter extends BaseAdapter<Search_SoftWareJavaBean.ResultBean> {

        public MyAdapter(Context context, int layoutId, List<Search_SoftWareJavaBean.ResultBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final Search_SoftWareJavaBean.ResultBean javaBean) {
            holder.setText(R.id.fragment_search_item_text, javaBean.getTitle() + "");
            holder.setText(R.id.fragment_search_item_body, javaBean.getDescription() + "");
//            holder.setOnclickListener(R.id.news_open, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent in = new Intent(MainActivity.this,Activity_Details.class);
//                    in.putExtra("details",javaBean.getId()+"");

//                      当不是内部类时
//                      String id = newsBean.getId();
//                      holder.getLayoutPosition();
//                      NewsListBean.NewsBean newsBean1 = datas.get(holder.getLayoutPosition());
//                    startActivity(in);
//                }
//            });
        }
    }
}

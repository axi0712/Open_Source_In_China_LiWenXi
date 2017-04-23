package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.RecommedJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.example.administrator.open_source_in_china_liwenxi.utils.Dates;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class Fragment_News_Recommed extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<RecommedJavaBean.BlogBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private int pageIndex = 1;
    private INewModel model = null;

    @Override
    protected int layoutId() {
        return R.layout.fragment_news_recommed;
    }

    @Override
    protected void initView(View view) {
        mView = (PullToRefreshRecyclerView) view.findViewById(R.id.news_recommed_recycle);
        mShared = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();
        model = new NewsModelImple();
//        pageIndex = mShared.getInt("Index", 1);
        Log.e("knakn",pageIndex+"");
        loadMode();

//        pageIndex++;
//        mEditor.putInt("Index", pageIndex);
//        mEditor.commit();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity().getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        mView.setLayoutManager(layout);
        mView.setPullRefreshEnabled(true);//下拉刷新
        mView.setLoadingMoreEnabled(true);//上拉加载
        mView.displayLastRefreshTime(true);//显示上次刷新的时间
        //设置刷新回调
        mView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                mView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mView.setRefreshComplete();
                        mList.clear();
                        for(int i = 1;i<=pageIndex;i++){
                            loadMode();
                        }


                        mEditor.putInt("Index",pageIndex);
                        mEditor.commit();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex++;
                        mView.setLoadMoreComplete();

                        loadMode();
                        mEditor.putInt("Index",pageIndex);
                        Log.i("加载",pageIndex+"");
                        mEditor.commit();
                    }
                }, 2000);
            }
        });
    }

    private void loadMode() {
        model.recommed("1", new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", RecommedJavaBean.class);
                xs.alias("blog",RecommedJavaBean.BlogBean.class);
                RecommedJavaBean homeListBean = (RecommedJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getBlogs());
                mAdapter =new MyAdapter(getActivity().getApplicationContext(),R.layout.fragment_news_opensource_item,mList);
                mView.setAdapter(mAdapter);
                Log.i("多少",mAdapter.getItemCount()+"");
                Log.i("KANKAN",homeListBean.getBlogs().toString());
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
        String test = bundle.getString("test");
        Log.d("PointFragment", test);
    }
    class MyAdapter extends BaseAdapter<RecommedJavaBean.BlogBean> {

        public MyAdapter(Context context, int layoutId, List<RecommedJavaBean.BlogBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final RecommedJavaBean.BlogBean javaBean) {
            holder.setText(R.id.news_open_item_title, javaBean.getTitle() + "");
            holder.setText(R.id.news_open_item_body, javaBean.getBody() + "");
            String date = Dates.getDate(javaBean.getPubDate());
            Log.i("date________", date);
            holder.setText(R.id.main_news_time, date);
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

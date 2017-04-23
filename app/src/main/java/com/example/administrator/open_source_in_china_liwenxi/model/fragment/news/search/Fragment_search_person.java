package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Search_PersonJavabean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class Fragment_search_person extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<Search_PersonJavabean.UserBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private int pageIndex = 1;
    private INewModel model ;
    @Override
    protected int layoutId() {
        return R.layout.fragment_search_person;
    }

    @Override
    protected void initView(View view) {
     mView = (PullToRefreshRecyclerView) view.findViewById(R.id.fragment_search_person_view);
        model = new NewsModelImple();
    }

    @Override
    protected void initData() {
        mShared = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();

        loadMode();
        mShared.getString("name","");
        Log.i("name",mShared.getString("name",""));
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
        model.search_person(mShared.getString("name",""), new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Search_PersonJavabean.class);
                xs.alias("user",Search_PersonJavabean.UserBean.class);
                Search_PersonJavabean homeListBean = (Search_PersonJavabean) xs.fromXML(strSuccess);
//                BlogJavaBean homeListBean = (BlogJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getUsers());
                mAdapter = new MyAdapter(getActivity().getApplicationContext(),R.layout.fragment_search_person_item,mList);
                mView.setAdapter(mAdapter);
                Log.i("多少",mAdapter.getItemCount()+"");
                Log.i("KANKAN",homeListBean.getUsers().toString());
            }
        });
    }
    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    class MyAdapter extends BaseAdapter<Search_PersonJavabean.UserBean> {

        public MyAdapter(Context context, int layoutId, List<Search_PersonJavabean.UserBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final Search_PersonJavabean.UserBean javaBean) {
            final ImageView im = (ImageView) holder.itemView.findViewById(R.id.person_item_image);
            Glide.with(getActivity()).load(javaBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(im) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    ciDrawable.setCircular(true);
                    im.setImageDrawable(ciDrawable);
                }
            });
            holder.setText(R.id.person_item_name, javaBean.getName() + "");
            holder.setText(R.id.person_item_address, javaBean.getFrom() + "");
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

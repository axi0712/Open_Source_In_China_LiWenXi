package com.example.administrator.open_source_in_china_liwenxi.model.fragment.move;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Move_NewJavaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class Fragment_Detail_Zan extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private MyAdapter myAdapter;
    private ArrayList<Move_NewJavaBean.TweetBean.UserBean> mList = new ArrayList<>();

    public ArrayList<Move_NewJavaBean.TweetBean.UserBean> getmList() {
        myAdapter.notifyDataSetChanged();
        return mList;
    }

    public void setmList(ArrayList<Move_NewJavaBean.TweetBean.UserBean> mList) {
        this.mList = mList;
    }
    @Override
    protected int layoutId() {
        return R.layout.fragment_detail_zan;
    }

    @Override
    protected void initView(View view) {
     mView = (PullToRefreshRecyclerView) view.findViewById(R.id.fragment_detail_zan);
        RecyclerView.LayoutManager manager =new LinearLayoutManager(App.base);
        mView.setLayoutManager(manager);
//分割线
        mView.addItemDecoration(new DividerItemDecoration(App.base,DividerItemDecoration.VERTICAL));


//        mView.setPullRefreshEnabled(true);//下拉刷新
//        mView.setLoadingMoreEnabled(true);//上拉加载
//        mView.displayLastRefreshTime(true);//显示上次刷新的时间

        myAdapter = new MyAdapter(App.base,mList);
        Log.i("list",mList.toString());
        mView.setAdapter(myAdapter);

        //设置刷新回调
//        mView.setPullToRefreshListener(new PullToRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        pageIndex = 0;
//                        mList.clear();
////                        for (int i = 1; i <= pageIndex; i++) {
//                        loadMode();
////                        }
//                        mView.setRefreshComplete();
//
////                        mEditor.putInt("Index", pageIndex);
////                        mEditor.commit();
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
//
//
//                        loadMode();
//                        mView.setLoadMoreComplete();
////                        mEditor.putInt("Index", pageIndex);
////                        Log.i("加载", pageIndex + "");
////                        mEditor.commit();
//                    }
//                }, 2000);
//            }
//        });
//    }
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
    class MyAdapter extends BaseAdapter<Move_NewJavaBean.TweetBean.UserBean> {

        public MyAdapter(Context context,  List<Move_NewJavaBean.TweetBean.UserBean> datas) {
            super(context, R.layout.fragment_detail_zan_item, datas);
        }

        @Override
        public void convert(ViewHolder holder, final Move_NewJavaBean.TweetBean.UserBean javaBean) {
//            if(javaBean.getPortrait().isEmpty()){
//                im.setImageResource(R.mipmap.ic_launcher);
//            }else {

//            }
           holder.setText(R.id.item_detail_zan_title,javaBean.getName());
            final ImageView im = holder.getView(R.id.item_detail_zan_img);
            Glide.with(App.base).load(javaBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(im) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(App.base.getResources(), resource);
                    ciDrawable.setCircular(true);
                    im.setImageDrawable(ciDrawable);
                }
            });

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
//
//                }
//            });
        }
    }
}

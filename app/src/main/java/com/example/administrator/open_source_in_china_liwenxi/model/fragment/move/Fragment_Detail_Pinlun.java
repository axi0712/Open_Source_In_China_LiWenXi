package com.example.administrator.open_source_in_china_liwenxi.model.fragment.move;

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
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Move_Detail_PinLunJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.example.administrator.open_source_in_china_liwenxi.utils.Dates;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class Fragment_Detail_Pinlun extends BaseFragment {

   private PullToRefreshRecyclerView mView;
 private SharedPreferences mShared;
 private SharedPreferences.Editor mEditor;
 private ArrayList<Move_Detail_PinLunJavaBean.CommentBean> mList = new ArrayList<>();
 private MyAdapter mAdapter;
 private int pageIndex = 1;
 private INewModel model = null;

    @Override
    protected int layoutId() {
        return R.layout.fragment_detail_pinlun;
    }

    @Override
    protected void initView(View view) {
        mView = (PullToRefreshRecyclerView) view.findViewById(R.id.fragment_detail_pinlun);
     mShared = getActivity().getSharedPreferences("data", MODE_PRIVATE);
     mEditor = mShared.edit();
     model = new NewsModelImple();
//        pageIndex = mShared.getInt("Index", 1);
     Log.e("knaknPinLun",pageIndex+"");
     loadMode();

//        pageIndex++;
//        mEditor.putInt("Index", pageIndex);
//        mEditor.commit();
     LinearLayoutManager layout = new LinearLayoutManager(App.base);

     mView.setLayoutManager(layout);
     //分割线
     mView.addItemDecoration(new DividerItemDecoration(App.base,DividerItemDecoration.VERTICAL));

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
         mList.clear();
            pageIndex = 0;
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
// private void Recycler() {

// }
 private void loadMode() {
  model.detail_PinLun(mShared.getString("PiniId",""),"0", new MyCallBack() {
   @Override
   public void onErro(String strErro) {

   }

   @Override
   public void onSuccess(String strSuccess) {
    XStream xs = new XStream();
    xs.alias("oschina", Move_Detail_PinLunJavaBean.class);
    xs.alias("comment",Move_Detail_PinLunJavaBean.CommentBean.class);
    Move_Detail_PinLunJavaBean homeListBean = (Move_Detail_PinLunJavaBean) xs.fromXML(strSuccess);
//                BlogJavaBean homeListBean = (BlogJavaBean) xs.fromXML(strSuccess);
    mList.addAll(homeListBean.getComments());
    mAdapter = new MyAdapter(App.base,R.layout.fragment_detail_pinlun_item,mList);
    mView.setAdapter(mAdapter);
    Log.i("多少",mAdapter.getItemCount()+"");
    Log.i("KANKAN",homeListBean.getComments().toString());
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
 class MyAdapter extends BaseAdapter<Move_Detail_PinLunJavaBean.CommentBean> {

  public MyAdapter(Context context, int layoutId, List<Move_Detail_PinLunJavaBean.CommentBean> datas) {
   super(context, layoutId, datas);
  }

  @Override
  public void convert(ViewHolder holder, final Move_Detail_PinLunJavaBean.CommentBean javaBean) {

   holder.setText(R.id.item_detail_pinlun_title, javaBean.getAuthor());
   holder.setText(R.id.item_detail_pinlun_body,javaBean.getContent());
   holder.setText(R.id.item_detail_pinlun_time, Dates.getDate(javaBean.getPubDate()));
   final ImageView im = holder.getView(R.id.item_detail_pinlun_img);
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

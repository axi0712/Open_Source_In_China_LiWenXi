package com.example.administrator.open_source_in_china_liwenxi.model.fragment.mine;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Mine_FenSiJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class Activity_GuanZhu extends BaseActivity {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<Mine_FenSiJavaBean.FriendBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private int pageIndex = 1;
    private INewModel model = null;
    private ImageView mCancle;
    @Override
    protected int getLayout() {
        return R.layout.activity_guanzhu;
    }

    @Override
    protected void getID() {
        mCancle = (ImageView) findViewById(R.id.fra_guanzhu_canel);
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mView = (PullToRefreshRecyclerView) findViewById(R.id.guanzhu_liebiao);
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();
        model = new NewsModelImple();
//        pageIndex = mShared.getInt("Index", 1);
        Log.e("knaknGuanZhu",pageIndex+"");
        loadMode();

//        pageIndex++;
//        mEditor.putInt("Index", pageIndex);
//        mEditor.commit();
        LinearLayoutManager layout = new LinearLayoutManager(App.base);

        mView.setLayoutManager(layout);
        //分割线
        mView.addItemDecoration(new DividerItemDecoration(App.base,DividerItemDecoration.VERTICAL));

    }
    private void loadMode() {
        model.mine_guanzhu(mShared.getString("uid",""),"0", new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Mine_FenSiJavaBean.class);
                xs.alias("friend",Mine_FenSiJavaBean.FriendBean.class);
                Mine_FenSiJavaBean homeListBean = (Mine_FenSiJavaBean) xs.fromXML(strSuccess);
//                BlogJavaBean homeListBean = (BlogJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getFriends());
                mAdapter = new MyAdapter(App.base,R.layout.activity_fensi_item,mList);
                mView.setAdapter(mAdapter);
                Log.i("多少",mAdapter.getItemCount()+"");
                Log.i("KANKAN",homeListBean.getFriends().toString());
            }
        });
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
    class MyAdapter extends BaseAdapter<Mine_FenSiJavaBean.FriendBean> {

        public MyAdapter(Context context, int layoutId, List<Mine_FenSiJavaBean.FriendBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final Mine_FenSiJavaBean.FriendBean javaBean) {

            holder.setText(R.id.item_fensi_title, javaBean.getName());
            holder.setText(R.id.item_fensi_address,javaBean.getFrom());
            holder.setText(R.id.item_fensi_geqian, javaBean.getGender());
            holder.setText(R.id.item_fensi_gongzuo,javaBean.getExpertise());
            final ImageView im = holder.getView(R.id.item_fensi_img);
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

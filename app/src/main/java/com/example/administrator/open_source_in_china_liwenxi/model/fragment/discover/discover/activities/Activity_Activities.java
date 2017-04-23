package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Activities_JavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class Activity_Activities extends BaseActivity {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<Activities_JavaBean.EventBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private int pageIndex = 1;
    private INewModel model = null;
    //    private ViewPager mPager;
//    private List<View> list = new ArrayList<>();
    private RollPagerView mRoll;
    @Override
    protected int getLayout() {
        return R.layout.activity_activities;
    }

    @Override
    protected void getID() {
        View v = LayoutInflater.from(this).inflate(R.layout.fragemnt_news_rollviewpager,null);
        mRoll = (RollPagerView) v.findViewById(R.id.fragment_news_viewPager);
        mView = (PullToRefreshRecyclerView) findViewById(R.id.activities_pullto);
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();
        model = new NewsModelImple();
        mView.addHeaderView(v);
//        pageIndex = mShared.getInt("Index", 1);
        Log.e("knakn", pageIndex + "");
        loadMode();

//        pageIndex++;
//        mEditor.putInt("Index", pageIndex);
//        mEditor.commit();
        recyclerView();

        RollViewPager();
//        mPager = (ViewPager) view.findViewById(R.id.fragment_news_viewPager);
//
//
//        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentItem = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        lunbo();

    }
    private void RollViewPager() {
        //设置播放时间间隔
        mRoll.setPlayDelay(1000);
        //设置透明度
        mRoll.setAnimationDurtion(500);
        //设置适配器
        mRoll.setAdapter(new RollAdapter());
        //自定义只是图片
        mRoll.setHintView(new ColorPointHintView(this, Color.YELLOW,Color.WHITE));

    }

    private void recyclerView() {
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        mView.addItemDecoration(new DividerItemDecoration(App.base,DividerItemDecoration.VERTICAL));
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
                        for (int i = 1; i <= pageIndex; i++) {
                            loadMode();
                        }


//                        mEditor.putInt("Index",pageIndex);
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
                        mView.setLoadMoreComplete();

                        loadMode();
                        mEditor.putInt("Index", pageIndex);
                        Log.i("加载", pageIndex + "");
                        mEditor.commit();
                    }
                }, 2000);
            }
        });
    }
    private void loadMode() {
        model.discover_activities("1", new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Activities_JavaBean.class);
                xs.alias("event", Activities_JavaBean.EventBean.class);
                Activities_JavaBean homeListBean = (Activities_JavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getEvents());
                mAdapter = new MyAdapter(App.base,R.layout.activities_item,mList);
//                mAdapter = new MyAdapter(getApplicationContext(), R.layout.fragment_news_opensource_item, mList);
                mView.setAdapter(mAdapter);
                Log.i("多少", mAdapter.getItemCount() + "");
                Log.i("KANKAN", homeListBean.getEvents().toString());
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
    class MyAdapter extends BaseAdapter<Activities_JavaBean.EventBean> {

        public MyAdapter(Context context, int layoutId, List<Activities_JavaBean.EventBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final Activities_JavaBean.EventBean javaBean) {
            final ImageView im = (ImageView) holder.itemView.findViewById(R.id.activities_image);
            holder.setText(R.id.activities_title, javaBean.getTitle() + "");
            Glide.with(App.base).load(javaBean.getCover()).diskCacheStrategy(DiskCacheStrategy.ALL).into(im);
//            String date = Dates.getDate(javaBean.getCreateTime());
//            Log.i("date________", date);
            holder.setText(R.id.activities_time, javaBean.getCreateTime());
//            holder.setOnclickListener(R.id.news_open, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent in = new Intent(App.base, Activity_News_Open_WebView.class);
//                    in.putExtra("details", javaBean.getId() + "");
//                    in.putExtra("commed", javaBean.getCommentCount() + "");
//                      当不是内部类时
//                      String id = newsBean.getId();
//                      holder.getLayoutPosition();
//                      NewsListBean.NewsBean newsBean1 = datas.get(holder.getLayoutPosition());
//                    startActivity(in);
//                }
//            });
        }
    }
    class RollAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.mipmap.purple_sky,
                R.mipmap.blue_windy,
                R.mipmap.sea,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }

    }
}

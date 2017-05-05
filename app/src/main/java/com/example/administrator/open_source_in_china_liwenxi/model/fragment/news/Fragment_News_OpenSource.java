package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.javaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.example.administrator.open_source_in_china_liwenxi.utils.Dates;
import com.jude.rollviewpager.RollPagerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class Fragment_News_OpenSource extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<javaBean.NewsBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private int pageIndex = 1;
    private INewModel model = null;
    private ViewPager mPager;
    private List<View> list = new ArrayList<>();
    private RollPagerView mRoll;
    private final int CODE_START = 0;
    private final int CODE_STOP = 1;
    private int currentItem = 10000000;
    private Handler mHand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_START:
                    mPager.setCurrentItem(currentItem++);
                    mHand.sendEmptyMessageDelayed(CODE_START, 1000);

                    break;
                case CODE_STOP:
                    mHand.removeMessages(CODE_START);
                    break;
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.fragment_news_opensource;
    }

    @Override
    protected void initView(View view) {
        View v = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragement_new_viewpager,null);
        mPager = (ViewPager) v.findViewById(R.id.new_viewpager);
//        mRoll = (RollPagerView) v.findViewById(R.id.fragment_news_viewPager);
        mView = (PullToRefreshRecyclerView) view.findViewById(R.id.news_open_recycle);
        mShared = getActivity().getSharedPreferences("data", MODE_PRIVATE);
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

//        RollViewPager();
//
//
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        lunbo();

    }

//    private void RollViewPager() {
//        //设置播放时间间隔
//        mRoll.setPlayDelay(1000);
//        //设置透明度
//        mRoll.setAnimationDurtion(500);
//        //设置适配器
//        mRoll.setAdapter(new RollAdapter());
//        //自定义只是图片
//        mRoll.setHintView(new ColorPointHintView(getActivity().getApplicationContext(), Color.YELLOW,Color.WHITE));
//
//    }

    private void recyclerView() {
        LinearLayoutManager layout = new LinearLayoutManager(getActivity().getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
//分割线
        mView.addItemDecoration(new DividerItemDecoration(App.base,DividerItemDecoration.VERTICAL));

        mView.setLayoutManager(layout);
        mView.setPullRefreshEnabled(true);//下拉刷新
        mView.setLoadingMoreEnabled(true);//上拉加载
        mView.displayLastRefreshTime(true);//显示上次刷新的时间
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

    private void lunbo() {
        View v = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.news_opensources_item1, null);
        View v1 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.news_opensources_item2, null);
        View v2 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.news_opensources_item3, null);
        list.add(v);
        list.add(v1);
        list.add(v2);

        mPager.setAdapter(new MyPagerAdapter());
        mPager.setCurrentItem(currentItem++);
        mHand.sendEmptyMessageDelayed(CODE_START, 1000);
    }

    private void loadMode() {
        model.newsList("1", new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", javaBean.class);
                xs.alias("news", javaBean.NewsBean.class);
                javaBean homeListBean = (javaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getNewslist());
                mAdapter = new MyAdapter(getActivity().getApplicationContext(), R.layout.fragment_news_opensource_item, mList);
                mView.setAdapter(mAdapter);
                Log.i("多少", mAdapter.getItemCount() + "");
                Log.i("KANKAN", homeListBean.getNewslist().toString());
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
        Log.d("NewsFragment", test);
    }

    class MyAdapter extends BaseAdapter<javaBean.NewsBean> {

        public MyAdapter(Context context, int layoutId, List<javaBean.NewsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, final javaBean.NewsBean javaBean) {
            holder.setText(R.id.news_open_item_title, javaBean.getTitle() + "");
            holder.setText(R.id.news_open_item_body, javaBean.getBody() + "");
            holder.setText(R.id.main_news_author, javaBean.getAuthor() + "");
            holder.setText(R.id.main_news_comment, javaBean.getCommentCount() + "");
            String date = Dates.getDate(javaBean.getPubDate());
            Log.i("date________", date);
            holder.setText(R.id.main_news_time, date);
            holder.setOnclickListener(R.id.news_open, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(App.base, Activity_News_Open_WebView.class);
                    in.putExtra("details", javaBean.getId() + "");
                    in.putExtra("commed", javaBean.getCommentCount() + "");
//                      当不是内部类时
//                      String id = newsBean.getId();
//                      holder.getLayoutPosition();
//                      NewsListBean.NewsBean newsBean1 = datas.get(holder.getLayoutPosition());
                    startActivity(in);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

        class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (container != null) {
                container.removeView(list.get(position % list.size()));
            }
            container.addView(list.get(position % list.size()));
            return list.get(position % list.size());
        }
    }

    @Override
    public void setUserVisibleHint(boolean  isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser) {

                Log.e("显示", "显示");
//

                mHand.sendEmptyMessageDelayed(CODE_START, 1000);

        } else {
            if (boo) {
                boo = false;
            } else {
                mHand.sendEmptyMessage(CODE_STOP);
            }

            Log.e("影藏", "影藏");
        }
    }

    private boolean boo = true;
    private boolean boo1 = true;
//    class RollAdapter extends StaticPagerAdapter{
//     private int[] imgs = {
//             R.mipmap.purple_sky,
//             R.mipmap.blue_windy,
//             R.mipmap.sea,
//     };
//
//    @Override
//    public View getView(ViewGroup container, int position) {
//        ImageView view = new ImageView(container.getContext());
//        view.setImageResource(imgs[position]);
//        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//        return view;
//    }
//
//    @Override
//    public int getCount() {
//        return imgs.length;
//    }
//
//}

}

package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover;

import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.RecommedJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/15 0015.
 */

public class Activity_Git extends BaseActivity {

    private LinearLayout mFraNewsLin;
    private ImageView mFraDisGitCanel;
    private PullToRefreshRecyclerView mFraDisRecycleCanel;
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<RecommedJavaBean.BlogBean> mList = new ArrayList<>();
//    private MyAdapter mAdapter;
    private int pageIndex = 1;
    private INewModel model = null;




    private void assignViews() {
        mFraNewsLin = (LinearLayout) findViewById(R.id.fra_news_lin);
        mFraDisGitCanel = (ImageView) findViewById(R.id.fra_dis_git_canel);
        mFraDisRecycleCanel = (PullToRefreshRecyclerView) findViewById(R.id.fra_dis_recycle_canel);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_git;
    }

    @Override
    protected void getID() {
        assignViews();
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
//        mView = (PullToRefreshRecyclerView) findViewById(R.id.news_recommed_recycle);
//        mShared = getSharedPreferences("data", MODE_PRIVATE);
//        mEditor = mShared.edit();
//        model = new NewsModelImple();
////        pageIndex = mShared.getInt("Index", 1);
//        Log.e("knakn",pageIndex+"");
//        loadMode();
//
////        pageIndex++;
////        mEditor.putInt("Index", pageIndex);
////        mEditor.commit();
//        LinearLayoutManager layout = new LinearLayoutManager(Activity_Git.this);
//        layout.setOrientation(LinearLayoutManager.VERTICAL);
//
//        mView.setLayoutManager(layout);
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
//    }
//    private void loadMode() {
//        model.recommed("1", new MyCallBack() {
//            @Override
//            public void onErro(String strErro) {
//
//            }
//
//            @Override
//            public void onSuccess(String strSuccess) {
//                XStream xs = new XStream();
//                xs.alias("oschina", RecommedJavaBean.class);
//                xs.alias("blog",RecommedJavaBean.BlogBean.class);
//                RecommedJavaBean homeListBean = (RecommedJavaBean) xs.fromXML(strSuccess);
//                mList.addAll(homeListBean.getBlogs());
//                mAdapter =new MyAdapter(Activity_Git.this,R.layout.activity_git_item,mList);
//                mView.setAdapter(mAdapter);
//                Log.i("多少",mAdapter.getItemCount()+"");
//                Log.i("KANKAN",homeListBean.getBlogs().toString());
//            }
//        });
//    }
//    class MyAdapter extends BaseAdapter<RecommedJavaBean.BlogBean> {
//
//        public MyAdapter(Context context, int layoutId, List<RecommedJavaBean.BlogBean> datas) {
//            super(context, layoutId, datas);
//        }
//
//        @Override
//        public void convert(ViewHolder holder, final RecommedJavaBean.BlogBean javaBean) {
//            holder.setText(R.id.news_open_item_title, javaBean.getTitle() + "");
//            holder.setText(R.id.news_open_item_body, javaBean.getBody() + "");
////            holder.setOnclickListener(R.id.news_open, new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent in = new Intent(MainActivity.this,Activity_Details.class);
////                    in.putExtra("details",javaBean.getId()+"");
//
////                      当不是内部类时
////                      String id = newsBean.getId();
////                      holder.getLayoutPosition();
////                      NewsListBean.NewsBean newsBean1 = datas.get(holder.getLayoutPosition());
////                    startActivity(in);
////                }
////            });
//        }
    }
}

package com.example.administrator.open_source_in_china_liwenxi.model.fragment.move;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Move_MainJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.example.administrator.open_source_in_china_liwenxi.utils.Dates;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class Fragment_move_mine extends BaseFragment {
    private PullToRefreshRecyclerView mView;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private ArrayList<Move_MainJavaBean.TweetBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private int pageIndex = 1;
    private INewModel model = null;
    private String isLike;

    @Override
    protected int layoutId() {
        return R.layout.fragment_move_point;
    }

    @Override
    protected void initView(View view) {
        mView = (PullToRefreshRecyclerView) view.findViewById(R.id.move_point_recycle);
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
        model.move_mine("0", new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Move_MainJavaBean.class);
                xs.alias("tweet",Move_MainJavaBean.TweetBean.class);
                Move_MainJavaBean homeListBean = (Move_MainJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getTweets());
                mAdapter = new MyAdapter(getActivity().getApplicationContext(),R.layout.fragment_move_new_item,mList);
                mView.setAdapter(mAdapter);
                Log.i("多少",mAdapter.getItemCount()+"");
                Log.i("KANKANMine",homeListBean.getTweets().toString());
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
        Log.d("BlogFragment", test);
    }
    class MyAdapter extends BaseAdapter<Move_MainJavaBean.TweetBean> {

        public MyAdapter(Context context, int layoutId, List<Move_MainJavaBean.TweetBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(final ViewHolder holder, final Move_MainJavaBean.TweetBean javaBean) {
            final ImageView im = (ImageView) holder.itemView.findViewById(R.id.move_new_item);
//            if(javaBean.getPortrait().isEmpty()){
//                im.setImageResource(R.mipmap.ic_launcher);
//            }else {
                Glide.with(getActivity()).load(javaBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(im) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                        ciDrawable.setCircular(true);
                        im.setImageDrawable(ciDrawable);
                    }
                });
//            }
            holder.setText(R.id.move_new_title, javaBean.getAuthor() + "");
            holder.setText(R.id.move_new_body, javaBean.getBody() + "");
            String date = Dates.getDate(javaBean.getPubDate());
            Log.i("date________", date);
            holder.setText(R.id.item_newsdongtan_author_date, date);
            isLike = javaBean.getIsLike()+"";
            holder.setText(R.id.item_newsdongtan_author_zan, isLike);
            final ImageView ima = (ImageView) holder.itemView.findViewById(R.id.move_new_image);
            Glide.with(App.lastFragment).load(javaBean.getImgSmall()).diskCacheStrategy(DiskCacheStrategy.ALL).into(ima);
            holder.setOnclickListener(R.id.item_newsdongtan_author_zan, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mShared.getString("uid","").isEmpty()){
                        Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                    }else{
                        if("1".equals(isLike)){
                            Toast.makeText(context, "您已经点赞过了", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, mShared.getString("uid",""), Toast.LENGTH_SHORT).show();
                            model.move_zan(javaBean.getId(), mShared.getString("uid", ""), javaBean.getAuthorid(), new MyCallBack() {
                                @Override
                                public void onErro(String strErro) {

                                }

                                @Override
                                public void onSuccess(String strSuccess) {
                                    Log.i("asd_____",strSuccess);
                                    TextView text = holder.getView(R.id.item_newsdongtan_author_zan);
                                    text.setTextColor(Color.parseColor("#FFCC1414"));
                                    int i = Integer.parseInt(javaBean.getLikeCount()) + 1;
                                    isLike = "1";
                                    holder.setText(R.id.item_newsdongtan_author_zan, String.valueOf(i));
                                }
                            });
                        }
                    }
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
//                }
//            });
        }
    }
}

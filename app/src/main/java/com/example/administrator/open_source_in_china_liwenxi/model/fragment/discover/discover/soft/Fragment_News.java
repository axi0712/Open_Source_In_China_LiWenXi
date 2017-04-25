package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.soft;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Discover_NewsJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/16 0016.
 */

public class Fragment_News extends BaseFragment {
    private ArrayList<Discover_NewsJavaBean.SoftwareBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private INewModel model;
    private ListView mView;
    @Override
    protected int layoutId() {
        return R.layout.fragment_news_discover;
    }

    @Override
    protected void initView(View view) {
     mView = (ListView) view.findViewById(R.id.soft_news_listView);
        model = new NewsModelImple();
    }

    @Override
    protected void initData() {
        model.discover_News("1", new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Discover_NewsJavaBean.class);
                xs.alias("software",Discover_NewsJavaBean.SoftwareBean.class);
                Discover_NewsJavaBean homeListBean = (Discover_NewsJavaBean) xs.fromXML(strSuccess);
//                BlogJavaBean homeListBean = (BlogJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getSoftwares());
                mAdapter = new MyAdapter();
                mView.setAdapter(mAdapter);
                Log.i("多少",mAdapter.getItem(0)+"");
                Log.i("KANKAN_________________news",homeListBean.getSoftwares().toString());
            }
        });
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.isEmpty()?0:mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if(convertView == null){
                holder = new Holder();
                convertView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(R.layout.fragment_discover_recommend_item,null);
                holder.mTitle = (TextView) convertView.findViewById(R.id.discover_recommend_text_title);
                holder.mBody = (TextView) convertView.findViewById(R.id.discover_recommend_text_body);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }
            Discover_NewsJavaBean.SoftwareBean dis = mList.get(position);
            holder.mTitle.setText(dis.getName()+"");
            holder.mBody.setText(dis.getDescription()+"");
            return convertView;
        }
        class Holder{
            private TextView mTitle,mBody;
        }
    }
}

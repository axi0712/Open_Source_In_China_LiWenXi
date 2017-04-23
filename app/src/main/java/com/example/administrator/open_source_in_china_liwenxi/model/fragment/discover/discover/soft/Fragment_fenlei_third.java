package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.soft;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Discover_FenLeithirdJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class Fragment_fenlei_third extends BaseFragment {
    private ListView mView;
    private ArrayList<Discover_FenLeithirdJavaBean.SoftwareBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private INewModel model;
    private android.support.v4.app.FragmentManager man;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private String tag;
    @Override
    protected int layoutId() {
        return R.layout.fragment_fenlei_third;
    }

    @Override
    protected void initView(View view) {
        mView = (ListView) view.findViewById(R.id.fenlei_third_listView);
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor = mShared.edit();
        model = new NewsModelImple();
        tag = mShared.getString("tag","");
    }

    @Override
    protected void initData() {
        model.discover_fenlei_third(tag,"1",new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Discover_FenLeithirdJavaBean.class);
                xs.alias("software",Discover_FenLeithirdJavaBean.SoftwareBean.class);
                Discover_FenLeithirdJavaBean homeListBean = (Discover_FenLeithirdJavaBean) xs.fromXML(strSuccess);
//                BlogJavaBean homeListBean = (BlogJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getSoftwares());
                mAdapter = new MyAdapter();
                mView.setAdapter(mAdapter);
                Log.i("KANKANThird+++++",homeListBean.getSoftwares().toString());
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
//                holder.layout = (RelativeLayout) convertView.findViewById(R.id.fragment_fenlei);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }
            Discover_FenLeithirdJavaBean.SoftwareBean dis = mList.get(position);
            holder.mTitle.setText(dis.getName()+"");
            holder.mBody.setText(dis.getDescription()+"");
//            holder.layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mEditor.putString("tag",dis.getTag()+"");
//                    mEditor.commit();
//                    man = getActivity().getSupportFragmentManager();
//                    FragmentTransaction tra = man.beginTransaction();
//                    tra.replace(R.id.fenlei_frame,new Fragment_fenlei_second());
//                    tra.commit();
//                }
//            });
            return convertView;
        }
        class Holder{
            private RelativeLayout layout;
            private TextView mTitle,mBody;
        }
    }
}

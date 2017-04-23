package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.soft;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Discover_fenleiJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Fragment_fenlei_second extends BaseFragment {
    private ListView mView;
    private ArrayList<Discover_fenleiJavaBean.SoftwareTypeBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private INewModel model;
    private String tag;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private FragmentManager man;
    @Override
    protected int layoutId() {
        return R.layout.fragment_fenlei_second;
    }

    @Override
    protected void initView(View view) {
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor = mShared.edit();
        mView = (ListView) view.findViewById(R.id.fenlei_second_listView);
        model = new NewsModelImple();
        tag = mShared.getString("tag","");
        Log.i("tag+++++++++",tag);
    }

    @Override
    protected void initData() {
        model.discover_fenlei_second(tag, new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Discover_fenleiJavaBean.class);
                xs.alias("softwareType",Discover_fenleiJavaBean.SoftwareTypeBean.class);
                Discover_fenleiJavaBean homeListBean = (Discover_fenleiJavaBean) xs.fromXML(strSuccess);
//                BlogJavaBean homeListBean = (BlogJavaBean) xs.fromXML(strSuccess);
                mList.addAll(homeListBean.getSoftwareTypes());

                mView.setAdapter(new MyAdapter());
                Log.i("KANKANSecond____",mList.toString());
            }
        });
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {
//        Toast.makeText(getActivity().getApplication(), tag+"++++++++++++++++++++", Toast.LENGTH_SHORT).show();
//      Log.e("++++++++++++",tag);
    }
    class  MyAdapter extends BaseAdapter {

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
                        .inflate(R.layout.fragment_fenlei_item,null);
                holder.mText = (TextView) convertView.findViewById(R.id.fenlei_item_text);
                holder.layout = (RelativeLayout) convertView.findViewById(R.id.fragment_fenlei);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }
            final Discover_fenleiJavaBean.SoftwareTypeBean dis = mList.get(position);
            holder.mText.setText(dis.getName()+"");
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditor.putString("tag",dis.getTag()+"");
                    mEditor.commit();
                    man = getActivity().getSupportFragmentManager();
                    FragmentTransaction tra = man.beginTransaction();
                    tra.replace(R.id.fenlei_frame,new Fragment_fenlei_third());
                    tra.addToBackStack(null);
                    tra.commit();
                }
            });
            return convertView;
        }
        class Holder{
            private TextView mText;
            private RelativeLayout layout;
        }
    }
}

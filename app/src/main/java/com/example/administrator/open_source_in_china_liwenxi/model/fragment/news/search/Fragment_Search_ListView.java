package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Fragment_Search_ListView extends BaseFragment {
    private ListView mFragmentSearchListViewListView;
    private ArrayList<String> mList = new ArrayList<>();
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private String name;
    private MyManager mDB;
    private MyAdapter mAdapter;
    private TextView mText;
    private android.support.v4.app.FragmentManager man;
    protected int layoutId() {
        return R.layout.fragment_search_listview;
    }

    @Override
    protected void initView(View view) {
        man = getActivity().getSupportFragmentManager();
        mText = (TextView) view.findViewById(R.id.fragment_search_listView_clear);
        mFragmentSearchListViewListView = (ListView) view.findViewById(R.id.fragment_search_listView_ListView);
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor = mShared.edit();
        mDB = new MyManager(getActivity().getApplicationContext());
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mShared.getString("name", "");
                Boolean boo = mDB.deleteRecord();
                if(boo){
                    mList = mDB.getDBAll();
                    mAdapter.setList(mList);
                    mAdapter.notifyDataSetChanged();
                    Log.i("成功删除",mList.toString());
                }else{
                    Log.i("失败",mList.toString());
                }
            }
        });
        mFragmentSearchListViewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = mList.get(position);
                mEditor.putString("name",s);
                mEditor.commit();
                FragmentTransaction tra = man.beginTransaction();
                tra.replace(R.id.fragment_frame, new Fragment_Search_JieGuo());
                tra.commit();
                Toast.makeText(getActivity().getApplicationContext(), "李文茜", Toast.LENGTH_SHORT).show();

            }
        });

        if(mList!=null){
            mText.setVisibility(View.VISIBLE);
            Log.i("隐藏","hidden");
        }else{
            mText.setVisibility(View.GONE);
            Log.i("显示","gone");
        }
    }

    @Override
    protected void initData() {
        mList = mDB.getDBAll();
        mAdapter = new MyAdapter();
        mAdapter.notifyDataSetChanged();
        mFragmentSearchListViewListView.setAdapter(mAdapter);
        Log.i("查询添加-------",mList.toString());
    }

    @Override
    protected void updateTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mList.isEmpty()?0:mList.size();
        }
        public void setList (ArrayList<String> list){
            mList = list;
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
                convertView = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.fragment_search_listview_item,null);
                holder.mText = (TextView) convertView.findViewById(R.id.fragment_search_listview_item);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }
            String str = mList.get(position);
            holder.mText.setText(str.toString()+"");
            return convertView;
        }

    }
    class Holder{
        private TextView mText,mBody;

    }
}

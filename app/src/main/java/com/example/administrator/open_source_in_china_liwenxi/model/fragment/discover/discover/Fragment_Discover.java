package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.MainActivity;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.activities.Activity_Activities;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.soft.Activity_Soft;


/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class Fragment_Discover extends BaseFragment {
    private RelativeLayout mDiscoverGit;
    private ImageView mGit;
    private RelativeLayout mDiscoverSoft;
    private ImageView mSoft;
    private RelativeLayout mDiscoverScan;
    private ImageView mScan;
    private RelativeLayout mDiscoverShake;
    private ImageView mShake;
    private RelativeLayout mDiscoverNearby;
    private ImageView mNearby;
    private RelativeLayout mDiscoverEvent;
    private ImageView mEvent;

    private void assignViews() {

    }



    @Override
    protected int layoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView(View view) {
        mDiscoverGit = (RelativeLayout) view.findViewById(R.id.discover_git);
        mDiscoverGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity().getApplicationContext(),Activity_Git.class);
                startActivity(in);
            }
        });
        mGit = (ImageView) view.findViewById(R.id.Git);
        mDiscoverSoft = (RelativeLayout) view.findViewById(R.id.discover_soft);
        mDiscoverSoft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity().getApplicationContext(),Activity_Soft.class);
                startActivity(in);
            }
        });
        mSoft = (ImageView) view.findViewById(R.id.Soft);
        mDiscoverScan = (RelativeLayout) view.findViewById(R.id.discover_scan);
        mScan = (ImageView) view.findViewById(R.id.scan);
        mDiscoverShake = (RelativeLayout) view.findViewById(R.id.discover_shake);
        mShake = (ImageView) view.findViewById(R.id.shake);
        mDiscoverNearby = (RelativeLayout) view.findViewById(R.id.discover_nearby);
        mNearby = (ImageView) view.findViewById(R.id.nearby);
        mDiscoverEvent = (RelativeLayout) view.findViewById(R.id.discover_event);
        mDiscoverEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in = new Intent(getActivity().getApplicationContext(), Activity_Activities.class);
                startActivity(in);
            }
        });
        mEvent = (ImageView) view.findViewById(R.id.event);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateTitleBar() {
        if(App.base instanceof MainActivity)
            ((MainActivity)App.base).getTitlt().setText("发现");
    }

    @Override
    public void setParams(Bundle bundle) {

    }
}

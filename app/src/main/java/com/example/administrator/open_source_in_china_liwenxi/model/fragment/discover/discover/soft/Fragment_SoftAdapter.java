package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.soft;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16 0016.
 */

public class Fragment_SoftAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFraList ;
    private List<String> mStrList;
    public Fragment_SoftAdapter(FragmentManager fm, List<Fragment> mFraList, List<String> mStrList) {
        super(fm);
        this.mFraList = mFraList;
        this.mStrList = mStrList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFraList.get(position);
    }

    @Override
    public int getCount() {
        return mFraList.isEmpty()?0:mFraList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrList.get(position);
    }
}

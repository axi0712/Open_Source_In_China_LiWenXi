package com.example.administrator.open_source_in_china_liwenxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Process;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseFragment;
import com.example.administrator.open_source_in_china_liwenxi.base.FragmentBuilder;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.Activity_Build;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.Fragment_Discover;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.mine.Activity_Login;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.mine.Fragment_Mine;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.move.Fragment_Move;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.Fragment_News;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search.Activity_Search;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    private RelativeLayout mActivityMain;
    private RelativeLayout mMainRela;
    private TextView mMainTitle;
    private ImageView mMainTitleImage;
    private RadioButton mMainNews;
    private RadioButton mMainMove;
    private RadioButton mMainIamgeBuild;
    private RadioButton mMainDiscover;
    private RadioButton mMainMine;
    private TabLayout mNewsTab;
    private FrameLayout mMainFrame;
    private RadioGroup radioGroup;
    private FragmentManager man;
    private SharedPreferences mShared;
    private SharedPreferences.Editor editor;
    private long mExitTime;
    public RelativeLayout getmMainRela() {
        return mMainRela;
    }

    public void setmMainRela(RelativeLayout mMainRela) {
        this.mMainRela = mMainRela;
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void getID() {
        assignViews();
        man = getSupportFragmentManager();
        FragmentBuilder.getInstance().start(Fragment_News.class).build();
        mShared = getSharedPreferences("data",MODE_PRIVATE);
        editor = mShared.edit();
    }
    public TextView getTitlt() {
        return mMainTitle;
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
    private void assignViews() {
        radioGroup = (RadioGroup) findViewById(R.id.Main_group);
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mNewsTab = (TabLayout) findViewById(R.id.news_tab);
        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);
        mMainRela = (RelativeLayout) findViewById(R.id.main_rela);
        mMainTitle = (TextView) findViewById(R.id.main_title);
        mMainTitleImage = (ImageView) findViewById(R.id.main_title_image);
        mMainNews = (RadioButton) findViewById(R.id.main_news);
        mMainMove = (RadioButton) findViewById(R.id.main_move);
        mMainIamgeBuild = (RadioButton) findViewById(R.id.main_iamge_build);
        mMainDiscover = (RadioButton) findViewById(R.id.main_discover);
        mMainMine = (RadioButton) findViewById(R.id.main_mine);
        mMainTitleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivity.this, Activity_Search.class);
                startActivity(in);
            }
        });

        mMainIamgeBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String str = mShared.getString("uid","");
            Log.i("_________",str);
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            if(str.length()==0) {
                Intent in = new Intent (MainActivity.this, Activity_Login.class);
                startActivity(in);
            }else {
                Intent in = new Intent(MainActivity.this, Activity_Build.class);
                startActivity(in);
            }
            }
        });

        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_news:
                FragmentBuilder.getInstance().start(Fragment_News.class).build();
                break;
            case R.id.main_move:
                FragmentBuilder.getInstance().start(Fragment_Move.class).build();
                break;
            case R.id.main_discover:
                FragmentBuilder.getInstance().start(Fragment_Discover.class).build();
                break;
            case R.id.main_mine:
                FragmentBuilder.getInstance().start(Fragment_Mine.class).build();
                break;
        }
    }

    @Override
    protected void onDestroy() {
//        super.onDestroy();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
        @Override
    public void onBackPressed() {
        String name = man.getBackStackEntryAt(man.getBackStackEntryCount() - 1).getName();
        if (Fragment_News.class.getSimpleName().equals(name)
                || Fragment_Move.class.getSimpleName().equals(name)
                || Fragment_Discover.class.getSimpleName().equals(name)
                || Fragment_Mine.class.getSimpleName().equals(name)) {
            finish();
        } else {
            if (man.getBackStackEntryCount() > 1) {
                //弹栈
                man.popBackStackImmediate();
                String name1 = man.getBackStackEntryAt(man.getBackStackEntryCount() - 1).getName();
                BaseFragment frag = (BaseFragment) man.findFragmentByTag(name1);
                App.lastFragment = frag;
            }
        }

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

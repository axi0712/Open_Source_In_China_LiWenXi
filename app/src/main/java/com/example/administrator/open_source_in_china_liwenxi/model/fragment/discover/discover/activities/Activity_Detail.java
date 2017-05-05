package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover.activities;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.HuoDongDetailBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class Activity_Detail extends BaseActivity {
    private WebView mWeb;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private INewModel model ;
    private ImageView mCancle;
    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void getID() {
        mShared = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mShared.edit();
        model = new NewsModelImple();
        mWeb = (WebView) findViewById(R.id.activity_detail);
        mCancle = (ImageView) findViewById(R.id.activity_detail_canel);
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        String id = mShared.getString("ids","");
        load(id);

        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());

    }

    private void load(String id) {
        model.activities_detail(id, new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs= new XStream();
                xs.alias("oschina", HuoDongDetailBean.class);
                xs.alias("post",HuoDongDetailBean.PostBean.class);
                HuoDongDetailBean huodong= (HuoDongDetailBean) xs.fromXML(strSuccess);
                Log.i("webView",huodong.getPost().toString());
                String url = huodong.getPost().getUrl();
                mWeb.loadUrl(url);
                Log.i("url",url);

            }
        });

    }
}

package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Detail_BlogJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

public class Activity_News_blog_WebView extends BaseActivity {

    private INewModel model;
    private RelativeLayout mActivityNewsOpenWebView;
    private LinearLayout mFraNewsLin;
    private ImageView mFraNewsCanel;
    private ImageView mFraNewsCommed;
    private TextView mFraNewsCommedCount;
    private WebView mWeb;

    private void assignViews() {
        model=new NewsModelImple();
        mActivityNewsOpenWebView = (RelativeLayout) findViewById(R.id.activity__news__blog__web_view);
        mFraNewsLin = (LinearLayout) findViewById(R.id.fra_news_blog_lin);
        mFraNewsCanel = (ImageView) findViewById(R.id.fra_news_blog_canel);
        mFraNewsCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFraNewsCommed = (ImageView) findViewById(R.id.fra_news_blog_commed);
        mFraNewsCommedCount = (TextView) findViewById(R.id.fra_news_blog_commed_count);
        mWeb = (WebView) findViewById(R.id.fra_blog_webView);
    }



    @Override
    protected int getLayout() {
        return R.layout.activity__news__blog__web_view;
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
        Intent in = getIntent();
        mFraNewsCommedCount.setText(in.getStringExtra("commed"));
        String id = in.getStringExtra("details");
        Log.i("id",id);
        load(id);

        mWeb.getSettings().setJavaScriptEnabled(true);
       mWeb.setWebViewClient(new WebViewClient());
    }

    private void load(String id) {
        model.getParsing_Blog(id, new MyCallBack() {
            @Override
            public void onErro(String strErro) {
            }

            /**
             *
             * */
            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Detail_BlogJavaBean.class);
                xs.alias("blog", Detail_BlogJavaBean.BlogBean.class);
                Detail_BlogJavaBean homeListBean = (Detail_BlogJavaBean) xs.fromXML(strSuccess);
                Log.i("Blog_________",homeListBean.getBlog().toString());
                 String url = homeListBean.getBlog().getUrl() + "";
                mWeb.loadUrl(url);
                Log.i("WebView", url + "");

    }
});

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

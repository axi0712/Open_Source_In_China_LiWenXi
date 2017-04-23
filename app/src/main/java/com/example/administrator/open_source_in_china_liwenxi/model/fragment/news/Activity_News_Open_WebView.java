package com.example.administrator.open_source_in_china_liwenxi.model.fragment.news;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.DetailBean;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

public class Activity_News_Open_WebView extends BaseActivity {
    private RelativeLayout mActivityNewsOpenWebView;
    private LinearLayout mFraNewsLin;
    private ImageView mFraNewsCanel;
    private ImageView mFraNewsCommed;
    private TextView mFraNewsCommedCount;
    private WebView mWeb;
    private INewModel model = null;
    private String url;

    private void assignViews() {
        mActivityNewsOpenWebView = (RelativeLayout) findViewById(R.id.activity__news__open__web_view);
        mFraNewsLin = (LinearLayout) findViewById(R.id.fra_news_lin);
        mFraNewsCanel = (ImageView) findViewById(R.id.fra_news_canel);
        mFraNewsCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFraNewsCommed = (ImageView) findViewById(R.id.fra_news_commed);
        mFraNewsCommedCount = (TextView) findViewById(R.id.fra_news_commed_count);
        mWeb = (WebView) findViewById(R.id.fra_news_webView);
        model = new NewsModelImple();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity__news__open__web_view;
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
        load(id);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void load(String id) {
        model.getParsing(id, new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            /**
             *
             * */
            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", DetailBean.class);
                xs.alias("relative", DetailBean.NewsBean.RelativeBean.class);
                DetailBean homeListBean = (DetailBean) xs.fromXML(strSuccess);
                url = homeListBean.getNews().getUrl() + "";
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

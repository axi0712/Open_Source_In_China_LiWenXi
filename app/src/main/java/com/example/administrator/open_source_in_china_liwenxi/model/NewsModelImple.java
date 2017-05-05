package com.example.administrator.open_source_in_china_liwenxi.model;

import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.example.administrator.open_source_in_china_liwenxi.model.http.ParsingFactory;
import com.example.administrator.open_source_in_china_liwenxi.utils.Urls;

import java.util.HashMap;
import java.util.Map;

import static com.example.administrator.open_source_in_china_liwenxi.utils.Urls.type;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class NewsModelImple implements INewModel {
    @Override
    public void newsList(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog", Urls.newsType);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.pageSize);
        ParsingFactory.initParsing().getNewsList( Urls.newsType, pageIndex, Urls.pageSize, callback);

    }

    @Override
    public void blog(String pageIndex, MyCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("type", type);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.pageSize);
        ParsingFactory.initParsing().getBlog( type, pageIndex, Urls.pageSize, callBack);
    }

    @Override
    public void point(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog",Urls.catalog);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.pageSize);
        params.put("show",Urls.show);
        ParsingFactory.initParsing().getPoint( Urls.catalog, pageIndex, Urls.pageSize,Urls.show, callback);
    }

    @Override
    public void answer(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog",Urls.catalog_Answer);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.pageSize);
        ParsingFactory.initParsing().getAnswer( Urls.catalog_Answer, pageIndex, Urls.pageSize, callback);

    }

    @Override
    public void recommed(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("type",Urls.type_Recommed);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.pageSize);
        ParsingFactory.initParsing().getRecommed( Urls.type_Recommed, pageIndex, Urls.pageSize, callback);

    }

    @Override
    public void move_new(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("uid",Urls.uid_move);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.pageSize);
        ParsingFactory.initParsing().getNew_Move( Urls.uid_move, pageIndex, Urls.pageSize, callback);

    }


    @Override
    public void move_mine(String id,String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("uid",id);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.pageSize);
        ParsingFactory.initParsing().getMine_Move( id, pageIndex, Urls.pageSize, callback);
    }

    @Override
    public void getParsing(String id, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("id",id);
        ParsingFactory.initParsing().getParesingDetail(id,callback);
    }

    @Override
    public void getParsing_Blog(String id, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("id",id);
        ParsingFactory.initParsing().getParesingDetail_Blog(id,callback);
    }

    @Override
    public void discover_fenlei(String type, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("type","0");
        ParsingFactory.initParsing().getDiscover_fenlei(type,callback);
    }

    @Override
    public void discover_fenlei_second(String tag, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("tag",tag);
        ParsingFactory.initParsing().getDiscover__fenlei_second(tag,callback);
    }

    @Override
    public void discover_fenlei_third(String searchTag,String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("searchTag",searchTag);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getDiscover__fenlei_third(searchTag,pageIndex,Urls.pageSize,callback);
    }

    @Override
    public void discover_Recommend(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("searchTag",Urls.DISCOVER_RECOMMEND_SEARCHTAG);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getDiscover_Recommend(Urls.DISCOVER_RECOMMEND_SEARCHTAG,pageIndex,Urls.pageSize,callback);
    }

    @Override
    public void discover_News(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("searchTag",Urls.DISCOVER_RECOMMEND_SEARCHTAG_NEWS);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getDiscover_News(Urls.DISCOVER_RECOMMEND_SEARCHTAG,pageIndex,Urls.pageSize,callback);
    }

    @Override
    public void discover_Hot(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("searchTag",Urls.DISCOVER_RECOMMEND_SEARCHTAG_HOT);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getDiscover_Hots(Urls.DISCOVER_RECOMMEND_SEARCHTAG_HOT,pageIndex,Urls.pageSize,callback);
    }

    @Override
    public void discover_China(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("searchTag",Urls.DISCOVER_RECOMMEND_SEARCHTAG_CHINA);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getDiscover_China(Urls.DISCOVER_RECOMMEND_SEARCHTAG_CHINA,pageIndex,Urls.pageSize,callback);
    }

    @Override
    public void move_hot(String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("uid",Urls.uid_move_hot);
        params.put("pageIndex",pageIndex);
        params.put("pageSize", Urls.pageSize);
        ParsingFactory.initParsing().getHot_Move( Urls.uid_move_hot, pageIndex, Urls.pageSize, callback);
    }

    @Override
    public void mine_login(String username, String pwd,String keep_login,MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("pwd",pwd);
        params.put("keep_login",keep_login);
        ParsingFactory.initParsing().getMine_Login(username, pwd, keep_login,callback);
    }

    @Override
    public void login_Info(String uid, MyCallBack callback) {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        ParsingFactory.initParsing().getLogin_Info(uid,callback);
    }

    @Override
    public void search_SoftWare( String content, String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog",Urls.SEARCH_SOFTWARE_CATALOG);
        params.put("content",content);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getSearch_SoftWare(Urls.SEARCH_SOFTWARE_CATALOG,content,pageIndex,Urls.pageSize,callback);
    }

    @Override
    public void search_Blog(String content, String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog",Urls.SEARCH_BLOG_CATALOG);
        params.put("content",content);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getSearch_Blog(Urls.SEARCH_BLOG_CATALOG,content,pageIndex,Urls.pageSize,callback);

    }

    @Override
    public void search_Post(String content, String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog",Urls.SEARCH_POST_CATALOG);
        params.put("content",content);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getSearch_Post(Urls.SEARCH_POST_CATALOG,content,pageIndex,Urls.pageSize,callback);

    }

    @Override
    public void search_News(String content, String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog",Urls.SEARCH_NEWS_CATALOG);
        params.put("content",content);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getSearch_News(Urls.SEARCH_NEWS_CATALOG,content,pageIndex,Urls.pageSize,callback);


    }

    @Override
    public void search_person(String name, MyCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("name",name);

        ParsingFactory.initParsing().getSearch_Person(name,callBack);

    }

    @Override
    public void send_move(String uid, String msg, String img, String amr, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("msg",msg);
        params.put("img",img);
        params.put("amr",amr);
        ParsingFactory.initParsing().getSend_Move(uid, msg, img, amr, callback);
    }

    @Override
    public void discover_activities( String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("uid", Urls.DISCOVER_UID);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().getActivities(Urls.DISCOVER_UID, pageIndex, Urls.pageSize, callback);
    }

    @Override
    public void move_zan(String tweetid, String uid, String owenrOfTweet, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("tweetid",tweetid);
        params.put("uid",uid);
        params.put("ownerOfTweet",owenrOfTweet);
        ParsingFactory.initParsing().getMove_Zan(tweetid,uid,owenrOfTweet, callback);

    }

    @Override
    public void move_UnZan(String tweetid, String uid, String owenrOfTweet, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("tweetid",tweetid);
        params.put("uid",uid);
        params.put("ownerOfTweet",owenrOfTweet);
        ParsingFactory.initParsing().getMove_UnZan(tweetid,uid,owenrOfTweet, callback);
    }

    @Override
    public void move_PinLun(String id, String uid, String content, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog",Urls.CATALOG_MOVE);
        params.put("id",id);
        params.put("uid",uid);
        params.put("content",content);
        params.put("isPostToMyZone",Urls.MOVE_ZHUANFA);
        ParsingFactory.initParsing().getMove_PINlUN(Urls.CATALOG_MOVE,id,uid,content,Urls.MOVE_ZHUANFA, callback);
    }

    @Override
    public void activities_detail(String id, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("id",id);
        ParsingFactory.initParsing().getActivities_detail(id,callback);

    }

    @Override
    public void detail_PinLun(String id, String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("catalog",Urls.DETAIL_PINLUN);
        params.put("id",id);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().detail_pinlun(Urls.DETAIL_PINLUN,id,pageIndex,Urls.pageSize,callback);
    }

    @Override
    public void mine_fensi(String uid, String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("id",uid);
        params.put("relation",Urls.MINE_FENSI_RELATION);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().mine_fensi(uid,Urls.MINE_FENSI_RELATION,pageIndex,Urls.pageSize,callback);

    }

    @Override
    public void mine_guanzhu(String uid, String pageIndex, MyCallBack callback) {
        Map<String,String> params = new HashMap<>();
        params.put("id",uid);
        params.put("relation","1");
        params.put("pageIndex",pageIndex);
        params.put("pageSize",Urls.pageSize);
        ParsingFactory.initParsing().mine_guanzhu(uid,"1",pageIndex,Urls.pageSize,callback);

    }

    @Override
    public void yaoyiyao(MyCallBack callback) {
        ParsingFactory.initParsing().yaoyiyao(callback);
    }
}

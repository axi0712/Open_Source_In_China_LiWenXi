package com.example.administrator.open_source_in_china_liwenxi.model;

import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public interface INewModel {
    void newsList(String pageIndex, MyCallBack callback);
    void blog(String pageIndex,MyCallBack callBack);
    void point(String pageIndex,MyCallBack callback);
    void answer(String pageIndex,MyCallBack callback);
    void recommed(String pageIndex,MyCallBack callback);
    void move_new(String pageIndex,MyCallBack callback);
    void move_mine(String pageIndex,MyCallBack callback);
    void move_hot(String pageIndex,MyCallBack callback);
    void getParsing(String id,MyCallBack callback);
    void getParsing_Blog(String id,MyCallBack callback);
    void discover_fenlei(String type,MyCallBack callback);
    void discover_fenlei_second(String tag,MyCallBack callback);
    void discover_fenlei_third(String searchTag,String pageIndex,MyCallBack callback);
    void discover_Recommend(String pageIndex,MyCallBack callback);
    void discover_News(String pageIndex,MyCallBack callback);
    void discover_Hot(String pageIndex,MyCallBack callback);
    void discover_China(String pageIndex,MyCallBack callback);
    void mine_login(String username,String pwd,String keep_login,MyCallBack callback);
    void login_Info(String uid,MyCallBack callback);
    void search_SoftWare(String content,String pageIndex,MyCallBack callback);
    void search_Blog(String content,String pageIndex,MyCallBack callback);
    void search_Post(String content,String pageIndex,MyCallBack callback);
    void search_News(String content,String pageIndex,MyCallBack callback);
    void search_person(String name,MyCallBack callBack);
    void send_move(String uid,String msg,String img,String amr,MyCallBack callback);
    void discover_activities(String pageIndex,MyCallBack callback);
    void move_zan(String tweetid,String uid,String owenrOfTweet,MyCallBack callback);
}

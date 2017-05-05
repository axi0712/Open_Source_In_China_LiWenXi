package com.example.administrator.open_source_in_china_liwenxi.model.http;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public interface IHTPP {
    void getNewsList(String catalog,String pageIndex,String pageSize,MyCallBack callback);
    void getBlog(String type,String pageIndex,String pageSize,MyCallBack callBack);
    void getPoint(String catalog,String pageIndex,String pageSize,String show,MyCallBack callback);
    void getAnswer(String catalog,String pageIndex,String pageSize,MyCallBack callback);
    void getRecommed(String type,String pageIndex,String pageSize,MyCallBack callback);
    void getNew_Move(String uid,String pageIndex,String pageSize,MyCallBack callback);
    void getMine_Move(String uid,String pageIndex,String pageSize,MyCallBack callback);
    void getHot_Move(String uid,String pageIndex,String pageSize,MyCallBack callback);
    void getParesingDetail(String id, MyCallBack callback);
    void getParesingDetail_Blog(String id, MyCallBack callback);
    void getDiscover_fenlei(String type, MyCallBack callback);
    void getDiscover__fenlei_second(String tag,MyCallBack callback);
    void getDiscover__fenlei_third(String searchtag,String pageIndex,String pageSize,MyCallBack callback);
    void getDiscover_Recommend(String searchTag,String pageIndex,String pageSize,MyCallBack callback);
    void getDiscover_News(String searchTag,String pageIndex,String pageSize,MyCallBack callback);
    void getDiscover_Hots(String searchTag,String pageIndex,String pageSize,MyCallBack callback);
    void getDiscover_China(String searchTag,String pageIndex,String pageSize,MyCallBack callback);
    void getMine_Login(String username,String pwd,String keep_login,MyCallBack callback);
    void getLogin_Info(String uid, MyCallBack callback);
    void getSearch_SoftWare(String catalotg,String content,String pageIndex,String pageSize,MyCallBack callback);
    void getSearch_Blog(String catalotg,String content,String pageIndex,String pageSize,MyCallBack callback);
    void getSearch_Post(String catalotg,String content,String pageIndex,String pageSize,MyCallBack callback);
    void getSearch_News(String catalotg,String content,String pageIndex,String pageSize,MyCallBack callback);
    void getSearch_Person(String name,MyCallBack callback);
    void getSend_Move(String uid,String msg,String img,String amr,MyCallBack callback);
    void getActivities(String uid,String pageIndex,String pageSize,MyCallBack callback);
    void getMove_Zan(String tweetid,String uid,String ownerOfTweet ,MyCallBack callback);
    void getMove_UnZan(String tweetid,String uid,String ownerOfTweet ,MyCallBack callback);
    void getMove_PINlUN(String catalog,String id,String uid ,String content,String isPostToMyZone, MyCallBack callback);
    void getActivities_detail(String id, MyCallBack callback);
    void detail_pinlun(String catalog,String id,String pageIndex,String pageSize,MyCallBack callback);
    void mine_fensi(String uid,String relation,String pageIndex,String pageSize,MyCallBack callback);
    void mine_guanzhu(String uid,String relation,String pageIndex,String pageSize,MyCallBack callback);
    void yaoyiyao(MyCallBack callback);
    void post();
    void load();
    void Filed(Map<String,String> map,File file, String filekey,MyCallBack callBack);

}

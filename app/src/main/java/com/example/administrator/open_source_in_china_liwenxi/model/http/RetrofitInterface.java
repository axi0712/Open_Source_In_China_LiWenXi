package com.example.administrator.open_source_in_china_liwenxi.model.http;

import com.example.administrator.open_source_in_china_liwenxi.utils.Urls;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public interface RetrofitInterface {
    @GET(Urls.NEWSLIST)
    Call<ResponseBody> getParsingNewsList(@Query("catalog")String catalog, @Query("pageIndex")String pageIndex, @Query("pageSize")String pageSize);
    @GET(Urls.BLOG)
    Call<ResponseBody> getParsingBlog(@Query("type")String type, @Query("pageIndex")String pageIndex, @Query("pageSize")String pageSize);
    @GET(Urls.POINT)
    Call<ResponseBody> getParsingPoint(@Query("catalog")String catalog, @Query("pageIndex")String pageIndex, @Query("pageSize")String pageSize,@Query("show")String show);
    @GET(Urls.ANSWER)
    Call<ResponseBody> getParsingAnswer(@Query("catalog")String catalog, @Query("pageIndex")String pageIndex, @Query("pageSize")String pageSize);

    @GET(Urls.RECOMMED)
    Call<ResponseBody> getParsingRecommed(@Query("type")String type, @Query("pageIndex")String pageIndex, @Query("pageSize")String pageSize);
    @GET(Urls.NEW_MOVE)
    Call<ResponseBody> getParsingNew_Move(@Query("uid")String uid, @Query("pageIndex")String pageIndex, @Query("pageSize")String pageSize);
    @GET(Urls.NEW_MOVE)
    Call<ResponseBody> getParsingMine_Move(@Query("uid")String uid, @Query("pageIndex")String pageIndex, @Query("pageSize")String pageSize);
    @GET(Urls.NEW_MOVE)
    Call<ResponseBody> getParsingHot_Move(@Query("uid")String uid, @Query("pageIndex")String pageIndex, @Query("pageSize")String pageSize);
    @GET(Urls.DETAIL)
    Call<ResponseBody> getParsingDetail(@Query("id") String id);
    @GET(Urls.DETAIL_BLOG)
    Call<ResponseBody> getParsingDetail_Blog(@Query("id") String id);
    @GET(Urls.DISCOVER_FENLEI)
    Call<ResponseBody> getDiscover_FenLei(@Query("type") String type);
    @GET(Urls.DISCOVER_FENLEI)
    Call<ResponseBody> getDiscover_FenLei_Second(@Query("tag") String tag);
    @GET(Urls.DISCOVER_FENLEI_THIRD)
    Call<ResponseBody> getDiscover_FenLei_Third(@Query("searchTag") String searchTag,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.DISCOVER_RECOMMEND_SEARCHTAG_ADDRESS)
    Call<ResponseBody> getDiscover_Recommend(@Query("searchTag") String searchTag,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.DISCOVER_RECOMMEND_SEARCHTAG_ADDRESS)
    Call<ResponseBody> getDiscover_News(@Query("searchTag") String searchTag,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.DISCOVER_RECOMMEND_SEARCHTAG_ADDRESS)
    Call<ResponseBody> getDiscover_Hot(@Query("searchTag") String searchTag,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.DISCOVER_RECOMMEND_SEARCHTAG_ADDRESS)
    Call<ResponseBody> getDiscover_China(@Query("searchTag") String searchTag,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.LOGIN)
    Call<ResponseBody> getLogin(@Query("username")String username, @Query("pwd")String pwd, @Query("keep_login")String keep_login);
    @GET(Urls.LOGIN_INFO)
    Call<ResponseBody> getLogin_Info(@Header("cookie")String cookie,@Query("uid")String uid);

    @GET(Urls.SEARCH)
    Call<ResponseBody> getSearch_SoftWare(@Query("catalog")String catalog,@Query("content") String content,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.SEARCH)
    Call<ResponseBody> getSearch_Blog(@Query("catalog")String catalog,@Query("content") String content,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.SEARCH)
    Call<ResponseBody> getSearch_BPost(@Query("catalog")String catalog,@Query("content") String content,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);

    @GET(Urls.SEARCH)
    Call<ResponseBody> getSearch_News(@Query("catalog")String catalog,@Query("content") String content,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.SEARCH_PERSON)
    Call<ResponseBody> getSearch_Person(@Query("name")String name);
    @GET(Urls.SEND_MOVE)
    Call<ResponseBody> getSend_Move(@Header("cookie")String cookie,@Query("uid")String uid,@Query("msg")String msg,@Query("img")String img,@Query("amr")String amr);
    @GET(Urls.DISCOVER_ACTIVITIES)
    Call<ResponseBody> getActivities(@Query("uid")String uid,@Query("pageIndex")String pageIndex,@Query("pageSize")String pageSize);
    @GET(Urls.MOVE_ZAN)
    Call<ResponseBody> getMove_Zan(@Header("cookie")String cookie,@Query("tweetid")String tweetid,@Query("uid")String uid,@Query("ownerOfTweet")String ownerOfTweet);


}

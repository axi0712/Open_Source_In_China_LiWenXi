package com.example.administrator.open_source_in_china_liwenxi.model.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.open_source_in_china_liwenxi.App;
import com.example.administrator.open_source_in_china_liwenxi.utils.Urls;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class Activity_Retrofit implements IHTPP {

    private Retrofit re;
    private static Activity_Retrofit activityRetrofit = null;
    private static SharedPreferences share;
    private static SharedPreferences.Editor editor;

    public Activity_Retrofit() {
        re = new Retrofit.Builder().baseUrl(Urls.BASE_URL).build();

    }

    public synchronized static Activity_Retrofit getInstance() {
        if (activityRetrofit == null)
            activityRetrofit = new Activity_Retrofit();
        return activityRetrofit;
    }


    @Override
    public void getNewsList(String catalog, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getParsingNewsList(catalog, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getBlog(String type, String pageIndex, String pageSize, final MyCallBack callBack) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getParsingBlog(type, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callBack.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getPoint(String catalog, String pageIndex, String pageSize, String show, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getParsingPoint(catalog, pageIndex, pageSize, show);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });

    }

    @Override
    public void getAnswer(String catalog, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getParsingAnswer(catalog, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getRecommed(String type, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getParsingRecommed(type, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getNew_Move(String uid, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);

        Call<ResponseBody> call = inter.getParsingNew_Move(sharedPreferences.getString("cookie",""),uid, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getMine_Move(String uid, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        final SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);
        Call<ResponseBody> call = inter.getParsingMine_Move(sharedPreferences.getString("cookie",""),uid, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getHot_Move(String uid, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);
        Call<ResponseBody> call = inter.getParsingHot_Move(sharedPreferences.getString("cookie",""),uid, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getParesingDetail(String id, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getParsingDetail(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getParesingDetail_Blog(String id, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getParsingDetail_Blog(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getDiscover_fenlei(String type, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getDiscover_FenLei(type);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getDiscover__fenlei_second(String tag, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getDiscover_FenLei_Second(tag);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getDiscover__fenlei_third(String searchtag, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getDiscover_FenLei_Third(searchtag, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getDiscover_Recommend(String searchTag, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getDiscover_Recommend(searchTag, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getDiscover_News(String searchTag, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getDiscover_News(searchTag, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getDiscover_Hots(String searchTag, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getDiscover_Hot(searchTag, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getDiscover_China(String searchTag, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getDiscover_China(searchTag, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getMine_Login(String username, String pwd, String keep_login, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getLogin(username, pwd, keep_login);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getLogin_Info(String uid, final MyCallBack callback) {

        RetrofitInterface inter = re.create(RetrofitInterface.class);
        final SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);
        Call<ResponseBody> call = inter.getLogin_Info(sharedPreferences.getString("cookie",""), uid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);
                            Toast.makeText(App.base, sharedPreferences.getString("cookie",""), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getSearch_SoftWare(String catalotg, String content, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getSearch_SoftWare(catalotg, content, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getSearch_Blog(String catalotg, String content, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getSearch_Blog(catalotg, content, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getSearch_Post(String catalotg, String content, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getSearch_BPost(catalotg, content, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getSearch_News(String catalotg, String content, String pageIndex, String pageSize, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getSearch_News(catalotg, content, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getSearch_Person(String name, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getSearch_Person(name);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getSend_Move(String uid, String msg, String img, String amr,final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);
        Call<ResponseBody> call = inter.getSend_Move(sharedPreferences.getString("cookie",""),uid,msg,img,amr);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getActivities(String uid, String pageIndex, String pageSize,final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getActivities(uid,pageIndex,pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getMove_Zan(String tweetid, String uid, String ownerOfTweet, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);
        Call<ResponseBody> call = inter.getMove_Zan(sharedPreferences.getString("cookie",""),tweetid,uid,ownerOfTweet);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                  saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getMove_UnZan(String tweetid, String uid, String ownerOfTweet,final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);
        Call<ResponseBody> call = inter.getMove_UnZan(sharedPreferences.getString("cookie",""),tweetid,uid,ownerOfTweet);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                  saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getMove_PINlUN(String catalog, String id, String uid, String content, String isPostToMyZone, final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);
        Call<ResponseBody> call = inter.getMove_PINGLUN(sharedPreferences.getString("cookie",""),catalog,id,uid,content,isPostToMyZone);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                  saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void getActivities_detail(String id,final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getActivities_Detail(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                  saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void detail_pinlun(String catalog,String id, String pageIndex, String pageSize,final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getDetail_PinLun(catalog,id,pageIndex,pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                  saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void mine_fensi(String uid, String relation, String pageIndex, String pageSize,final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getMine_FenSi(uid,relation,pageIndex,pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                  saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void mine_guanzhu(String uid, String relation, String pageIndex, String pageSize,final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getMine_GuanZhu(uid,relation,pageIndex,pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                  saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }

    @Override
    public void yaoyiyao(final MyCallBack callback) {
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        Call<ResponseBody> call = inter.getYaoYiYao();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                  saveCookie(response);
                final String result;
                try {
                    result = response.body().string();
                    App.base.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //触发请求成功的回调
                            callback.onSuccess(result);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onErro(t.getMessage());
            }
        });
    }


    @Override
    public void post() {

    }

    @Override
    public void load() {

    }

    @Override
    public void Filed(Map<String, String> map, File file, String filekey, final MyCallBack callBack) {
        RequestBody fileRequest = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(filekey,file.getName(),fileRequest);
        RetrofitInterface inter = re.create(RetrofitInterface.class);
        final SharedPreferences sharedPreferences = App.base.getSharedPreferences("data",Context.MODE_PRIVATE);
        Call<ResponseBody> call = inter.Filed(sharedPreferences.getString("cookie", ""), map, part);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        callBack.onSuccess(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            callBack.onErro(response.errorBody().string());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
callBack.onErro(t.getMessage());
            }
        });
    }

    private static void saveCookie(Response<ResponseBody> response) {
        String cookie = "";
        Headers head = response.headers();
        Set<String> names = head.names();
        for (String key : names) {
            String value = head.get(key);
            if (key.contains("Set-Cookie")) {
                cookie += value + ";";
                 share = App.base.getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= share.edit();
                editor.putString("cookie", cookie);
                Log.i("cookie________", cookie);
                editor.commit();
            }

        }
    }
}

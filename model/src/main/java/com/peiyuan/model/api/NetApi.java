package com.peiyuan.model.api;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class NetApi {

    private static NetApiService netApiService = null;

    private static NetApi instance = null;

    private NetApi() {

        //配置Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetApiService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(NetConfig.prepareClient())
                .build();

        //创建代理的目标
        netApiService = retrofit.create(NetApiService.class);
    }

    public static NetApi getInstance() {
        if (instance == null) {
            synchronized (NetApi.class) {
                if (instance == null) {
                    instance = new NetApi();
                }
            }
        }
        return instance;
    }

    public Observable<JsonObject> getHomeArticle(@Path("maxID") long maxID, @Path("minID") long minID, @Path("limit") int limit, @Query("ishome") int ishome) {
        // TODO: 2016/1/17 0017 请求前的逻辑处理
        return netApiService.getHomeArticle(maxID, minID, limit, ishome).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Call<ResponseBody> getArticleDetail(@Path("id") long id) {
        return netApiService.getArticleDetail(id);//.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

}

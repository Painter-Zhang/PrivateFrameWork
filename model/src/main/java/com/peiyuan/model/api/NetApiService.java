package com.peiyuan.model.api;


import com.google.gson.JsonObject;
import com.peiyuan.model.entity.ArticleListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public interface NetApiService {

    String HOST = "";    //HOST

    @GET("/cq/financearticle/home/{maxID}/{minID}/{limit}")
    Observable<JsonObject> getHomeArticle(@Path("maxID") long maxID, @Path("minID") long minID, @Path("limit") int limit, @Query("ishome") int ishome);


    @GET("/cq/financearticle/{id}")
    Call<ArticleListEntity> getArticleDetail(@Path("id") long id);
}

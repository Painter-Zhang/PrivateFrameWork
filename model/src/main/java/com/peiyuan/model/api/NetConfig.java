package com.peiyuan.model.api;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class NetConfig {

    private static NetConfig instance = null;
    private OkHttpClient client;
    private List<Cookie> savedCookies = new ArrayList<>();

    private NetConfig() {

        //请求拦截器
        Interceptor interceptor = getInterceptor();

        //Cookie
        CookieJar cookieJar = getCookieJar();

        //创建请求client
        client = new OkHttpClient.Builder().addInterceptor(interceptor).cookieJar(cookieJar).build();

    }

    /**
     * 获取cookie管理
     * @return
     */
    @NonNull
    private CookieJar getCookieJar() {
        return new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    savedCookies = cookies;
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    return savedCookies;
                }
            };
    }

    /**
     * 获取请求拦截器
     * @return
     */
    @NonNull
    private Interceptor getInterceptor() {
        return new NetInterceptor();
    }

    public static OkHttpClient prepareClient() {
        if (instance == null) {
            synchronized (NetConfig.class) {
                if (instance == null) {
                    instance = new NetConfig();
                }
            }
        }
        return instance.getClient();
    }

    private OkHttpClient getClient(){
        return client;
    }
}

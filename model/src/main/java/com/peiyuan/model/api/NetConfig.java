package com.peiyuan.model.api;

import android.util.Log;

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

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class NetConfig {

    private static NetConfig instance = null;
    private OkHttpClient client;
    private List<Cookie> savedCookies = new ArrayList<>();

    private NetConfig() {

        //请求拦截器
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").addHeader("deviceId", "1091").build();
                long startTime = System.currentTimeMillis();
                Log.d("start_request", newRequest.url().toString());
                Response response = chain.proceed(newRequest);
                if (response!=null && response.code()==200){
                    Log.d("response",response.code()+""+"耗时:" + (System.currentTimeMillis()-startTime)/1000.0 + "秒");
                }
                return response;
            }
        };

        //Cookie
        CookieJar cookieJar = new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                savedCookies = cookies;
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return savedCookies;
            }
        };

        //创建请求client
        client = new OkHttpClient.Builder().addInterceptor(interceptor).cookieJar(cookieJar).build();

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

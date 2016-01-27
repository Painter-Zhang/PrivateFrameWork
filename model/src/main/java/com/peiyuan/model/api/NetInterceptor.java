package com.peiyuan.model.api;


import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by hexun on 2016/1/27.
 */
public class NetInterceptor implements Interceptor {

    private static HashMap<Integer, String> codeMapping;

    static {
        codeMapping = new HashMap<Integer, String>();
        // 1K网络错误 2K业务错误 3K其他错误 4K服务返回错误
        codeMapping.put(-1, "网络不可用, 请检查您的网络");
        codeMapping.put(400, "请求无效");
        codeMapping.put(401, "服务器异常，请注销帐号后重新登录");
        codeMapping.put(403, "服务器异常，请注销帐号后重新登录");
        codeMapping.put(404, "请求失败");
        codeMapping.put(500, "服务器异常，请稍后再试");
        codeMapping.put(501, "服务器异常，请稍后再试");
        codeMapping.put(502, "服务器异常，请稍后再试");
        codeMapping.put(503, "服务器正忙，请稍后再试");
        codeMapping.put(504, "服务器请求超时，请稍后再试");

        codeMapping.put(4001, "请求的参数错误");
        codeMapping.put(4002, "发布//更新内容错误");
        codeMapping.put(4003, "XML解析错误");
        codeMapping.put(4004, "传图片错误");
        codeMapping.put(4005, "上传图片大小错误");
        codeMapping.put(4007, "图片压缩错误");
        codeMapping.put(4008, "用户不存在");
        codeMapping.put(4010, "请求的数据不存在");
        codeMapping.put(4011, "URL错误");
        codeMapping.put(4012, "内容包含非法词");
        codeMapping.put(4013, "不能重复绑定服务");

        codeMapping.put(40002, "请求无效");
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        beforeRequestCheck();
        Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").addHeader("deviceId", "1091").build();
        long startTime = System.currentTimeMillis();
        Timber.i("start_request url:%s", newRequest.url().toString());

        Response response = chain.proceed(newRequest);
        if (response!=null && response.code()==200){
            Timber.i("response code: %d 耗时: %d毫秒",response.code(),System.currentTimeMillis()-startTime);
        }else {
            handleHttpError(response.code());
        }
        return response;
    }

    /**
     * 请求之前的检查
     */
    private void beforeRequestCheck() {

    }

    /**
     * 请求之后的错误处理
     * @param code
     */
    private void handleHttpError(int code) {

    }
}

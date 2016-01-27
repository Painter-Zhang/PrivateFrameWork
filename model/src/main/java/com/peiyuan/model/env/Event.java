package com.peiyuan.model.env;

/**
 * Created by hexun on 2016/1/27.
 */
public class Event {

    /**
     * 请求之前的检查,异常事件
     */
    public static class BeforeRequestErrorEvent{

    }

    /**
     * 请求之后的检查,服务器返回错误处理
     */
    public static class HandleHttpErrorEvent{

    }


}

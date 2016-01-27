package com.peiyuan.model.env;

/**
 * Created by hexun on 2016/1/27.
 */
public class Event {

    /**
     * 请求前异常事件
     */
    public static class BeforeRequestErrorEvent {

    }


    /**
     * 请求后Http异常事件
     */
    public static class HandleHttpErrorEvent {

        private int code;

        public HandleHttpErrorEvent(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

    }


}

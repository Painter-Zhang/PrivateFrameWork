package com.peiyuan.model.env;

import android.content.Context;

import com.peiyuan.model.api.NetInterceptor;

import de.greenrobot.event.EventBus;
import timber.log.Timber;

/**
 * Created by Painter-Zhang on 2016/1/27.
 * 事件处理或发送中心. 需要UI层作反应的会发给UI层处理,UI层也会有事件接收处理中心
 */
public class EventProcessCenter {

    private static EventProcessCenter center;
    private Context context;

    private EventProcessCenter(Context context){
        EventBus.getDefault().register(this);
        this.context = context;
    }

    public static EventProcessCenter init(Context context){
        if (center==null){
            synchronized (EventProcessCenter.class){
                if (center==null){
                    center = new EventProcessCenter(context);
                }
            }
        }
        return center;
    }

    /**
     * 处理请求之前的事件
     * @param event
     */
    public void onEventMainThread(Event.BeforeRequestErrorEvent event){

    }

    /**
     * 处理Http异常的事件
     * @param event
     */
    public void onEventMainThread(Event.HandleHttpErrorEvent event){
        int code = event.getCode();
        String errorInfo = NetInterceptor.codeMapping.get(code);
        Timber.e(errorInfo);
    }

}

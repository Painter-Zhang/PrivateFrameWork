package com.peiyuan.model.env;

import android.content.Context;

import de.greenrobot.event.EventBus;

/**
 * Created by hexun on 2016/1/27.
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

    public void onEventMainThread(){

    }

}

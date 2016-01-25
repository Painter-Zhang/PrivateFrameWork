package com.peiyuan.model.db;

import com.orhanobut.logger.Logger;

import io.realm.RealmChangeListener;

/**
 * Created by hexun on 2016/1/25.
 */
public class RmChangeListener implements RealmChangeListener {

    private static RmChangeListener listener;

    private RmChangeListener(){
        Logger.d("RealM 改变了");
    }

    @Override
    public void onChange() {

    }

    public static RmChangeListener getListener(){
        if (listener==null){
            synchronized (RmChangeListener.class){
                if (listener==null){
                    listener = new RmChangeListener();
                }
            }
        }
        return listener;
    }
}

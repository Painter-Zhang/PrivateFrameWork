package com.peiyuan.rxandretro.ui.base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.peiyuan.rxandretro.component.ApplicationComponent;
import com.peiyuan.rxandretro.component.DaggerApplicationComponent;
import com.peiyuan.rxandretro.module.ApplicationModule;

/**
 * Created by Administrator on 2016/1/23 0023.
 */
public class RxApplication extends Application{

    private ApplicationComponent applicationComponent;

    public static RxApplication get(Context context){
        return (RxApplication)context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        Logger.init("RxAndRetro")                 // default PRETTYLOGGER or use just init()
                .methodCount(2)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(0)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}

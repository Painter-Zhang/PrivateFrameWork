package com.peiyuan.rxandretro.ui.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.peiyuan.model.env.ModelContext;
import com.peiyuan.rxandretro.component.ApplicationComponent;
import com.peiyuan.rxandretro.component.DaggerApplicationComponent;
import com.peiyuan.rxandretro.module.ApplicationModule;

/**
 * Created by Administrator on 2016/1/23 0023.
 */
public class RxApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static RxApplication get(Context context) {
        return (RxApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        //防止三方库另起进程导致代码执行多次.
        if (getPackageName().equals(getCurProcessName(this))) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
            initProjectLibrary(getApplicationContext());
            Stetho.initializeWithDefaults(this);
        }
    }

    /**
     * 初始化依赖项目
     *
     * @param applicationContext
     */
    private void initProjectLibrary(Context applicationContext) {
        ModelContext.init(applicationContext);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    /**
     * 判断是否是APP主进程
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }

        return null;
    }
}

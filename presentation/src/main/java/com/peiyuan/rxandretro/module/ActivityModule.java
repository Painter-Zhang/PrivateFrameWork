package com.peiyuan.rxandretro.module;

import android.app.Activity;

import com.peiyuan.rxandretro.component.PerActivity;
import com.peiyuan.model.api.NetApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/1/23 0023.
 */
@Module
public class ActivityModule{

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return this.activity;
    }

    @Provides
    @PerActivity
    NetApi provideNetApi(){
        return NetApi.getInstance();
    }

}

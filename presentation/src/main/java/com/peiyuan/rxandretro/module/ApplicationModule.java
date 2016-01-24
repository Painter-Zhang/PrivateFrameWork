package com.peiyuan.rxandretro.module;

import android.app.Application;
import android.content.Context;

import com.peiyuan.model.api.NetApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/1/23 0023.
 */
@Singleton
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(){

    }

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideApplicationContext(){
        return this.application;
    }


    @Provides
    @Singleton
    NetApi provideNetApi(){
        return NetApi.getInstance();
    }


}

package com.peiyuan.rxandretro.component;

import android.app.Application;
import android.content.Context;

import com.peiyuan.rxandretro.module.ApplicationModule;
import com.peiyuan.rxandretro.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/1/23 0023.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context context();

}

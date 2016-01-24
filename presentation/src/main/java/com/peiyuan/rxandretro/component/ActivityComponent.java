package com.peiyuan.rxandretro.component;

import android.app.Activity;

import com.peiyuan.rxandretro.module.ActivityModule;
import com.peiyuan.rxandretro.module.ApplicationModule;
import com.peiyuan.rxandretro.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/1/23 0023.
 */
@PerActivity
@Component(modules = {ActivityModule.class},dependencies = ApplicationComponent.class)
public interface ActivityComponent{

    MainActivity inject(MainActivity mainActivity);

    Activity activity();

}

package com.peiyuan.common.util;

import android.app.Activity;
import android.app.Instrumentation;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hexun on 2016/4/1.
 */
public class HookUtils {

    public static void hookStartActivity() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field field = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            field.setAccessible(true);
            Object currentThread = field.get(null);

            Field instrumentation = activityThreadClass.getDeclaredField("mInstrumentation");
            instrumentation.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) instrumentation.get(currentThread);

            EvilInstrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);
            instrumentation.set(currentThread, evilInstrumentation);
            Log.d("TEST","finish");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

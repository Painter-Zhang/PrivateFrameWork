package com.peiyuan.common.util;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import timber.log.Timber;

/**
 * Created by hexun on 2016/4/1.
 */
public class EvilInstrumentation extends Instrumentation {

    private Instrumentation base;

    public EvilInstrumentation(Instrumentation base) {
        this.base = base;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Log.d("EvilInstrumentation","\n执行了startActivity, 参数如下: \n" + "who = [" + who + "], " +
                "\ncontextThread = [" + contextThread + "], \ntoken = [" + token + "], " +
                "\ntarget = [" + target + "], \nintent = [" + intent +
                "], \nrequestCode = [" + requestCode + "], \noptions = [" + options + "]");

        Method execStartActivityMethod = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
        execStartActivityMethod.setAccessible(true);
        return (ActivityResult) execStartActivityMethod.invoke(base,who,contextThread,token,target,intent,requestCode,options);
    }
}

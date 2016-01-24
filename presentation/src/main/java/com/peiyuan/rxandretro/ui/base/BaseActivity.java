package com.peiyuan.rxandretro.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.peiyuan.rxandretro.component.ApplicationComponent;

/**
 * Created by Administrator on 2016/1/23 0023.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(RxApplication.get(this).getApplicationComponent());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract void setupActivityComponent(ApplicationComponent applicationComponent);
}

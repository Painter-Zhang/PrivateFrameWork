package com.peiyuan.rxandretro.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;

import com.peiyuan.common.util.DisplayUtils;
import com.peiyuan.rxandretro.R;
import com.peiyuan.rxandretro.component.ApplicationComponent;
import com.peiyuan.rxandretro.component.DaggerActivityComponent;
import com.peiyuan.rxandretro.module.ActivityModule;
import com.peiyuan.rxandretro.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hexun on 2016/3/28.
 */
public class MaterialTestActivity extends BaseActivity {


    @Override
    protected void setupActivityComponent(ApplicationComponent applicationComponent) {
        DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        ButterKnife.bind(this);

        int width = DisplayUtils.getWindowWidth(this);

    }
}

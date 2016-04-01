package com.peiyuan.rxandretro.ui.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.peiyuan.rxandretro.R;

import timber.log.Timber;

/**
 * Created by hexun on 2016/3/29.
 */
public class ToolbarBehavior extends CoordinatorLayout.Behavior<RelativeLayout> {

    private static final String TAG = "ToolbarAlphaBehavior";
    private int offset = 0;
    private int startOffset = 0;
    private int endOffset = 0;
    private Context context;
    private long targetId;

    public ToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//
//        TypedArray a = context.getApplicationContext().obtainStyledAttributes(attrs,R.styleable.circle_behavior);
//        if (a!=null){
//            for (int i = 0; i < a.getIndexCount(); i++) {
//                int attr = a.getIndex(i);
//                if(a.getIndex(i) == R.styleable.circle_behavior_targetId){
//                    targetId = a.getResourceId(attr, -1);
//                }
//            }
//        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RelativeLayout child, View dependency) {
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RelativeLayout child, View dependency) {
        float dependencyStopY = parent.getHeight()-child.getHeight()+getStatusBarHeight();
        float zoom = dependency.getY()/dependencyStopY;


        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RelativeLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RelativeLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        startOffset = 0;
        endOffset = coordinatorLayout.getHeight() - getActionBarHeight();
        offset += dyConsumed;
        Timber.d(offset+";"+dyConsumed);
        if (offset <= startOffset) {  //alpha为0
            child.getBackground().setAlpha(0);
        } else if (offset > startOffset && offset < endOffset) { //alpha为0到255
            float precent = (float) (offset - startOffset) / endOffset;
            int alpha = Math.round(precent * 255);
            child.getBackground().setAlpha(alpha);
        } else if (offset >= endOffset) {  //alpha为255
            child.getBackground().setAlpha(255);
        }
    }

    private int getActionBarHeight() {
        int result;
        result = context.getResources().getDimensionPixelOffset(android.support.design.R.dimen.abc_action_bar_default_height_material);
        return result;
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}

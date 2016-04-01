package com.peiyuan.rxandretro.ui.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.peiyuan.common.util.DisplayUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hexun on 2016/3/28.
 */
public class PotraitBehavior extends CoordinatorLayout.Behavior<CircleImageView> {

    private Context context;
    private float dependencyStartY;
    private float childStartY;
    private float childSize;
    private boolean isDown;
    private int targetId;

    public PotraitBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;


//        TypedArray a = context.getApplicationContext().obtainStyledAttributes(attrs, R.styleable.circle_behavior);
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
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        maybeInitProperties(child, dependency);

//        float maxScrollYDistance = dependencyStartY - dependencyStopY;
//        float currentDistance =  dependencyStartY - dependency.getY();
//        double i = (dependencyStartY-dependencyStopY)/(childStartY - childStopY);
//        double j = (dependency.getY() - dependencyStopY)/maxScrollYDistance;
//        double zoomSize = 1 - (dependencyStartY - dependency.getY()) / (maxScrollYDistance);
//        double positionY = (childStartY - currentDistance)/ i - dependency.getHeight();
//        double positionY = childStartY - currentDistance*(i);
//        double positionY = childStopY+(childStartY-childStopY)*j;
//        if (child.getY() + child.getHeight() / 2 <= dependency.getHeight() / 2) {
//            positionY = dependency.getHeight() / 2 - child.getHeight() / 2;
//        }
//        child.setY((float) positionY);
//
//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//        lp.width = (int) (childSize * zoomSize);
//        lp.height = (int) (childSize * zoomSize);
//        if (lp.width <= 75) {
//            lp.width = (75);
//            lp.height = (75);
//        }
//        child.setLayoutParams(lp);

//        float scrollDistance =
//        FrameLayout layout = (FrameLayout) parent.findViewById(R.id.appbar_content);
//        float dependencyStopY = parent.getHeight() - layout.getHeight() + getStatusBarHeight();
//        float zoom = layout.getY() / dependencyStopY;
//


        float scrollDistance = dependency.getY() - getStatusBarHeight();
        float maxDistance = DisplayUtils.dip2px(context, 150) - dependency.getHeight() / 2;
//        int width = DisplayUtils.getWindowWidth(context);

        double zoom = scrollDistance / maxDistance;

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.width = (int) (DisplayUtils.dip2px(context, 35) * (zoom * 1 + 1));
        lp.height = (int) (DisplayUtils.dip2px(context, 35) * (zoom * 1 + 1));
//        if (dependency.getY()==getStatusBarHeight()){
//            child.setX( width - child.getHeight()/2);
//            child.setY(getStatusBarHeight()+dependency.getHeight()/2 - child.getHeight()/2);
//        }

        float marginTopStart = DisplayUtils.dip2px(context,5);
        float marginTopEnd = DisplayUtils.dip2px(context, 150) - child.getHeight() / 2;
        float delta = marginTopEnd - marginTopStart;
        lp.topMargin = (int) (marginTopStart + zoom*0.6*delta)+getStatusBarHeight();

        child.setLayoutParams(lp);
//        float offset = (float) (dependency.getHeight()/2 * (zoom));
//        child.setY(dependency.getY()-offset);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, CircleImageView child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, CircleImageView child, View target, int dx, int dy, int[] consumed) {
        if (dy > 0) {
            isDown = false;
        } else {
            isDown = true;
        }

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    private void maybeInitProperties(CircleImageView child, View dependency) {
        if (childStartY == 0) {
            childStartY = child.getY();
        }
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    private int getActionBarHeight() {
        int result;
        result = context.getResources().getDimensionPixelOffset(android.support.design.R.dimen.abc_action_bar_default_height_material);
        return result;
    }


}

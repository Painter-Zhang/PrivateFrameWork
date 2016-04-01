package com.peiyuan.rxandretro.ui.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.peiyuan.common.util.DisplayUtils;
import com.peiyuan.rxandretro.R;

/**
 * Created by hexun on 2016/3/31.
 */
public class PopKeyboard extends PopupWindow {

    private LinearLayout mMenuView;
    private Context context;

    public PopKeyboard(Context context, EditText editText) {
        super(context);
        this.context = context;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        mMenuView = (LinearLayout) layoutInflater.inflate(R.layout.pop_keyboard_view,null);
        new KeyBoardUtil(mMenuView,context,editText);

        this.setContentView(mMenuView);
        this.setWidth(DisplayUtils.getWindowWidth(context));
        this.setHeight(DisplayUtils.dip2px(context,230));
//        this.setHeight(210);
        this.setBackgroundDrawable(null);
        this.setFocusable(true);

    }

    public void show(View view){
        this.showAtLocation(view, Gravity.TOP, 0, DisplayUtils.getWindowHeight(context));
    }

}

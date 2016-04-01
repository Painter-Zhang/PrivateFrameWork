package com.peiyuan.rxandretro.ui.view;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.peiyuan.rxandretro.R;


/**
 * Created by user on 2016/3/21.
 */
public class KeyBoardUtil {

    private Context ct;
    private Activity act;
    private KeyboardView keyboardView;
    private Keyboard key1 ,key2;
    private boolean isnum = false;
    private EditText et;



    public KeyBoardUtil(Activity act, Context ct, EditText et){
        this.ct = ct;
        this.act = act;
        this.et = et;

        keyboardView = (KeyboardView)act.findViewById(R.id.pop_keyboard);
        key1 = new Keyboard(ct, R.xml.qwerty);
        key2 = new Keyboard(ct, R.xml.symbols);

        keyboardView.setKeyboard(key1);
        keyboardView.setKeyboard(key2);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);


    }
    public KeyBoardUtil(View act, Context ct, EditText et){
        this.ct = ct;
        this.et = et;

        keyboardView = (KeyboardView)act.findViewById(R.id.pop_keyboard);
        key1 = new Keyboard(ct, R.xml.qwerty);
        key2 = new Keyboard(ct, R.xml.symbols);

        keyboardView.setKeyboard(key2);
        keyboardView.setKeyboard(key1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(listener);
    }


    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int i) {

        }

        @Override
        public void onRelease(int i) {

        }

        @Override
        public void onKey(int i, int[] ints) {

            Editable edit = et.getText();
            int start = et.getSelectionStart();


            if(i == Keyboard.KEYCODE_MODE_CHANGE){//数字键盘切换

                if(isnum){
                    isnum = false;
                    keyboardView.setKeyboard(key1);
                }else{
                    isnum = true;
                    keyboardView.setKeyboard(key2);
                }
            }else if(i == Keyboard.KEYCODE_DELETE) {//回退
                if (edit != null && edit.length() > 0) {
                    if (start > 0) {
                        edit.delete(edit.length() - 1, edit.length());
                    }
                }
            }else if(i == 57600) {//600
                edit.insert(start,"600");
            }else if(i == 57601) {//601
                edit.insert(start,"601");
            }else if(i == 57300) {//300
                edit.insert(start,"300");
            }else if(i == 57000) {//000
                edit.insert(start,"000");
            }else if(i == 57002) {//002
                edit.insert(start,"002");
            }else{
                edit.insert(start,Character.toString((char)i));
            }




        }

        @Override
        public void onText(CharSequence charSequence) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };



    public void showView(){
        keyboardView.setVisibility(View.VISIBLE);
    }

    public void hideView(){
        keyboardView.setVisibility(View.INVISIBLE);
    }


}

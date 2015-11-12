package com.creditease.common.view;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.creditease.common.R;


/**
 * 类描述:自定义软件盘
 * 类名称：SoftBoard
 * @version: 1.0
 * @author: cd.hou
 * @time: 2015年8月20日 下午1:32:48
 */
public class SoftBoard {
	

	private Activity act;
	private Context ctx;
    private KeyboardView keyboardView;
    private Keyboard k;
    private EditText ed;
        
    public SoftBoard(Activity act, Context ctx, EditText edit) {
    	
        this.act = act;
        this.ctx = ctx;
    	this.ed = edit;
        k = new Keyboard(ctx, R.xml.symbols);
        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(k);  
        keyboardView.setEnabled(true);  
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(listener);  
    }  
    private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
    	
        @Override
        public void swipeUp() {
        	
        }  
   
        @Override
        public void swipeRight() {  
        }  
   
        @Override
        public void swipeLeft() {  
        }  
   
        @Override
        public void swipeDown() {  
        }  
   
        @Override
        public void onText(CharSequence text) {
        }  
   
        @Override
        public void onRelease(int primaryCode) {  
        }  
   
        @Override
        public void onPress(int primaryCode) {  
        }  
                
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {  
                Editable editable = ed.getText();
                int start = ed.getSelectionStart();  
                if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                        if (editable != null && editable.length() > 0) {  
                                if (start > 0) {  
                                        editable.delete(start - 1, start);  
                                }  
                        }  
                 } else { //将要输入的数字现在编辑框中 
                                editable.insert(start, Character.toString((char) primaryCode));
                 }  
         }  
        };  
       
    public void showKeyboard() {  
    	//InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
		//imm.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        int visibility = keyboardView.getVisibility();  
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }  
        keyboardView.bringToFront();
    } 
    
    public void hideKeyboard(){
    	int visibility = keyboardView.getVisibility();  
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }  
    }
    
    public boolean isShowing(){
    	return keyboardView.getVisibility() == View.VISIBLE;
    }
}  

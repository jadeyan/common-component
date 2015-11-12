package com.creditease.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;

import com.creditease.common.view.SoftBoard;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 类描述：自定义软键盘实现
 * 类名称：SoftBoardActivity
 *
 * @version: 1.0
 * @author: George
 * @time: 2015/11/12 17:33
 */
public class SoftBoardActivity extends Activity{

    private EditText mEt;
    private SoftBoard id_keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_show_layout);
        findView();
    }

    private void findView(){
        mEt = (EditText) findViewById(R.id.et_soft);
        id_keyboard = new SoftBoard(SoftBoardActivity.this,this,mEt);
        id_keyboard.showKeyboard();

        //反射解决光标跳转问题
        Class <EditText> cls = EditText.class;
        try{
            Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",boolean.class);
            setShowSoftInputOnFocus.setAccessible(false);
            setShowSoftInputOnFocus.invoke(mEt,false);
        }catch (NoSuchMethodException e){

        }catch (IllegalAccessException e){

        }catch (IllegalArgumentException e){

        }catch (InvocationTargetException e){

        }
    }

    //返回键隐藏键盘
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && id_keyboard != null && id_keyboard.isShowing()){
            id_keyboard.hideKeyboard();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

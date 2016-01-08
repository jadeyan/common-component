package com.creditease.common;

import com.creditease.common.event.AsyncEvent;
import com.creditease.common.event.BackGroundEvent;
import com.creditease.common.event.MainEvent;
import com.creditease.common.event.PostEvent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

/**
 * @author shihaisun@creditease.cn
 * @version 3.3.0
 * @title CreditPerson_3.3.0
 * @package songol.com.eventbusdemo
 * @description TODO
 * @date 2015-12-15  10:24
 */
public class EventBusActivity extends Activity {
    private static final String TAG = "EventBusActivity";
    TextView receive_activity_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        EventBus.getDefault().register(this);
        receive_activity_desc=(TextView)findViewById(R.id.receive_activity_desc);
    }
    public void methodPost(View view) {
        Log.d(TAG, "PostThread-->" + Thread.currentThread().getId());
        PostEvent postEvent=new PostEvent();
        postEvent.setIsMainThread(true);
        postEvent.setMsg("PostEvent");
        EventBus.getDefault().post(postEvent);

    }

    public void methodMain(View view) {

        Log.d(TAG, "PostThread-->" + Thread.currentThread().getId());
        MainEvent mainEvent=new MainEvent();
        mainEvent.setIsMainThread(true);
        mainEvent.setMsg("MainEvent");
        EventBus.getDefault().post(mainEvent);

    }

    public void methodBack(View view) {
        Log.d(TAG, "PostThread-->" + Thread.currentThread().getId());

        BackGroundEvent backGroundEvent=new BackGroundEvent();
        backGroundEvent.setIsMainThread(true);
        backGroundEvent.setMsg("BackEvent");
        EventBus.getDefault().post(backGroundEvent);

    }

    public void methodAsync(View view) {
        Log.d(TAG, "PostThread-->" + Thread.currentThread().getId());

        AsyncEvent asyncEvent=new AsyncEvent();
        asyncEvent.setIsMainThread(true);
        asyncEvent.setMsg("AsyncEvent");
        EventBus.getDefault().post(asyncEvent);

    }

    public void methodSubPost(View view) {
        new Thread() {
            public void run() {
                Log.d(TAG, "PostThread-->" + Thread.currentThread().getId());
                PostEvent postEvent=new PostEvent();
                postEvent.setIsMainThread(false);
                postEvent.setMsg("PostEvent");
                EventBus.getDefault().post(postEvent);

            }

        }.start();

    }

    public void methodSubMain(View view) {
        new Thread() {
            public void run() {
                Log.d(TAG, "PostThread-->" + Thread.currentThread().getId());
                MainEvent mainEvent=new MainEvent();
                mainEvent.setIsMainThread(false);
                mainEvent.setMsg("MainEvent");
                EventBus.getDefault().post(mainEvent);

            }
        }.start();

    }

    public void methodSubBack(View view) {
        new Thread() {
            public void run() {
                Log.d(TAG, "PostThread-->" + Thread.currentThread().getId());

                BackGroundEvent backGroundEvent=new BackGroundEvent();
                backGroundEvent.setIsMainThread(false);
                backGroundEvent.setMsg("BackEvent");
                EventBus.getDefault().post(backGroundEvent);

            }
        }.start();

    }

    public void methodSubAsync(View view) {
        new Thread() {
            public void run() {
                Log.d(TAG, "PostThread-->" + Thread.currentThread().getId());
                AsyncEvent asyncEvent=new AsyncEvent();
                asyncEvent.setIsMainThread(false);
                asyncEvent.setMsg("AsyncEvent");
                EventBus.getDefault().post(asyncEvent);

            }
        }.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 使用onEvent来接收事件，那么接收事件和分发事件在一个线程中执行
     */
    public void onEvent(PostEvent event) {

        String str = "OnEvent-->" + Thread.currentThread().getId()+"   msg:  "+event.getMsg();
        if(event.isMainThread())
        {
            setText(str);
        }else
        {
            Log.i(TAG, str);
        }


    }

    /**
     * 使用onEventMainThread来接收事件，那么不论分发事件在哪个线程运行，接收事件永远在UI线程执行，
     * 这对于android应用是非常有意义的，
     */
    public void onEventMainThread(MainEvent event)
    {

        String str = "onEventMainThread-->" + Thread.currentThread().getId()+"   msg:  "+event.getMsg();
        if(event.isMainThread())
        {
            setText(str);
        }else
        {
            showToast(str);
        }

    }

    /**
     * 使用onEventBackgroundThread来接收事件，如果分发事件在子线程运行，那么接收事件直接在同样线程
     * 运行，如果分发事件在UI线程，那么会启动一个子线程运行接收事件
     */
    public void onEventBackgroundThread(BackGroundEvent event) {

        String str =  "onEventBackgroundThread-->" + Thread.currentThread().getId()+"   msg:  "+event.getMsg();
        Log.i(TAG, str);


    }

    /**
     * 使用onEventAsync接收事件，无论分发事件在（UI或者子线程）哪个线程执行，接收都会在另外一个子线程执行
     */
    public void onEventAsync(AsyncEvent event) {


        String str =  "onEventAsync-->" + Thread.currentThread().getId()+"   msg:  "+event.getMsg();
        Log.i(TAG, str);
    }



    private void  setText(String msg)
    {
        receive_activity_desc.setText(msg);
    }
    /**
     * 返回主页
     */
    public void backAgain(View view)
    {
        finish();
    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }








}

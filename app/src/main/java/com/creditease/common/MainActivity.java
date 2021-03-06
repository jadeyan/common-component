package com.creditease.common;

import com.creditease.common.net.WebVideoFullScreenActivity;
import com.creditease.common.net.WebViewActivity;
import com.creditease.common.service.FunctionBallService;
import com.creditease.common.view.ListViewLoadingActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent intent = new Intent(MainActivity.this, FunctionBallService.class);
        startService(intent);
        init();
    }

    private void init() {
        findViewById(R.id.btn_listview).setOnClickListener(this);
        findViewById(R.id.btn_crashhandler).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_softboard).setOnClickListener(this);
        findViewById(R.id.btn_webview).setOnClickListener(this);
        findViewById(R.id.btn_webVideo).setOnClickListener(this);
        findViewById(R.id.btn_eventBus).setOnClickListener(this);
        findViewById(R.id.btn_handler).setOnClickListener(this);
    }

    private Object o = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crashhandler:
                android.util.Log.e("TAG", "----:" + o.toString());
                break;
            case R.id.btn_sms:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, SMSAutoGetActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_softboard:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, SoftBoardActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_webview:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, WebViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_webVideo:
                Intent intent4 = new Intent();
                intent4.setClass(MainActivity.this, WebVideoFullScreenActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_eventBus:
                Intent intent5 = new Intent();
                intent5.setClass(MainActivity.this, EventBusActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_handler:
                Intent intent6 = new Intent();
                intent6.setClass(MainActivity.this, HandlerActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_listview:
                Intent intent7 = new Intent();
                intent7.setClass(MainActivity.this, ListViewLoadingActivity.class);
                startActivity(intent7);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(MainActivity.this, FunctionBallService.class);
        stopService(intent);
        super.onDestroy();
    }
}

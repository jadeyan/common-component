package com.creditease.common;

import com.creditease.common.net.WebVideoFullScreenActivity;
import com.creditease.common.net.WebViewActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        init();
    }

    private void init() {
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_softboard).setOnClickListener(this);
        findViewById(R.id.btn_webview).setOnClickListener(this);
        findViewById(R.id.btn_webVideo).setOnClickListener(this);
        findViewById(R.id.btn_eventBus).setOnClickListener(this);
        findViewById(R.id.btn_handler).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            default:
                break;
        }
    }
}

package com.creditease.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        init();
    }

    private void init(){
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_softboard).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sms:
                Intent mIntent = new Intent();
                mIntent.setClass(MainActivity.this,SMSAutoGetActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_softboard:
                Intent mIntent1 = new Intent();
                mIntent1.setClass(MainActivity.this,SoftBoardActivity.class);
                startActivity(mIntent1);
                break;
            default:
                break;
        }
    }
}

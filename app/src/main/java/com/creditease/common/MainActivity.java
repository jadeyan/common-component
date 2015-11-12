package com.creditease.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.creditease.common.view.SoftBoard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

package com.creditease.common.net;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebViewActivity extends Activity {

    WebView mWebView;
    LinearLayout mLlyParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mLlyParent = new LinearLayout(getApplicationContext());
        mLlyParent.setOrientation(LinearLayout.VERTICAL);
        mWebView = new WebView(getApplicationContext());

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //支持缩放
        webSettings.setBuiltInZoomControls(true);
        //设定缩放控件隐藏
        webSettings.setDisplayZoomControls(true);

        //支持缓存
        webSettings.setAppCacheEnabled(true);
//        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        settings.setDomStorageEnabled(true);

        //打开页面时自适应屏幕
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);

        //如果需要用户输入信息则必须设置支持获取手势焦点
//        mWebView.requestFocusFromTouch();

        //屏蔽WebView中LongClick
//        wv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return true;
//            }
//        });

        //设置最后加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }

        mLlyParent.addView(mWebView);
        setContentView(mLlyParent);

        mWebView.loadUrl("http://www.yirendai.com/");

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!view.getSettings().getLoadsImagesAutomatically()) {
                    view.getSettings().setLoadsImagesAutomatically(true);
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String
                    failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // 设置无法打开时展示自定义的view
//                mErrorFrame.setVisibility(View.VISIBLE);
            }
        });
    }

    public void removeCookie() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
        CookieManager.getInstance().removeSessionCookie();
    }

    public void removeCacheAndHistory(WebView view) {
        view.clearCache(true);
        view.clearHistory();
    }

    @Override
    protected void onDestroy() {
        mLlyParent.removeView(mWebView);
        mWebView.stopLoading();
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
        mLlyParent = null;
        super.onDestroy();
    }
}

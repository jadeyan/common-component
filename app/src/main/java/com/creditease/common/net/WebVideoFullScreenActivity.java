package com.creditease.common.net;

import com.creditease.common.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class WebVideoFullScreenActivity extends Activity {

    private WebView mWebView;
    private static final String VIDEO_URL = "file:///android_asset/index.html";

    private DefaultWebChromeClient mWebChromeClient = new DefaultWebChromeClient();

    private void configWebView() {

        WebSettings settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        //do not allow to zoom
        settings.setLoadsImagesAutomatically(false);
        settings.setUseWideViewPort(false);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(settings.LOAD_DEFAULT);
        settings.setAllowFileAccess(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //chrome browser
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
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

        settings.setDatabaseEnabled(true);
        settings.setGeolocationEnabled(true);
        String cacheDir = this.getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(cacheDir);
        settings.setAppCacheMaxSize(1024 * 1024 * 10);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_video);
        mWebView = (WebView) findViewById(R.id.wv_video);
        configWebView();
        mWebView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        mWebView.loadUrl(VIDEO_URL);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mWebView.stopLoading();
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
        super.onDestroy();
    }

    /**
     * 设置全屏
     */
    private void setFullScreen() {
        // 设置全屏的相关属性，获取当前的屏幕状态，然后设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 退出全屏
     */
    private void quitFullScreen() {
        // 声明当前屏幕状态的参数并获取
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    private class DefaultWebChromeClient extends WebChromeClient {
        // 一个回调接口使用的主机应用程序通知当前页面的自定义视图已被撤职
        CustomViewCallback customViewCallback;

        // 进入全屏的时候
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            // 赋值给callback
            customViewCallback = callback;
            // 设置webView隐藏
            mWebView.setVisibility(View.GONE);
            // 声明video，把之后的视频放到这里面去
            FrameLayout video = (FrameLayout) findViewById(R.id.fl_video_view);
            // 将video放到当前视图中
            video.addView(view);
            // 横屏显示
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            // 设置全屏
            setFullScreen();
        }

        // 退出全屏的时候
        @Override
        public void onHideCustomView() {
            if (customViewCallback != null) {
                // 隐藏掉
                customViewCallback.onCustomViewHidden();
            }
            // 用户当前的首选方向
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
            // 退出全屏
            quitFullScreen();
            // 设置WebView可见
            mWebView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    }
}


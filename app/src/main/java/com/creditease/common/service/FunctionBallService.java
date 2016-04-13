package com.creditease.common.service;

import com.creditease.common.R;

import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FunctionBallService extends Service {

    ImageView mIvPlus;
    ImageView mIvGRCode;
    ImageView mIvRecommend;
    ImageView mIvSearch;
    private FrameLayout mFlBallFunction;
    private FrameLayout mFlBarFunction;
    private boolean mIsFuncBallShowing = false;

    //定义浮动窗口布局
    RelativeLayout mFloatLayout;
    WindowManager.LayoutParams wmParams;
    //创建浮动窗口设置布局参数的对象
    WindowManager mWindowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatView();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createFloatView() {
        wmParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication()
                .WINDOW_SERVICE);
        //设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;

        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.LEFT | Gravity.BOTTOM;

        // 以屏幕左上角为原点，设置x、y初始值
        wmParams.x = 0;
        wmParams.y = 0;

        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局
        mFloatLayout = (RelativeLayout) inflater.inflate(R.layout.function_ball_service_layout,
                null);
        //添加mFloatLayout
        mWindowManager.addView(mFloatLayout, wmParams);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //浮动窗口按钮
        mFlBallFunction = (FrameLayout) mFloatLayout.findViewById(R.id.fl_ball_function);
        mFlBarFunction = (FrameLayout) mFloatLayout.findViewById(R.id.fl_bar_function);
        mIvPlus = (ImageView) mFloatLayout.findViewById(R.id.iv_plus);
        mIvGRCode = (ImageView) mFloatLayout.findViewById(R.id.iv_grcode);
        mIvRecommend = (ImageView) mFloatLayout.findViewById(R.id.iv_recommend);
        mIvSearch = (ImageView) mFloatLayout.findViewById(R.id.iv_search);

        mFlBallFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mIsFuncBallShowing) {
                    wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                    mWindowManager.updateViewLayout(mFloatLayout, wmParams);

                    startBackwardAnimationSet(mIvSearch, 400);
                    startBackwardAnimationSet(mIvRecommend, 300);
                    startBackwardAnimationSet(mIvGRCode, 200);
                    rotateyAnimRun(mIvPlus, 45f, 0f, 500);
                    mIsFuncBallShowing = false;
                } else {
                    wmParams.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    mWindowManager.updateViewLayout(mFloatLayout, wmParams);

                    mIvGRCode.setVisibility(View.VISIBLE);
                    mIvRecommend.setVisibility(View.VISIBLE);
                    mIvSearch.setVisibility(View.VISIBLE);
                    rotateyAnimRun(mIvPlus, 0f, 45f, 500);
                    mIsFuncBallShowing = true;
                }
            }
        });

        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFunctions();
            }
        });
        mIvRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFunctions();
            }
        });
        mIvGRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFunctions();
            }
        });


        ViewTreeObserver vtoBank3 = mIvSearch.getViewTreeObserver();
        vtoBank3.addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mIsFuncBallShowing) {
                    startForwardAnimationSet(mIvSearch, 400);
                }
            }
        });
        ViewTreeObserver vtoBank2 = mIvRecommend.getViewTreeObserver();
        vtoBank2.addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mIsFuncBallShowing) {
                    startForwardAnimationSet(mIvRecommend, 300);
                }
            }
        });
        ViewTreeObserver vtoBank1 = mIvGRCode.getViewTreeObserver();
        vtoBank1.addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mIsFuncBallShowing) {
                    startForwardAnimationSet(mIvGRCode, 200);
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        if (mFloatLayout != null) {
            mWindowManager.removeView(mFloatLayout);
        }
        super.onDestroy();
    }

    private void hideFunctions() {
        if (mIsFuncBallShowing) {
            startBackwardAnimationSet(mIvSearch, 400);
            startBackwardAnimationSet(mIvRecommend, 300);
            startBackwardAnimationSet(mIvGRCode, 200);
            rotateyAnimRun(mIvPlus, 45f, 0f, 500);
            mIsFuncBallShowing = false;
        }
    }

    private void rotateyAnimRun(View view, float fromDegree, float toDegree, long
            duration) {
        ObjectAnimator.ofFloat(view, "rotation", fromDegree, toDegree)
                .setDuration(duration)
                .start();
    }

    private void startForwardAnimationSet(View view, long duration) {
        AnimationSet animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation
                .RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        Animation translateAnimation = new TranslateAnimation(mFlBarFunction.getX() -
                mFlBarFunction.getWidth() / 2 - view.getX() - ((ViewGroup
                .MarginLayoutParams)
                view.getLayoutParams()).leftMargin, 0f, 0f, 0f);
        animationSet.addAnimation(translateAnimation);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setDuration(duration);
        view.startAnimation(animationSet);
    }

    private void startBackwardAnimationSet(final View view, long duration) {
        AnimationSet animationSet = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation
                .RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        Animation translateAnimation = new TranslateAnimation(0f, mFlBarFunction.getX() -
                mFlBarFunction.getWidth() / 2 - view.getX() - ((ViewGroup
                .MarginLayoutParams)
                view.getLayoutParams()).leftMargin, 0f, 0f);
        animationSet.addAnimation(translateAnimation);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setDuration(duration);
        BallAnimationListener listener = new BallAnimationListener(view);
        animationSet.setAnimationListener(listener);
        view.startAnimation(animationSet);
    }

    class BallAnimationListener implements Animation.AnimationListener {

        View view;

        public BallAnimationListener(View view) {
            this.view = view;
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

}

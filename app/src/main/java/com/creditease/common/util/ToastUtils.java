package com.creditease.common.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.creditease.common.R;

/**
 * 类描述：$Tosat show as popupwindow
 * 类名称：$ToastUtils
 *
 * @version: 1.0
 * @author: George
 * @time: 2016/3/11 10:31
 */
public class ToastUtils {
    /**
     * 方法描述：PopupWindow Toast at center
     * @author: cd.hou
     * @time: 2015-11-20 下午5:12:25
     */
    public static void showPopupToastCenter(Context context, String msg, View rootView) {
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.toast_popup_layout, null);
        TextView tv = (TextView) contentView.findViewById(R.id.message_toast);
        tv.setText(msg);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.button_no));
        //int[] location = new int[2];
        //view.getLocationInWindow(location);
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
        //popupWindow.showAsDropDown(view,Gravity.CENTER_HORIZONTAL,0);
    }

    /**
     * 方法描述：PopupWindow Toast
     * @author: cd.hou
     * @time: 2015-11-20 下午5:12:25
     */
    public static void showPopupToastTop(Activity act, String msg, View rootView) {
        View contentView = LayoutInflater.from(act).inflate(
                R.layout.toast_popup_layout, null);
        TextView tv = (TextView) contentView.findViewById(R.id.message_toast);
        tv.setText(msg);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(act.getResources().getDrawable(R.drawable.button_no));
        //int[] location = new int[2];
        //view.getLocationInWindow(location);
        Rect rect = new Rect();
        act.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int height = 50 + rect.top;//50 is act.getResources().getDimensionPixelSize(R.dimen.main_top_height)
        popupWindow.showAtLocation(rootView, Gravity.TOP, 0,height);
        //popupWindow.showAsDropDown(view,Gravity.CENTER_HORIZONTAL,0);
    }
}

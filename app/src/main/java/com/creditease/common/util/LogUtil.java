package com.creditease.common.util;

import android.util.Log;

/**
 * Description: LOG相关
 *
 * @author ly
 * @version v1.0 2013-5-1 09:30
 */
public class LogUtil {

    /**
     * 打印Log信息
     */
    public static void Log(String message) {
        if (Config.DEBUG) {
            Log.d(Config.LOG_TAG, "---" + System.currentTimeMillis() + "---" + message);
        }
    }

    /**
     * 打印Log信息
     */
    public static void Log(String tag, String message) {
        if (Config.DEBUG) {
            Log.d(tag, "---" + System.currentTimeMillis() + "---" + message);
        }
    }

    /**
     * 打印错误Log信息
     */
    public static void LogError(String tag, String message) {
        if (Config.DEBUG) {
            Log.e(tag, "---" + System.currentTimeMillis() + "---" + message);
        }
    }

    /**
     * 打印低级别Log信息
     */
    public static void LogVerbose(String tag, String message) {
        if (Config.DEBUG) {
            Log.v(tag, "---" + System.currentTimeMillis() + "---" + message);
        }
    }
}

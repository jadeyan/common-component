package com.creditease.common.event;

public class PostEvent {
    private static final String TAG = "PostEvent";
    private boolean is_Main_Thread = false;
    private String msg;

    public boolean isMainThread() {
        return is_Main_Thread;
    }

    public void setIsMainThread(boolean isMainThread) {
        this.is_Main_Thread = isMainThread;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

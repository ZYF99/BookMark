package com.lxh.bookmark;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtil.getInstance(this, "cache");
    }
}

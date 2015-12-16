package com.example.uit.bannhanong;

import android.app.Application;
import android.content.Context;

import com.example.uit.bannhanong.utils.TypeFaceManagerUtils;

public class MainApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        TypeFaceManagerUtils.init(getApplicationContext());
    }

    public static Context getContext() {
        return mContext;
    }
}
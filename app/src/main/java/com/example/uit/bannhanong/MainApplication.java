package com.example.uit.bannhanong;

import android.app.Application;
import android.content.Context;

import com.example.uit.bannhanong.socketio.MySocket;
import com.example.uit.bannhanong.utils.TypeFaceManagerUtils;

public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();

    private static Context mContext;
    private static MySocket mMySocket;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        TypeFaceManagerUtils.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mMySocket != null) {
            mMySocket.disconnectSocket();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static MySocket   getMySocket() {
        return mMySocket;
    }

    public static void setMySocket(MySocket mySocket) {
        mMySocket = mySocket;
    }
}
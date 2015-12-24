package com.example.uit.bannhanong;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.uit.bannhanong.DTO.Chat;
import com.example.uit.bannhanong.DTO.User;
import com.example.uit.bannhanong.connection.response.ChatResponse;
import com.example.uit.bannhanong.socketio.MySocket;
import com.example.uit.bannhanong.utils.TypeFaceManagerUtils;
import com.example.uit.bannhanong.utils.UserPref;

import de.greenrobot.event.EventBus;

public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();

    private static Context mContext;
    private static MySocket mMySocket;
    private EventBus mBus = EventBus.getDefault();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        TypeFaceManagerUtils.init(getApplicationContext());
        mBus.register(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mMySocket != null) {
            mMySocket.disconnectSocket();
        }
    }

    public void onEvent(ChatResponse chatResponse) {

        Chat data = chatResponse.data;

        Log.i("On", "okok");

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
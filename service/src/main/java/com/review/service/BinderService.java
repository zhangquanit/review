package com.review.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author 张全
 */

public class BinderService extends Service {
    MyBinder binder = new MyBinder();
    final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "BinderService onBind");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "BinderService onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "BinderService onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "BinderService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "BinderService onDestroy");
    }


}

package com.review.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author 张全
 */

public class BackgroundService extends Service {
    final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "BackgroundService onCreate");


    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "BackgroundService onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = null;
        if (null != intent) {
            msg = intent.getStringExtra("msg");
        }
        Log.d(TAG, "BackgroundService onStartCommand msg="+msg);
        Log.d(TAG, "BackgroundService onStartCommand,thread=" + Thread.currentThread().getName() + ",startId=" + startId + ",intent=" + intent + ",flags=" + flags);
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "BackgroundService onDestroy");
    }
}

package com.review.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 运行在另外一个进程中的主线程中
 * 不支持与其他进程内的Activity之间进行bind.
 *
 * @author 张全
 */

public class ProcessService extends Service {
    final String TAG = "MyService";
    MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "ProcessService onBind");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "ProcessService onCreate,pid=" + android.os.Process.myPid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "ProcessService onStartCommand,pid=" + android.os.Process.myPid() + ",thread=" + Thread.currentThread().getName());
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "ProcessService onStartCommand,..........end");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ProcessService onDestroy,pid=" + android.os.Process.myPid());
    }
}

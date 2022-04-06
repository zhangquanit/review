package com.review.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;

import androidx.annotation.Nullable;

/**
 * @author zhangquan
 */
public class IntentFilterService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println("IntentFilterService onCreate,pid=" + Process.myPid() + ",thread=" + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("IntentFilterService onStartCommand,pid=" + Process.myPid() + ",thread=" + Thread.currentThread().getName());
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("IntentFilterService onDestory,pid=" + Process.myPid() + ",thread=" + Thread.currentThread().getName());
    }
}

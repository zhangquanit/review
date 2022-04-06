package com.review.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * @author 张全
 */

public class MyIntentService extends IntentService {
    private final String TAG="MyService";
    public MyIntentService(){
        super("MyIntentService"); //必须传入一个name，否则会抛出异常
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"MyIntentService onStartCommand,flags="+flags+",startId="+startId+",thread="+Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"MyIntentService onDestroy,thread="+Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"MyIntentService,onHandleIntent ,thread="+Thread.currentThread().getName());
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

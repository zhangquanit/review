package com.review.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author 张全
 */

public class ForgroundService extends Service {
    final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "ForgroundService onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "ForgroundService onCreate");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("通知标题")
                .setContentText("通知内容")
                .setTicker("通知来了")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        startForeground(1, notification);

        //停止前台服务
//        stopForeground(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "ForgroundService onStartCommand");
        return Service.START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ForgroundService onDestroy");
    }
}

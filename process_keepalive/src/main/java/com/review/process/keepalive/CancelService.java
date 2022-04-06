package com.review.process.keepalive;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

/**
 */
public class CancelService extends Service {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("------CancelService");

        //发送与KeepLiveService中ID相同的Notification，然后将其取消并取消自己的前台显示
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        startForeground(250, builder.build());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopForeground(true);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.cancel(250);
                stopSelf();
            }
        }, 100);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }
}
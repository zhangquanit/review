package com.review.process.keepalive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;

/**
 * 从 Android2.3 开始调用 setForeground 将后台 Service 设置为前台 Service 时，必须在系统的通知栏发送一条通知，也就是前台 Service 与一条可见的通知时绑定在一起的。
 * 通过实现一个内部 Service，在 LiveService 和其内部 Service 中同时发送具有相同 ID 的 Notification，然后将内部 Service 结束掉。
 * 随着内部 Service 的结束，Notification 将会消失，但系统优先级依然保持为2。
 *
 * @author 张全
 */

public class KeepAliveService extends Service {
    private KeepAliveReceiver receiver;

    @Override
    public void onCreate() {
        System.out.println("------KeepAliveService ，pid="+ Process.myPid());

        //前台Service
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            Notification.Builder builder = new Notification.Builder(this);
//            builder.setSmallIcon(R.mipmap.ic_launcher);
//            startForeground(250, builder.build());
//            startService(new Intent(this, CancelService.class));
//        } else {
//            startForeground(250, new Notification());
//        }

        //注册锁屏广播
        receiver = new KeepAliveReceiver();
        KeepAliveReceiver.registerScreenActionReceiver(this,receiver );

        // jobservice
        MyJobService.start(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (null != receiver) unregisterReceiver(receiver);
        }catch(Exception e){
            e.printStackTrace();
        }
        sendBroadcast(new Intent("com.review.action.restartservice"));
    }
}

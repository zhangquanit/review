package com.review.datastorage.sp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;

/**
 * @author 张全
 */

public class SPProcessService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("SPProcessService pid"+ Process.myPid());
        //获取其他进程中保存的数据
        String username = SPUtil.getString(this, "username");
        System.out.println("SPProcessService username="+username);
        //保存数据
        SPUtil.setString(this,"password","123456");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.review.aidl.server;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.os.Process;

import java.util.List;

/**
 * @author zhangquan
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        boolean mainProcess = isMainProcess(this);
        System.out.println("主进程："+(mainProcess));
        System.out.println("pid="+ Process.myPid());
        if(!mainProcess){
            System.out.println("注册异常处理");
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println("子进程出错了 msg="+e.getMessage());
                }
            });
        }

    }

    /**
     * 包名判断是否为主进程
     *
     * @param context
     * @return
     */
    public static boolean isMainProcess(Context context) {
        return context.getPackageName().equals(getProcessName(context));
    }

    /**
     * 获取进程名称
     *
     * @param context
     * @return
     */
    public static String getProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }
}

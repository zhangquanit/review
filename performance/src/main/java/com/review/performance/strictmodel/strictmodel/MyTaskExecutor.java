package com.review.performance.strictmodel.strictmodel;

import android.os.StrictMode;
import android.os.SystemClock;

/**
 * 耗时检测
 */
public class MyTaskExecutor {
    public static long CAN_BEAR_TIME = 500;

    public void execute(Runnable task) {
        long sTime = SystemClock.uptimeMillis();
        task.run();
        long cTime = SystemClock.uptimeMillis() - sTime;
        if (cTime > CAN_BEAR_TIME) {
            StrictMode.noteSlowCall("slow call cost:" + cTime);
        }
    }

    public static void executeTest(){
        /**
         * StrictMode policy violation; ~duration=2 ms: android.os.strictmode.CustomViolation: slow call cost:1115
         */
        MyTaskExecutor taskExecutor = new MyTaskExecutor();
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

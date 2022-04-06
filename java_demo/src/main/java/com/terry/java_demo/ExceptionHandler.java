package com.terry.java_demo;

/**
 * 1、子线程crash  main线程还可以继续运行
 * 2、通过UncaughtExceptionHandler拦截子线程异常
 *
 * @author zhangquan
 */
public class ExceptionHandler {
    public static void main(String[] args) {
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                System.out.println("thread="+t.getName()+",e="+e.getMessage());
//                e.printStackTrace();
//            }
//        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                    Integer.valueOf("a");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        Integer.valueOf("b");
        System.out.println("---------------------");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1 * 1000);
                System.out.println("i=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

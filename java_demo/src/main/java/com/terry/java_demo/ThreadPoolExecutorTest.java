package com.terry.java_demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        /**
         * 从执行结果可以看出，当线程池中线程的数目大于5时，便将任务放入任务缓存队列里面，
         * 当任务缓存队列满了之后，便创建新的线程。
         * 如果上面程序中，将for循环中改成执行20个任务，就会抛出任务拒绝异常了。
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 10,
                200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5)
//				 new LinkedBlockingDeque<>()
        );

        //创建15个任务来执行
        for (int i = 0; i < 15; i++) {
            System.out.println("i=" + i);
            //创建task
            MyTask myTask = new ThreadPoolExecutorTest().new MyTask(i);
            //执行task
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size());
        }
        executor.shutdown();
    }

    class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
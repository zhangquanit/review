package com.review.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;

/**
 * @author zhangquan
 */
public class MyJobService extends JobService {

    public static void start(Context ctx) {
        JobScheduler mJobScheduler = (JobScheduler) ctx.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1,
                new ComponentName(ctx.getPackageName(),
                        MyJobService.class.getName()));
        builder.setPeriodic(5 * 1000, 3 * 1000);
        builder.setPersisted(true);
        mJobScheduler.schedule(builder.build());
        /**
         * 关于其他的几个设置介绍如下:
         * - setMinimumLatency(long minLatencyMillis): 表示多少毫秒后开始执行任务,这个函数与setPeriodic(long time)方法互斥，这两个方法同一时候调用了就会引起异常, 与setPeriodic(long time)互斥；
         * - setPeriodic(long intervalMillis): 表示多长时间重复执行一次
         * - setOverrideDeadline(long maxExecutionDelayMillis): 设置任务最大的延迟时间,也就是到设置的时间后, 不管其他条件是否满足都会开始任务. 与setMinimumLatency(long time)一样，该方法也会与setPeriodic(long time)互斥。
         * - setPersisted(boolean isPersisted): 表明当设备重新启动后你的任务是否还要继续运行。
         * - setRequiresCharging(boolean requiresCharging): 是否需要当设备在充电时任务才会被运行。
         * - setRequiresDeviceIdle(boolean requiresDeviceIdle): 表示任务只有当用户没有在使用该设备且有一段时间没有使用时才会启动。
         * - setRequiredNetworkType(int networkType): 指明在满足指定的网络条件时才会被运行。默认条件是JobInfo.NETWORK_TYPE_NONE, 即无论是否有网络这个任务都会被运行。另外两个可选类型。一种是JobInfo.NETWORK_TYPE_ANY，它表明须要随意一种网络才使得任务能够运行。还有一种是JobInfo.NETWORK_TYPE_UNMETERED，它表示设备不是蜂窝网络( 比方在WIFI连接时 )时任务才会被运行。剩下的还有两种分别是NETWORK_TYPE_NOT_ROAMING 不是漫游, NETWORK_TYPE_METERED. wifi
         */
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        /**
         * 返回值如果是false表明调用onStartJob后系统任务任务已经处理完成
         * 当返回false的时候, 当系统在收到取消请求的时候, 会认为当前已经没有任务在运行, 就不会调用对应的onStopJob方法了
         *
         * 如果返回的是true, 表示告诉系统我的任务还在执行, 这时候系统就会把任务的结束调用交给用户去做,
         * 也就是我们需要在任务执行完毕的时候手动去调用jobFinished(JobParameters params, boolean needsRescheduled)来通知系统
         *
         * 注意onStartJob本身是在主线程中的, 所以如果要做耗时的操作记得另开子线程处理
         */
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

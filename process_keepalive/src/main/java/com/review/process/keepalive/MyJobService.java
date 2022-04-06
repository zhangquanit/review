package com.review.process.keepalive;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;


/**
 * @author 张全
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    /**
     *JobSheduler是作为进程死后复活的一种手段，native进程方式最大缺点是费电，
     * Native 进程费电的原因是感知主进程是否存活有两种实现方式，在 Native 进程中通过死循环或定时器，轮训判断主进程是否存活，
     * 当主进程不存活时进行拉活。其次5.0 以上系统不支持。 但是JobSheduler可以替代在Android5.0以上native进程方式，
     * 这种方式即使用户强制关闭，也能被拉起来，亲测可行。
     * @param ctx
     */
    public static void start(Context ctx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(ctx.getPackageName(), MyJobService.class.getName()));
                builder.setPeriodic(1000);
                builder.setPersisted(true);
                JobScheduler jobScheduler = (JobScheduler) ctx.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                jobScheduler.schedule(builder.build());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public MyJobService() {
        System.out.println("----------MyJobService");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
//        System.out.println("----------MyJobService，onStartJob,thread=" + Thread.currentThread().getName()+",pid="+ android.os.Process.myPid());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        System.out.println("----------MyJobService，onStopJob,thread=" + Thread.currentThread().getName());
        return false;
    }
}

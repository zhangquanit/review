package com.medlinker.demo.leakcanary;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.MessageQueue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * @author zhangquan
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Activity/Fragment 内存泄露
        detectLeaks();
        LeakCanary.install(this);

        //检测卡顿
        BlockCanary.install(this, new BlockCanaryContext()).start();
    }

    ReferenceQueue<Object> queue = new ReferenceQueue<>();
    Handler backgroundHandler;
    private Set<String> retainedKeys;

    public void detectLeaks() {
        retainedKeys = new CopyOnWriteArraySet<>();

        HandlerThread handlerThread = new HandlerThread("LeakCanary-Heap-Dump");
        handlerThread.start();
        backgroundHandler = new Handler(handlerThread.getLooper());
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                System.out.println("onActivityDestroyed ->name=" + activity.getClass().getSimpleName());
                String key = UUID.randomUUID().toString();
                retainedKeys.add(key);
                final KeyedWeakReference reference =
                        new KeyedWeakReference(activity, key, activity.getClass().getSimpleName(), queue);
                Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                    @Override
                    public boolean queueIdle() {
                        System.out.println("queueIdle");
                        postToBackgroundWithDelay(reference);
                        return false;
                    }
                });
            }
        });
    }

    private void postToBackgroundWithDelay(KeyedWeakReference reference) {
        backgroundHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
                //检测是否已回收
                removeWeaklyReachableReferences();

                if (gone(reference)) {
                    System.out.println("已回收");
                    return;
                }

                System.out.println("触发GC");
                //GC
                Runtime.getRuntime().gc();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new AssertionError();
                }
                System.runFinalization();

                //再次检测是否已回收
                removeWeaklyReachableReferences();

                if (!gone(reference)) {
                    System.out.println("还没有回收,dump内存信息");
                    File downloadsDirectory = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);
                    File storageDirectory = new File(downloadsDirectory, "leakcanary-" + getPackageName());
                    File heapDumpFile = new File(storageDirectory, UUID.randomUUID().toString() + "_pending.hprof");
                    try {
                        Debug.dumpHprofData(heapDumpFile.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 2 * 1000);
    }

    private boolean gone(KeyedWeakReference reference) {
        return !retainedKeys.contains(reference.key);
    }

    private void removeWeaklyReachableReferences() {
        // WeakReferences are enqueued as soon as the object to which they point to becomes weakly
        // reachable. This is before finalization or garbage collection has actually happened.
        KeyedWeakReference ref;
        while ((ref = (KeyedWeakReference) queue.poll()) != null) {
            System.out.println("--已完成回收 name=" + ref.name);
            retainedKeys.remove(ref.key);
        }
    }

    final class KeyedWeakReference extends WeakReference<Object> {
        public final String key;
        public final String name;

        KeyedWeakReference(Object referent, String key, String name,
                           ReferenceQueue<Object> referenceQueue) {
            super(referent, referenceQueue);
            this.key = key;
            this.name = name;
        }
    }
}

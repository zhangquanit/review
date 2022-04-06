package com.review.asyncloader.loader;

import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 监听数据时：在onStartLoading中开启监听，在onRest中注销监听
 * @author 张全
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<Person> {
    private String url;
    private Person person;
    public static final String ACTION = "con.review.action.loader.change";

    public MyAsyncTaskLoader(Context context, String url) {
        super(context);
        this.url = url;

    }

    @Override
    public Person loadInBackground() {
        System.out.println("AsyncTaskLoader  loadInBackground ,thread=" + Thread.currentThread().getName());
        person = getPerson();
        return person;
    }

    @Override
    protected void onStartLoading() {
        System.out.println("AsyncTaskLoader onStartLoading");

        //监听数据变化
        registeReceiver();

        // 如果已经有数据
        if (null != person) {
            deliverResult(person);
            return;
        }
        //如果没有数据  加载数据
        forceLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        System.out.println("AsyncTaskLoader onReset");
        unregistReceiver();
    }

    private Person getPerson() {
        person = new Person();
        person.setMsg("hello world");
        return person;
    }

    private void registeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        getContext().registerReceiver(receiver, intentFilter);
    }

    private void unregistReceiver() {
        getContext().unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("AsyncTaskLoader onReceive");
            onContentChanged(); //重新加载数据
            /**
              AsyncTaskLoader  onReceive  //监听到数据变化 调用onContentChanged 加载数据
              AsyncTaskLoader  loadInBackground ,thread=AsyncTask #1  //加载数据
              AsyncTaskLoader  onLoadFinished  //数据加载完毕 回调给客户端
             */
        }
    };
}

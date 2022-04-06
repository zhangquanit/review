package com.review.asyncloader.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

/**
 * @author 张全
 */

public class AsyncTaskActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<10;i++){
            new AsyncTask<Void,Void,Void>(){
                @Override
                protected Void doInBackground(Void... params) {
                    System.out.println(Thread.currentThread().getName()+" 进入");
                    try {
                        Thread.sleep(3*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" 执行完毕");
                    return null;
                }
            }.execute();
        }
    }
}

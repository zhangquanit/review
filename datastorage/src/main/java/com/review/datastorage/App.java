package com.review.datastorage;

import android.app.Application;
import android.content.Context;

/**
 * @author zhangquan
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("App onCreate");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        System.out.println("App attachBaseContext");
    }
}

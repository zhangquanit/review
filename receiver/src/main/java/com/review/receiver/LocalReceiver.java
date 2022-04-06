package com.review.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author 张全
 */

public class LocalReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "LocalReceiver： thread="+Thread.currentThread().getName());

        //在Receiver中开启Activity，需要添加Intent.FLAG_ACTIVITY_NEW_TASK
//        Intent intentAct = new Intent(context, TwoActivity.class);
//        intentAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intentAct);
    }
}

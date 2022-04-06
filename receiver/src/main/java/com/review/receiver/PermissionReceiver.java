package com.review.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author zhangquan
 */
public class PermissionReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"PermissionReceiver onReceive  action="+intent.getAction());
    }
}

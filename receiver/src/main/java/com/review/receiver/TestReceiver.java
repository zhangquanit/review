package com.review.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author zhangquan
 */
public class TestReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"TestReceiver  onReceive data="+intent.getStringExtra("data"));
    }
}

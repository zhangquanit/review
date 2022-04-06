package com.review.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
  
    private static final String TAG = "MyReceiver";    
      
    @Override  
    public void onReceive(Context context, Intent intent) {
        //广播消息
        String msg = intent.getStringExtra("msg");
        Log.i(TAG, "MyReceiver："+msg);

        //传给下一个Receiver的消息内容
        Bundle bundle = new Bundle();
        bundle.putString("msg","MyReceiver_bundleMsg");
        setResult(1,"MyReceiver",bundle);
    }
}  
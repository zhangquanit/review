package com.review.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondRecever extends BroadcastReceiver {
  
    private static final String TAG = "MyReceiver";    
    @Override  
    public void onReceive(Context context, Intent intent) {
        //广播消息 不能被修改
        String msg = intent.getStringExtra("msg");    
        Log.i(TAG, "SecondRecever："+msg);


        //来自上一个Receiver的setResult
        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(true);
        Log.i(TAG, "SecondRecever：[resultCode="+resultCode+",resultData="+resultData+",resultExtras="+resultExtras);
    }  
}  
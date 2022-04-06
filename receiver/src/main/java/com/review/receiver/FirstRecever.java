package com.review.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class FirstRecever extends BroadcastReceiver {
  
    private static final String TAG = "MyReceiver";    
    @Override  
    public void onReceive(Context context, Intent intent) {
        //广播消息 不能被修改
        String msg = intent.getStringExtra("msg");    
        Log.i(TAG, "FirstRecever："+msg);

        //来自上一个Receiver的setResult
        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(true);
        Log.i(TAG, "FirstRecever：[resultCode="+resultCode+",resultData="+resultData+",resultExtras="+resultExtras);

        //传给下一个
        Bundle bundle = new Bundle();
        bundle.putString("msg","FirstRecever_bundleMsg");
        setResult(2,"FirstRecever",bundle);

        //停止后续的广播
//        abortBroadcast();
    }  
}  
package com.review.receiver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends Activity implements View.OnClickListener {
    LocalBroadcastManager localBroadcastManager;
    LocalReceiver localReceiver;
    static String ACTION = "com.intent.action.test";
    private static final String TAG = "MyReceiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        findViewById(R.id.btn_sendBrocast).setOnClickListener(this);
        findViewById(R.id.btn_sendBrocast2).setOnClickListener(this);
        findViewById(R.id.btn_sendBrocast3).setOnClickListener(this);
        findViewById(R.id.btn_sendLocalBrocast).setOnClickListener(this);
        findViewById(R.id.btn_permission).setOnClickListener(this);
        findViewById(R.id.btn_schedulBrocast).setOnClickListener(this);


        //注册本地广播
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);


        int taskId = getTaskId();
        System.out.println("MainActivity: taskId=" + taskId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendBrocast:  //发送隐式广播
                //8.0限制隐式广播
                Intent intent = new Intent(ACTION);
                intent.putExtra("msg", "发送隐式广播");
                sendOrderedBroadcast(intent, null);
                break;
            case R.id.btn_permission:
                //8.0限制隐私广播，发送权限广播不受影响
                intent = new Intent("com.intent.action.permission");
                sendBroadcast(intent, "com.review.receiver.permission");
                break;
            case R.id.btn_sendBrocast2: //发送显示广播
                //必须在manifest.xml中声明 <receiver android:name="" />
                intent = new Intent(this, TestReceiver.class);
                intent.putExtra("data", "发送显示广播");
                sendOrderedBroadcast(intent, null);
                break;
            case R.id.btn_sendBrocast3: //动态注册广播
                //隐式调用  BrodcastReceiver无需在manifest.xml中注册
                registerReceiver(new DynamicReceiver(), new IntentFilter("com.test.receiver.action"));
                sendBroadcast(new Intent("com.test.receiver.action"));


                //显示调用 BrodcastReceiver必须在manifest.xml中注册
                registerReceiver(new TestReceiver(), new IntentFilter());
                intent=new Intent(this, TestReceiver.class);
                intent.putExtra("data","代码注册的显示广播");
                sendBroadcast(intent);

                break;
            case R.id.btn_sendLocalBrocast:
                Intent intent2 = new Intent(ACTION);
                localBroadcastManager.sendBroadcast(intent2);
                break;

            case R.id.btn_schedulBrocast:
                //AlarmManager+PendingIntent 发送定时广播
                intent = new Intent(this, TestReceiver.class);
                intent.putExtra("data","定时广播");
                PendingIntent sender = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
                // 进行闹铃注册
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 100, sender);
                break;
        }
    }

    public static class DynamicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "DynamicReceiver onReceive....代码注册的隐式广播，无需在manifest中注册");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}


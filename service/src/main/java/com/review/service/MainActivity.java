package com.review.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_startService).setOnClickListener(this);
        findViewById(R.id.btn_restartService).setOnClickListener(this);
        findViewById(R.id.btn_stopService).setOnClickListener(this);
        findViewById(R.id.btn_bindService).setOnClickListener(this);
        findViewById(R.id.btn_unbindService).setOnClickListener(this);
        findViewById(R.id.btn_forgroundService).setOnClickListener(this);
        findViewById(R.id.btn_processService).setOnClickListener(this);
        findViewById(R.id.btn_intentService).setOnClickListener(this);
        findViewById(R.id.btn_aidl).setOnClickListener(this);
        findViewById(R.id.btn_intentfilter).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startService:
                System.out.println("-------------------------------------------------");
                Intent intent = new Intent(this, BackgroundService.class);
                intent.putExtra("msg", "hello world=" + System.currentTimeMillis());
                startService(intent);

                break;
            case R.id.btn_restartService:
                Integer.valueOf("a"); //制造异常，测试Service是否重启,Service.START_STICKY
                break;
            case R.id.btn_stopService:
                intent = new Intent(this, BackgroundService.class);
                stopService(intent);
                break;
            case R.id.btn_bindService:
                intent = new Intent(this, BinderService.class); //在同一个进程内
//                intent = new Intent(this, ProcessService.class); //本应用跨进程  不支持bind，需要写aidl
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbindService:
                unbindService(serviceConnection);
                break;
            case R.id.btn_aidl: //跨应用AIDL访问

                break;
            case R.id.btn_forgroundService:
                intent = new Intent(this, ForgroundService.class);
                startService(intent);
                break;
            case R.id.btn_processService:
                Log.d("MyService", "MainActivity,pid=" + Process.myPid());
                intent = new Intent(this, ProcessService.class);
                startService(intent);
                break;
            case R.id.btn_intentService:
                intent = new Intent(this, MyIntentService.class);
                intent.putExtra("url", "http://www.baidu.com");
                startService(intent);
                break;
            case R.id.btn_intentfilter:
                System.out.println("主进程:" + Process.myPid());
                try {
                    //通过intent-filter打开service
                    intent = new Intent("com.review.service.intent.action");
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startService(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                break;

        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("代码注册的广播");
        }
    };

    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MyService", "BinderService onServiceConnected ,name=" + name + ",service=" + service);
            MyBinder binder = (MyBinder) service;
            binder.showToast();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MyService", "BinderService onServiceDisconnected");
        }
    };


}

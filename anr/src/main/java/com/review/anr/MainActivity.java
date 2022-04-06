package com.review.anr;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{
    private static final String ACTION="com.review.anr.action";
    MyReceiver myReceiver ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_onClick).setOnClickListener(this);
        findViewById(R.id.btn_receive).setOnClickListener(this);

        myReceiver=new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(myReceiver,intentFilter);

        //主线程中执行耗时操作，特别是生命周期函数中,此时按返回键退出，由于返回事件不能得到及时响应，会出现ANR
        System.out.println("--------------onCreate");
        try {
            Thread.sleep(15*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //主线程中执行耗时操作，特别是生命周期函数中
        System.out.println("--------------onResume");
        try {
            Thread.sleep(15*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_onClick:
                //输入事件不能及时响应
                System.out.println("onClick-------start");
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("onClick-------end");
                break;
            case R.id.btn_receive:
                sendBroadcast(new Intent(ACTION));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("onReceive-------start");
            //输入事件不能及时响应
            try {
                Thread.sleep(20*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("onReceive-------end");
        }
    }
}

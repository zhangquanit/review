package com.review.process.messenger.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MSG_WHAT = 1;
    private TextView tv_result;
    private boolean isConn;
    private Messenger mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView) findViewById(R.id.tv_result);
        findViewById(R.id.btn_sendMsg).setOnClickListener(this);

        //绑定服务
        bindServiceInvoked();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendMsg:
                Message msgFromClient = Message.obtain(null, MSG_WHAT);
                msgFromClient.replyTo = mMessenger; //实现序列化
                if (isConn) {
                    //往服务端发送消息
                    try {
                        mService.send(msgFromClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }


    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgFromServer) {
            System.out.println("client  handleMessage,msgFromServer=" + msgFromServer);
            switch (msgFromServer.what) {
                case MSG_WHAT:
                    tv_result.setText(msgFromServer.obj.toString());
                    break;
            }
        }
    });
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("onServiceConnected...serviceBinder=" + service);
            mService = new Messenger(service);
            isConn = true;
            tv_result.setText("connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isConn = false;
            tv_result.setText("disconnected!");
        }
    };

    private void bindServiceInvoked() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.review.process.messenger", "com.review.process.messenger.MessengerService"));
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
        Log.e(TAG, "bindService invoked !");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
    }
}

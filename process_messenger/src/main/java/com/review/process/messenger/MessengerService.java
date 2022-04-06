package com.review.process.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

/**
 * @author 张全
 */

public class MessengerService extends Service {
    private static final int MSG_WHAT=1;
    Messenger messenger= new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msgfromClient) {
            System.out.println("MessengerService handleMessage");
            System.out.println("MessengerService msgfromClient="+msgfromClient+",replyTo="+msgfromClient.replyTo);

            switch (msgfromClient.what){
                case MSG_WHAT:
                    Messenger clentMessenger = msgfromClient.replyTo;
                    Message msgToClient = Message.obtain(null,MSG_WHAT); // 返回给客户端的消息
                    try{
                        msgToClient.obj="来自服务端的消息";
                        clentMessenger.send(msgToClient);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    });
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        IBinder binder = messenger.getBinder();
        System.out.println("MessengerService onBind() binder="+binder);
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

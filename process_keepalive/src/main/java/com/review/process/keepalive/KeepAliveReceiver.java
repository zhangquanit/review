package com.review.process.keepalive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * @author 张全
 */

public class KeepAliveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        System.out.println("action="+action);
        if(action.equals(Intent.ACTION_SCREEN_OFF)){
            KeepAliveActivity.start(context,true);
        }else if(action.equals(Intent.ACTION_USER_PRESENT)){
            KeepAliveActivity.start(context,false);
        }

        context.startService(new Intent(context,KeepAliveService.class));

    }

    public static void registerScreenActionReceiver(Context ctx,KeepAliveReceiver receiver){
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        ctx.registerReceiver(receiver, filter);
    }
}

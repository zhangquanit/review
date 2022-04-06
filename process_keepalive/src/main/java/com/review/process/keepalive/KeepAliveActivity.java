package com.review.process.keepalive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author 张全
 */

public class KeepAliveActivity extends Activity {
    private static final String PARAM="PARAM";
    private static boolean START_FLAG=true;
    public static void start(Context ctx,boolean start){
        Intent intent = new Intent(ctx, KeepAliveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PARAM,start);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean startFlag = getIntent().getBooleanExtra(PARAM, true);
        System.out.println("KeepAliveActivity........startFlag="+startFlag);
        if(startFlag){
            Window window = getWindow();
            window.setGravity(Gravity.LEFT|Gravity.TOP);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.x=0;
            layoutParams.y=0;
            layoutParams.width=1;
            layoutParams.height=1;
            window.setAttributes(layoutParams);
        }else{
            finish();
        }

    }
}

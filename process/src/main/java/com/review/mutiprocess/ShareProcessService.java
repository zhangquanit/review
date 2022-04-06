package com.review.mutiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author 张全
 */

public class ShareProcessService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

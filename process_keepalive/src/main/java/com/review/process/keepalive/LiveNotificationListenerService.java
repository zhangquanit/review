package com.review.process.keepalive;

import android.annotation.TargetApi;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/**
 * @author 张全
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class LiveNotificationListenerService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }
}

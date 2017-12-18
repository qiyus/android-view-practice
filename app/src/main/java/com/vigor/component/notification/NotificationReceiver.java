package com.vigor.component.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.vigor.component.R;
import com.vigor.component.notification.command.SendBroadcastNotification;

/**
 * Created by vigor on 2017/7/22.
 * 网络连接状态广播接受
 */

public class NotificationReceiver extends BroadcastReceiver {
    public final static int NOTIFICATION_ID = 9;

    @Override
    public void onReceive(Context context, Intent intent) {
        showNormalNotification(context, intent);
    }

    private void showNormalNotification(Context context, Intent intent) {
        Intent activityIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, activityIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(context.getString(R.string.notification_title_broadcast))
                .setContentText(intent.getStringExtra(SendBroadcastNotification.MESSAGE))
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}

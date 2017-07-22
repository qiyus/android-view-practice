package com.vigorx.viewdemo.notification.command;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.vigorx.viewdemo.R;
import com.vigorx.viewdemo.notification.ApplicationBackActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by vigor on 2017/7/22.
 * 设置常规 Activity PendingIntent
 */

public class BackApplicationNotification implements Command {

    private final static int NOTIFICATION_ID = 6;
    private Bitmap mIcon;
    private Context mContext;
    private NotificationManager mNotificationManager;
    final NotificationCompat.Builder mBuilder;

    public BackApplicationNotification(Context context) {
        this.mContext = context;
        mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tom);
        mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(mContext);
    }

    @Override
    public void execute() {
        showBackApplication();
    }

    /**
     * 返回应用主界面
     */
    private void showBackApplication() {
        Intent intent = new Intent(mContext, ApplicationBackActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addParentStack(ApplicationBackActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(NOTIFICATION_ID,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentTitle(mContext.getString(R.string.notification_title))
                .setContentText(mContext.getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setPriority(android.app.Notification.PRIORITY_DEFAULT)
                .setDefaults(android.app.Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent);

        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

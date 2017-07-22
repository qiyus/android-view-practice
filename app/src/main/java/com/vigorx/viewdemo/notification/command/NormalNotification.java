package com.vigorx.viewdemo.notification.command;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.vigorx.viewdemo.R;
import com.vigorx.viewdemo.mainview.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by vigor on 2017/7/22.
 * 普通视图通知
 */

public class NormalNotification implements Command {
    private final static int NOTIFICATION_ID = 1;
    private Bitmap mIcon;
    private Context mContext;
    private NotificationManager mNotificationManager;
    final NotificationCompat.Builder mBuilder;

    public NormalNotification(Context context) {
        this.mContext = context;
        mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tom);
        mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(mContext);
    }

    @Override
    public void execute() {
        showNormalView();
    }

    /**
     * 普通视图notification
     */
    private void showNormalView() {
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, NOTIFICATION_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentTitle(mContext.getString(R.string.notification_title))
                .setContentText(mContext.getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setPriority(android.app.Notification.PRIORITY_DEFAULT)
                .setDefaults(android.app.Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent);

        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

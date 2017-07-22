package com.vigorx.viewdemo.notification.command;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.vigorx.viewdemo.R;
import com.vigorx.viewdemo.mainview.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by vigor on 2017/7/22.
 * 自定义通知布局
 */

public class CustomViewNotification implements Command {
    private final static int NOTIFICATION_ID = 5;
    private Bitmap mIcon;
    private Context mContext;
    private NotificationManager mNotificationManager;
    final NotificationCompat.Builder mBuilder;

    public CustomViewNotification(Context context) {
        this.mContext = context;
        mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tom);
        mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(mContext);
    }

    @Override
    public void execute() {
        showCustomView();
    }

    /**
     * 自定义notification
     */
    private void showCustomView() {
        RemoteViews remoteView =
                new RemoteViews(mContext.getPackageName(), R.layout.remoteview_notification);
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.paly_pause_music, pendingIntent);
        mBuilder.setContentTitle(mContext.getString(R.string.notification_title))
                .setContentText(mContext.getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setPriority(android.app.Notification.PRIORITY_DEFAULT)
                .setDefaults(android.app.Notification.DEFAULT_ALL)
                .setContent(remoteView);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

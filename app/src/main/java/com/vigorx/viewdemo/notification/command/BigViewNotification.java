package com.vigorx.viewdemo.notification.command;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.vigorx.viewdemo.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by vigor on 2017/7/22.
 * 大视图通知
 */
public class BigViewNotification implements Command {
    private final static int NOTIFICATION_ID = 2;
    private Bitmap mIcon;
    private Context mContext;
    private NotificationManager mNotificationManager;
    final NotificationCompat.Builder mBuilder;

    public BigViewNotification(Context context) {
        this.mContext = context;
        mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tom);
        mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(mContext);
    }

    @Override
    public void execute() {
        showBigView();
    }

    /**
     * 大布局Text类型notification
     */
    private void showBigView() {
        NotificationCompat.BigTextStyle textStyle =
                new android.support.v4.app.NotificationCompat.BigTextStyle();
        textStyle.setBigContentTitle(mContext.getString(R.string.notification_title))
                .setSummaryText(mContext.getString(R.string.notification_detail))
                .bigText(mContext.getString(R.string.notification_large_detail));
        mBuilder.setContentTitle(mContext.getString(R.string.notification_title))
                .setContentText(mContext.getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setStyle(textStyle)
                .setPriority(android.app.Notification.PRIORITY_DEFAULT)
                .setDefaults(android.app.Notification.DEFAULT_SOUND);

        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

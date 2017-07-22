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
 * 显示大图片
 */

public class BigPictureNotification implements Command {
    private final static int NOTIFICATION_ID = 3;
    private Bitmap mIcon;
    private Context mContext;
    private NotificationManager mNotificationManager;
    final NotificationCompat.Builder mBuilder;

    public BigPictureNotification(Context context) {
        this.mContext = context;
        mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tom);
        mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(mContext);
    }

    @Override
    public void execute() {
        showBigPicture();
    }

    /**
     * 大布局Picture类型notification
     */
    private void showBigPicture() {
        NotificationCompat.BigPictureStyle pictureStyle =
                new android.support.v4.app.NotificationCompat.BigPictureStyle();
        pictureStyle.setBigContentTitle(mContext.getString(R.string.notification_title))
                .bigPicture(mIcon)
                .setSummaryText(mContext.getString(R.string.notification_detail));

        mBuilder.setContentTitle(mContext.getString(R.string.notification_title))
                .setContentText(mContext.getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setStyle(pictureStyle)
                .setPriority(android.app.Notification.PRIORITY_DEFAULT)
                .setDefaults(android.app.Notification.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

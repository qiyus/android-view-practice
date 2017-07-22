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
 * 将扩展布局应用于通知
 */

public class InboxNotification implements Command {

    private final static int NOTIFICATION_ID = 4;
    private Bitmap mIcon;
    private Context mContext;
    private NotificationManager mNotificationManager;
    final NotificationCompat.Builder mBuilder;

    public InboxNotification(Context context) {
        this.mContext = context;
        mIcon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tom);
        mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(mContext);
    }

    @Override
    public void execute() {
        showBigInbox();
    }

    /**
     * 大布局Inbox类型notification
     */
    private void showBigInbox() {
        NotificationCompat.InboxStyle inboxStyle =
                new android.support.v4.app.NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(mContext.getString(R.string.notification_title))
                .setSummaryText(mContext.getString(R.string.notification_detail));
        for (int i = 0; i < 3; i++) {
            inboxStyle.addLine(mContext.getString(R.string.notification_inbox) + i);
        }

        mBuilder.setContentTitle(mContext.getString(R.string.notification_title))
                .setContentText(mContext.getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setStyle(inboxStyle)
                .setPriority(android.app.Notification.PRIORITY_DEFAULT)
                .setDefaults(android.app.Notification.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

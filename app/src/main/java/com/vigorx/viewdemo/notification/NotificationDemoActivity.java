package com.vigorx.viewdemo.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vigorx.viewdemo.R;
import com.vigorx.viewdemo.mainview.MainActivity;

public class NotificationDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int NOTIFICATION_ID_1 = 1;
    private static final int NOTIFICATION_ID_2 = 2;
    private static final int NOTIFICATION_ID_3 = 3;
    private static final int NOTIFICATION_ID_4 = 4;
    private static final int NOTIFICATION_ID_5 = 5;
    private static final int NOTIFICATION_ID_6 = 6;
    private static final int NOTIFICATION_ID_7 = 7;
    private static final int NOTIFICATION_ID_8 = 8;
    private static final int[] BUTTONS = new int[]{R.id.notification_button1,
            R.id.notification_button2, R.id.notification_button3, R.id.notification_button4,
            R.id.notification_button5, R.id.notification_button6, R.id.notification_button7,
            R.id.notification_button8};
    private Bitmap mIcon;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mIcon = BitmapFactory.decodeResource(getResources(), R.drawable.tom);

        for (int buttonId : BUTTONS) {
            findViewById(buttonId).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification_button1:
                showNormalView();
                break;
            case R.id.notification_button2:
                showBigView();
                break;
            case R.id.notification_button3:
                showBigPicture();
                break;
            case R.id.notification_button4:
                showBigInbox();
                break;
            case R.id.notification_button5:
                showCustomView();
                break;
            case R.id.notification_button6:
                showBackApplication();
                break;
            case R.id.notification_button7:
                showBackDesktop();
                break;
            case R.id.notification_button8:
                showProgressBar();
                break;
            default:
        }
    }

    private void showProgressBar() {
        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationDemoActivity.this);
        builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setOngoing(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    builder.setProgress(100, i, false);
                    mNotificationManager.notify(NOTIFICATION_ID_8, builder.build());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                builder.setContentText(getString(R.string.notification_complete))
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                mNotificationManager.notify(NOTIFICATION_ID_8, builder.build());
            }
        }).start();

    }

    /**
     * 返回桌面，感觉没啥用,可以了解一下Intent Flag 和 LaunchMode。
     */
    private void showBackDesktop() {
        Intent intent = new Intent(NotificationDemoActivity.this, NotificationSpecialActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                NotificationDemoActivity.this, NOTIFICATION_ID_7, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationDemoActivity.this);
        builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent);

        mNotificationManager.notify(NOTIFICATION_ID_7, builder.build());
    }

    /**
     * 返回应用主界面
     */
    private void showBackApplication() {
        Intent intent = new Intent(NotificationDemoActivity.this, Notification2Activity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(NotificationDemoActivity.this);
        // 设置父堆栈，manifest定义。
        stackBuilder.addParentStack(Notification2Activity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(NOTIFICATION_ID_6, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationDemoActivity.this);
        builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent);

        mNotificationManager.notify(NOTIFICATION_ID_6, builder.build());
    }

    /**
     * 自定义notification
     */
    private void showCustomView() {
        RemoteViews remoteView =
                new RemoteViews(getPackageName(), R.layout.remoteview_notification);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, NOTIFICATION_ID_5, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteView.setOnClickPendingIntent(R.id.paly_pause_music, pendingIntent);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationDemoActivity.this);
        builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContent(remoteView);
        mNotificationManager.notify(NOTIFICATION_ID_5, builder.build());
    }

    /**
     * 大布局Inbox类型notification
     */
    private void showBigInbox() {
        NotificationCompat.InboxStyle inboxStyle =
                new android.support.v4.app.NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(getString(R.string.notification_title))
                .setSummaryText(getString(R.string.notification_detail));
        for (int i = 0; i < 3; i++) {
            inboxStyle.addLine(getString(R.string.notification_inbox) + i);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationDemoActivity.this);
        builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setStyle(inboxStyle)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID_4, builder.build());
    }

    /**
     * 大布局Picture类型notification
     */
    private void showBigPicture() {
        NotificationCompat.BigPictureStyle pictureStyle =
                new android.support.v4.app.NotificationCompat.BigPictureStyle();
        pictureStyle.setBigContentTitle(getString(R.string.notification_title))
                .bigPicture(mIcon)
                .setSummaryText(getString(R.string.notification_detail));
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationDemoActivity.this);
        builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setStyle(pictureStyle)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID_3, builder.build());

    }

    /**
     * 大布局Text类型notification
     */
    private void showBigView() {
        NotificationCompat.BigTextStyle textStyle =
                new android.support.v4.app.NotificationCompat.BigTextStyle();
        textStyle.setBigContentTitle(getString(R.string.notification_title))
                .setSummaryText(getString(R.string.notification_detail))
                .bigText(getString(R.string.notification_large_detail));
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationDemoActivity.this);
        builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setStyle(textStyle)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_SOUND);

        mNotificationManager.notify(NOTIFICATION_ID_2, builder.build());
    }

    /**
     * 普通notification
     */
    private void showNormalView() {
        Intent intent = new Intent(NotificationDemoActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                NotificationDemoActivity.this, NOTIFICATION_ID_1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationDemoActivity.this);
        builder.setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_detail))
                .setLargeIcon(mIcon)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent);

        mNotificationManager.notify(NOTIFICATION_ID_1, builder.build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNotificationManager.cancelAll();
    }
}

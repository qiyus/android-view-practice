package com.vigor.component.notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vigor.component.R;
import com.vigor.component.notification.command.BackApplicationNotification;
import com.vigor.component.notification.command.BackDesktopNotification;
import com.vigor.component.notification.command.BigPictureNotification;
import com.vigor.component.notification.command.BigViewNotification;
import com.vigor.component.notification.command.Command;
import com.vigor.component.notification.command.CustomViewNotification;
import com.vigor.component.notification.command.InboxNotification;
import com.vigor.component.notification.command.NormalNotification;
import com.vigor.component.notification.command.ProgressNotification;
import com.vigor.component.notification.command.SendBroadcastNotification;

import java.util.HashMap;
import java.util.Map;

public class NotificationDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int[] BUTTONS = new int[]{R.id.normal_button,
            R.id.big_view_button, R.id.big_picture_button, R.id.big_box_button,
            R.id.custom_view_button, R.id.application_button, R.id.desktop_button,
            R.id.progress_button, R.id.send_broadcast_button};

    private Map<Integer, Command> mCommands = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);
        for (int id : BUTTONS) {
            findViewById(id).setOnClickListener(this);
        }

        mCommands.put(R.id.normal_button, new NormalNotification(this));
        mCommands.put(R.id.big_view_button, new BigViewNotification(this));
        mCommands.put(R.id.big_picture_button, new BigPictureNotification(this));
        mCommands.put(R.id.big_box_button, new InboxNotification(this));
        mCommands.put(R.id.custom_view_button, new CustomViewNotification(this));
        mCommands.put(R.id.application_button, new BackApplicationNotification(this));
        mCommands.put(R.id.desktop_button, new BackDesktopNotification(this));
        mCommands.put(R.id.progress_button, new ProgressNotification(this));
        mCommands.put(R.id.send_broadcast_button, new SendBroadcastNotification(this));
    }

    @Override
    public void onClick(View v) {
        mCommands.get(v.getId()).execute();
    }
}

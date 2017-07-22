package com.vigorx.viewdemo.notification.command;

import android.content.Context;
import android.content.Intent;

import com.vigorx.viewdemo.R;

/**
 * Created by vigor on 2017/7/22.
 * 发送广播
 */

public class SendBroadcastNotification implements Command {

    public final static String NOTIFICATION = "view.demo.notification.SendBroadcast";
    public final static String MESSAGE = "SendBroadcastMessage";

    private Context mContext;

    public SendBroadcastNotification(Context context) {
        this.mContext = context;
    }

    @Override
    public void execute() {
        Intent intent = new Intent();
        intent.putExtra(MESSAGE, mContext.getString(R.string.broadcast_message));
        intent.setAction(NOTIFICATION);
        mContext.sendBroadcast(intent);
    }
}

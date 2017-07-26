package com.vigorx.viewdemo.action;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.vigorx.viewdemo.R;

public class ActionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mActionWebButton;
    private Button mActionFileButton;
    private Button mActionShareButton;
    private Button mActionMailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mActionWebButton = (Button) findViewById(R.id.action_view_url);
        mActionWebButton.setOnClickListener(this);
        mActionFileButton = (Button) findViewById(R.id.action_view_file);
        mActionWebButton.setOnClickListener(this);
        mActionShareButton = (Button) findViewById(R.id.action_send_share);
        mActionShareButton.setOnClickListener(this);
        mActionMailButton = (Button) findViewById(R.id.action_send_mail);
        mActionMailButton.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_view_url:
                viewUrl();
                break;
            case R.id.action_view_file:
                break;
            case R.id.action_send_share:
                break;
            case R.id.action_send_mail:
                sendMail();
                break;
            default:
                break;
        }
    }

    /**
     * 发送Mail
     */
    private void sendMail() {
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.setType("text/plain");
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
//        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));

        try {
            startActivity(emailIntent);
        }
        catch (ActivityNotFoundException exception) {
            showMessage();
        }
    }

    /**
     * 打开指定的URL
     */
    private void viewUrl() {
        Uri uri = Uri.parse(getString(R.string.baidu));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            showMessage();
        }
    }

    /**
     * 显示提示消息
     */
    private void showMessage() {
        // TODO 利用占位符格式化字符串
        Snackbar.make(mActionWebButton, "No application can open this url", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}

package com.vigorx.viewdemo.lifecycle;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.vigorx.viewdemo.R;

/**
 * Created by songlei on 16/10/28.
 */

public class LifeCycleActivity  extends AppCompatActivity {
    private TextView mLeftTop;
    private TextView mRightBottom;
    private ToggleButton mToggleButton;
    private Button mButton;
    private LinearLayout mLayout;
    private final static String TAG = "com.vigorx.lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_lifecycle);
        mLeftTop = (TextView) findViewById(R.id.textView);
        mRightBottom = (TextView) findViewById(R.id.textView2);
        mLayout = (LinearLayout) findViewById(R.id.linear);

        // 取得坐标
        mToggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rect rect = new Rect();
                if (mToggleButton.isChecked()){
                    mLayout.getLocalVisibleRect(rect);
                }
                else {
                    mLayout.getGlobalVisibleRect(rect);
                }
                mLeftTop.setText(rect.left + ", " + rect.top);
                mRightBottom.setText(rect.right + ", " + rect.bottom);
            }
        });

        // 退出
        mButton = (Button) findViewById(R.id.buttonExit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}

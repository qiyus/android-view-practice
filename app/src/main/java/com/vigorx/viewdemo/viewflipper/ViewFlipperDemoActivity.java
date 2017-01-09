package com.vigorx.viewdemo.viewflipper;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.squareup.leakcanary.RefWatcher;
import com.vigorx.viewdemo.R;
import com.vigorx.viewdemo.ViewDemoApplication;

public class ViewFlipperDemoActivity extends Activity {

    private static MemoryLeak mMemoryLeak;
    private Singleton mSingleton;

    private GestureDetector mGestureDetector;
    private ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // memory leak test
        mMemoryLeak = new MemoryLeak();
        mSingleton = Singleton.getInstance(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper_demo);

        mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
        View view1 = getLayoutInflater().inflate(R.layout.view_flipper_one, null);
        View view2 = getLayoutInflater().inflate(R.layout.view_flipper_two, null);
        View view3 = getLayoutInflater().inflate(R.layout.view_flipper_three, null);

        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        mViewFlipper.addView(view1);
        mViewFlipper.addView(view2);
        mViewFlipper.addView(view3);

        mViewFlipper.setAutoStart(false);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });

        Button button1 = (Button) findViewById(R.id.view_flipper_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button2 = (Button) findViewById(R.id.view_flipper_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button3 = (Button) findViewById(R.id.view_flipper_button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncTask();
                finish();
            }
        });
    }

    void startAsyncTask() {
        // This async task is an anonymous class and therefore has a hidden reference to the outer
        // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
        // the activity instance will leak.
        new AsyncTask<Void, Void, Void>() {
            @Override protected Void doInBackground(Void... params) {
                // Do some slow work in background
                SystemClock.sleep(20000);
                return null;
            }
        }.execute();
    }

    private class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // 向左滑动
            if (e1.getX() - e2.getX() > 0 & mViewFlipper.getDisplayedChild() != 2) {
                mViewFlipper.showNext();
            }
            // 向右滑动
            else if (e2.getX() - e1.getX() > 0 & mViewFlipper.getDisplayedChild() != 0) {
                mViewFlipper.showPrevious();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ViewDemoApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }

    public class MemoryLeak {

    }
}

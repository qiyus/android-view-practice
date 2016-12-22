package com.vigorx.viewdemo.viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.vigorx.viewdemo.R;

public class ViewFlipperDemoActivity extends AppCompatActivity {

    private GestureDetector mGestureDetector;
    private ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                finish();
            }
        });
    }

    private class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // 向右滑动
            if (e1.getX() - e2.getX() > 0 & mViewFlipper.getDisplayedChild() != 2) {
                mViewFlipper.showNext();
            }
            // 向左滑动
            else if (e2.getX() - e1.getX() > 0 & mViewFlipper.getDisplayedChild() != 0) {
                mViewFlipper.showPrevious();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}

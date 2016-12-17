package com.vigorx.viewdemo.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vigorx.viewdemo.R;

public class ProgressDemoActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private AppCompatRatingBar mRatingBar;
    private AppCompatSeekBar mSeekBar;
    private TextView mRating;
    private TextView mSeek;

    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mProgressBar = (ProgressBar) findViewById(R.id.progress1);
        mRating = (TextView) findViewById(R.id.progress_rating);
        mRatingBar = (AppCompatRatingBar) findViewById(R.id.progress3);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRating.setText(String.valueOf(rating));
            }
        });
        mSeek = (TextView) findViewById(R.id.progress_seek);
        mSeekBar = (AppCompatSeekBar) findViewById(R.id.progress7);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSeek.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doWork();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgressStatus);
                            mProgressBar.setSecondaryProgress(mProgressStatus++ > 100 ? 100 : mProgressStatus);
                        }
                    });
                }
            }
        }).start();
    }

    private int doWork() {
        try {
            Thread.sleep(500);
            mProgressStatus++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mProgressStatus;
    }
}

package com.vigor.component.progressbar;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.vigor.component.R;

public class ProgressDemoActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private IndicatorProgressBar mIndicatorProgressBar;
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

        mIndicatorProgressBar = (IndicatorProgressBar) this.findViewById(R.id.indicator_progress_bar);
        mIndicatorProgressBar.setMax(100);

        Drawable indicator = getResources().getDrawable(
                R.drawable.progress_indicator);
        Rect bounds = new Rect(0, 0, indicator.getIntrinsicWidth() + 5,
                indicator.getIntrinsicHeight());
        indicator.setBounds(bounds);

        mIndicatorProgressBar.setProgressIndicator(indicator);
        mIndicatorProgressBar.setProgress(30);
        mIndicatorProgressBar.setVisibility(View.VISIBLE);

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
//                            mIndicatorProgressBar.setSecondaryProgress(mProgressStatus++ > 100 ? 100 : mProgressStatus);
//                            mIndicatorProgressBar.setProgress(mProgressStatus);
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

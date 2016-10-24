package com.vigorx.viewdemo.recyclerview;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by songlei on 16/10/24.
 * 利用RecyclerView提供的addOnItemTouchListener（）实现点击处理。
 */

public abstract class OnItemTouchListener extends RecyclerView.SimpleOnItemTouchListener {
    private GestureDetectorCompat mGestureDetectorCompat;
    private RecyclerView mRecyclerView;

    public OnItemTouchListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(),
                new OnItemGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return super.onInterceptTouchEvent(rv, e);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        super.onTouchEvent(rv, e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.onRequestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    private class OnItemGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View item = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (item != null) {
                RecyclerView.ViewHolder vh = mRecyclerView.getChildViewHolder(item);
                onItemClick(vh);
            }
            return super.onSingleTapUp(e);
        }
    }
}

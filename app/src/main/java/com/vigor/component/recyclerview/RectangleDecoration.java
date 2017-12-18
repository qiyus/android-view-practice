package com.vigor.component.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vigor.component.R;

/**
 * Created by Vigor on 2017/12/14.
 * 描画RecycleView的矩形框
 */

public class RectangleDecoration implements DividerDecoration{

    private Drawable mDivider;

    public RectangleDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.layer_grid);
    }

    @Override
    public void draw(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int top = child.getTop() - Math.round(ViewCompat.getTranslationY(child));
            final int bottom = child.getBottom() + Math.round(ViewCompat.getTranslationY(child));
            final int left = child.getLeft() - Math.round(ViewCompat.getTranslationX(child));
            final int right = child.getRight() + Math.round(ViewCompat.getTranslationX(child));
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}

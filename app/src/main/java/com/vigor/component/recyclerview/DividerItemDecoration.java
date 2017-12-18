package com.vigor.component.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Vigor on 16/10/21.
 * 自定义的间隔样式
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private DividerDecoration mDecoration;

    public DividerItemDecoration(DividerDecoration decoration) {
        this.mDecoration = decoration;
    }

    public void setItemDecoration(DividerDecoration decoration) {
        this.mDecoration = decoration;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        mDecoration.draw(c, parent);
    }
}

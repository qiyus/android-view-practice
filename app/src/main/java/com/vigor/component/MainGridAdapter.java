package com.vigor.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vigor on 16/10/20.
 * 主画面列表的Adapter
 */

public class MainGridAdapter extends RecyclerView.Adapter<MainGridAdapter.ViewHolder>{
    private List<MainDataItem> mData;
    private Context mContext;
    private int mSpanCount;

    public MainGridAdapter(Context context, List<MainDataItem> data, int spanCount) {
        mData = data;
        mContext = context;
        mSpanCount = spanCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mImage;

        public ViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.title);
            mImage = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_main_grid, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainDataItem item = mData.get(position);
        holder.mTitle.setText(item.getTitle());
        holder.mImage.setImageResource(item.getIconId());

        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
        int offset = holder.itemView.getPaddingLeft() +
                holder.itemView.getPaddingRight() +
                params.leftMargin + params.rightMargin;

        holder.mImage.getLayoutParams().height =
                getImageHeight(item.getIconId(), offset * mSpanCount, mSpanCount);
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    private int getImageHeight(int resId, int offset, int spanCount) {
        AppCompatActivity activity = (AppCompatActivity)mContext;
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = (float)(dm.widthPixels - offset) / spanCount;
        Drawable drawable = activity.getDrawable(resId);
        float scale = drawable.getIntrinsicWidth() / width;
        return Math.round(drawable.getIntrinsicHeight() / scale);
    }
}

package com.vigor.component;

import android.support.v7.widget.RecyclerView;
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

public class MainCardAdapter extends RecyclerView.Adapter<MainCardAdapter.ViewHolder>{
    private List<MainDataItem> mData;

    public MainCardAdapter(List<MainDataItem> data) {
        mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mIcon;

        public ViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.title);
            mIcon = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_main_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainDataItem item = mData.get(position);
        holder.mTitle.setText(item.getTitle());
        holder.mIcon.setImageResource(item.getIconId());
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}

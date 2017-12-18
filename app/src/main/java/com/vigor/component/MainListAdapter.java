package com.vigor.component;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vigor on 16/10/20.
 * 主画面列表的Adapter
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder>{
    private List<MainDataItem> mData;

    public MainListAdapter(List<MainDataItem> data) {
        mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;

        public ViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_main_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainDataItem item = mData.get(position);
        holder.mTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}

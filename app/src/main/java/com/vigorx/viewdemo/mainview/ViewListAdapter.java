package com.vigorx.viewdemo.mainview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vigorx.viewdemo.R;

import java.util.List;

/**
 * Created by songlei on 16/10/20.
 */

public class ViewListAdapter extends RecyclerView.Adapter<ViewListAdapter.ViewHolder>{
    private List<ViewDataItem> mData;

    public ViewListAdapter(List<ViewDataItem> data) {
        mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mDate;

        public ViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.title);
            mDate = (TextView) v.findViewById(R.id.date);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewDataItem item = mData.get(position);
        holder.mTitle.setText(item.getTitle());
        holder.mDate.setText(item.getDetail());
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}

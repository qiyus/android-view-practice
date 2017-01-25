package com.vigorx.viewdemo.swiperefreshlayout;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vigorx.viewdemo.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshLayoutDemoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private List<String> mData;
    private ArrayAdapter<String> mAdapter;
    private boolean mIsRefreshing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_demo);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("SwipeRefreshLayout item" + i);
        }

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        mListView = (ListView) findViewById(R.id.refresh_list);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh() {
        if (!mIsRefreshing) {
            mIsRefreshing = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mData.add("SwipeRefreshLayout new item");
                    mSwipeRefreshLayout.setRefreshing(false);
                    mAdapter.notifyDataSetChanged();
                    mIsRefreshing = false;
                }
            }, 3000);
        }
    }
}

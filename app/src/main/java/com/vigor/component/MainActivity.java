package com.vigor.component;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vigor.component.recyclerview.DividerDecoration;
import com.vigor.component.recyclerview.DividerItemDecoration;
import com.vigor.component.recyclerview.ItemClickSupport;
import com.vigor.component.recyclerview.LineDecoration;
import com.vigor.component.recyclerview.RectangleDecoration;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DividerItemDecoration mDividerItemDecoration;
    private DividerDecoration mListDividerDecoration;
    private DividerDecoration mGridDividerDecoration;
    private List<MainDataItem> mData;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private LinearLayoutManager mListLayoutManager;
    private MainListAdapter mListAdapter;
    private MainGridAdapter mGridAdapter;
    private MainGridAdapter mStaggeredAdapter;
    private static final int GRID_SPAN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mData = MainData.getInstance().getRecordList();
        initList();
    }

    public void initList() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // 设置Item之间间隔样式
        mListDividerDecoration = new LineDecoration(this, LineDecoration.VERTICAL_LIST);
        mGridDividerDecoration = new RectangleDecoration(this);
        mDividerItemDecoration = new DividerItemDecoration(mListDividerDecoration);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 设置布局管理器
        mListLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, GRID_SPAN_COUNT);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(GRID_SPAN_COUNT,
                OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(mListLayoutManager);

        // 设置adapter
        mListAdapter = new MainListAdapter(mData);
        mGridAdapter = new MainGridAdapter(this, mData, GRID_SPAN_COUNT);
        mStaggeredAdapter = new MainGridAdapter(this, mData, GRID_SPAN_COUNT);
        mRecyclerView.setAdapter(mListAdapter);

        /*// 设置点击事件另一种方法。
        mRecyclerView.addOnItemTouchListener(new OnItemTouchListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                toNextActivity(mData.get(position).getClassName());
            }
        });*/

        // 设置点击事件
        ItemClickSupport clickSupport = ItemClickSupport.addTo(mRecyclerView);
        clickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                toNextActivity(mData.get(position).getClassName());
            }
        });
    }

    public void toNextActivity(Class activityClass) {
        if (null != activityClass) {
            startActivity(new Intent(this, activityClass));
        } else {
            Snackbar snackbar = Snackbar.make(mRecyclerView, R.string.defaultNotStudyMessage, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_list) {
            setMainList(mListAdapter, mListDividerDecoration, mListLayoutManager);
            return true;
        } else if (id == R.id.action_grid) {
            setMainList(mGridAdapter, mGridDividerDecoration, mGridLayoutManager);
            return true;
        } else if (id == R.id.action_staggered) {
            setMainList(mStaggeredAdapter, mGridDividerDecoration, mStaggeredGridLayoutManager);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMainList(RecyclerView.Adapter adapter,
                             DividerDecoration decoration,
                             RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setAdapter(adapter);
        mDividerItemDecoration.setItemDecoration(decoration);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}

package com.vigorx.viewdemo.mainview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vigorx.viewdemo.R;
import com.vigorx.viewdemo.lifecycle.LifeCycleActivity;
import com.vigorx.viewdemo.recyclerview.DividerItemDecoration;
import com.vigorx.viewdemo.recyclerview.ItemClickSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainContract.IMainView {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ItemClickSupport mSupport;
    private RecyclerView.LayoutManager mLayoutManager;
    private IMainContract.IMainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPresenter = new MainPresenter(this, new ViewDataModel());
        mPresenter.initList();
    }

    @Override
    public void initList(final List<ViewDataItem> data) {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new ViewListAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        // 设置点击事件可以有2种方法，注释掉一种。
//        mRecyclerView.addOnItemTouchListener(new OnItemTouchListener(mRecyclerView) {
//            @Override
//            public void onItemClick(RecyclerView.ViewHolder vh) {
//            }
//        });

        mSupport = ItemClickSupport.addTo(mRecyclerView);
        mSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                toNextActivity(data.get(position).getType());
            }
        });
    }

    @Override
    public void message(String message) {
        Snackbar snackbar = Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void toNextActivity(int type) {
        switch (type){
            case 0:
                startActivity(new Intent(this, LifeCycleActivity.class));
                break;
            case 1:
                message("第二个Activity");
                break;
            default:
                message("未实现的例子");
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

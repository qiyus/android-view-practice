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
import com.vigorx.viewdemo.R;
import com.vigorx.viewdemo.customtab.CustomTabDemoActivity;
import com.vigorx.viewdemo.dialog.DialogDemoActivity;
import com.vigorx.viewdemo.fragment.FragmentDemoActivity;
import com.vigorx.viewdemo.lifecycle.LifeCycleDemoActivity;
import com.vigorx.viewdemo.pdfview.PDFViewDemoActivity;
import com.vigorx.viewdemo.progressbar.ProgressDemoActivity;
import com.vigorx.viewdemo.mainview.recyclerview.DividerItemDecoration;
import com.vigorx.viewdemo.mainview.recyclerview.ItemClickSupport;
import com.vigorx.viewdemo.surfaceview.SurfaceViewDemoActivity;
import com.vigorx.viewdemo.viewflipper.ViewFlipperDemoActivity;
import com.vigorx.viewdemo.viewpager.ViewPagerDemoActivity;

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
        /*mRecyclerView.addOnItemTouchListener(new OnItemTouchListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }
        });*/

        mSupport = ItemClickSupport.addTo(mRecyclerView);
        mSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                toNextActivity(data.get(position).getType());
            }
        });
    }

    @Override
    public void toNextActivity(int type) {
        switch (type){
            case 0:
                startActivity(new Intent(this, LifeCycleDemoActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, CustomTabDemoActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, DialogDemoActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, ProgressDemoActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, FragmentDemoActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, ViewPagerDemoActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, ViewFlipperDemoActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, PDFViewDemoActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, SurfaceViewDemoActivity.class));
            default:
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

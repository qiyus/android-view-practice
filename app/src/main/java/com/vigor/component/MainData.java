package com.vigor.component;

import com.vigor.component.commonintent.CommonIntentDemoActivity;
import com.vigor.component.canvas.CanvasDemoActivity;
import com.vigor.component.customtab.CustomTabDemoActivity;
import com.vigor.component.dialog.DialogDemoActivity;
import com.vigor.component.fragment.FragmentDemoActivity;
import com.vigor.component.fragmenttabhost.FragmentTabActivity;
import com.vigor.component.lifecycle.LifeCycleDemoActivity;
import com.vigor.component.notification.NotificationDemoActivity;
import com.vigor.component.pdfview.PDFViewDemoActivity;
import com.vigor.component.progressbar.ProgressDemoActivity;
import com.vigor.component.surfaceview.SurfaceViewDemoActivity;
import com.vigor.component.swiperefreshlayout.SwipeRefreshLayoutDemoActivity;
import com.vigor.component.viewflipper.ViewFlipperDemoActivity;
import com.vigor.component.viewpager.ViewPagerDemoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vigor on 2017/12/14.
 * 列表数据
 */

public class MainData {
    private static final MainData ourInstance = new MainData();

    private List<MainDataItem> mData = new ArrayList<>();

    public static MainData getInstance() {
        return ourInstance;
    }

    private MainData() {
        mData.add(new MainDataItem(LifeCycleDemoActivity.class, "PopupWindow", R.drawable.popupwindow));
        mData.add(new MainDataItem(CustomTabDemoActivity.class, "Custom Tab", R.drawable.customtab));
        mData.add(new MainDataItem(DialogDemoActivity.class, "DialogFragment", R.drawable.dialogfragment));
        mData.add(new MainDataItem(ProgressDemoActivity.class, "ProgressBar", R.drawable.progressbar));
        mData.add(new MainDataItem(FragmentDemoActivity.class, "Fragment", R.drawable.fragment));
        mData.add(new MainDataItem(ViewPagerDemoActivity.class, "Viewpager", R.drawable.viewpager));
        mData.add(new MainDataItem(ViewFlipperDemoActivity.class, "ViewFlipper", R.drawable.viewflipper));
        mData.add(new MainDataItem(NotificationDemoActivity.class, "Notification", R.drawable.notification));
        mData.add(new MainDataItem(SwipeRefreshLayoutDemoActivity.class, "SwipeRefreshLayout", R.drawable.swiperefresh));
        mData.add(new MainDataItem(PDFViewDemoActivity.class, "PDFView", R.drawable.pdfview));
        mData.add(new MainDataItem(SurfaceViewDemoActivity.class, "SurfaceView", R.drawable.surfaceview));
        mData.add(new MainDataItem(CanvasDemoActivity.class, "Canvas", R.drawable.canvas));
        mData.add(new MainDataItem(FragmentTabActivity.class, "FragmentTabHost", R.drawable.fragementtab));
        mData.add(new MainDataItem(CommonIntentDemoActivity.class, "Action view send", R.drawable.popupwindow));
    }

    public List<MainDataItem> getRecordList() {
        return mData;
    }
}

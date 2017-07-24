package com.vigorx.viewdemo.mainview;

import com.vigorx.viewdemo.action.ActionActivity;
import com.vigorx.viewdemo.fragmenttabhost.FragmentTabActivity;
import com.vigorx.viewdemo.canvas.CanvasDemoActivity;
import com.vigorx.viewdemo.customtab.CustomTabDemoActivity;
import com.vigorx.viewdemo.dialog.DialogDemoActivity;
import com.vigorx.viewdemo.fragment.FragmentDemoActivity;
import com.vigorx.viewdemo.lifecycle.LifeCycleDemoActivity;
import com.vigorx.viewdemo.notification.NotificationDemoActivity;
import com.vigorx.viewdemo.pdfview.PDFViewDemoActivity;
import com.vigorx.viewdemo.progressbar.ProgressDemoActivity;
import com.vigorx.viewdemo.surfaceview.SurfaceViewDemoActivity;
import com.vigorx.viewdemo.swiperefreshlayout.SwipeRefreshLayoutDemoActivity;
import com.vigorx.viewdemo.viewflipper.ViewFlipperDemoActivity;
import com.vigorx.viewdemo.viewpager.ViewPagerDemoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songlei on 16/10/21.
 */

class ViewDataModel implements IMainContract.IViewDataModel {
    private List<ViewDataItem> mData = new ArrayList<>();

    /**
     * 构造主画面的List数据源。
     */
    ViewDataModel() {
        addRecord(LifeCycleDemoActivity.class, "Activity Cycle", "AlertDialog|PopupWindow|PopupMenu|ContextMenu|RatingBar|ToggleButton");
        addRecord(CustomTabDemoActivity.class, "Custom Tab", "ViewPager|Fragment|CustomComponent");
        addRecord(DialogDemoActivity.class, "Dialog Fragment", "DialogFragment|AlertDialog|SimpleCursorAdapter|CursorLoader|Contacts");
        addRecord(ProgressDemoActivity.class, "ProgressBar", "ProgressBar|RatingBar|SeekBar");
        addRecord(FragmentDemoActivity.class, "Fragment", "Fragment|RecyclerView");
        addRecord(ViewPagerDemoActivity.class, "Viewpager", "FragmentPagerAdapter|PagerAdapter|FragmentPagerStateAdapter");
        addRecord(ViewFlipperDemoActivity.class, "ViewFlipper", "ViewFlipper|GestureDetector");
        addRecord(NotificationDemoActivity.class, "Notification", "NotificationCompat|PendingIntent|NotificationManager|RemoteViews");
        addRecord(SwipeRefreshLayoutDemoActivity.class, "SwipeRefreshLayout", "SwipeRefreshLayout");
        addRecord(PDFViewDemoActivity.class, "PDFView", "PDFView|assets");
        addRecord(SurfaceViewDemoActivity.class, "SurfaceView", "SurfaceView|Camera");
        addRecord(CanvasDemoActivity.class, "Canvas", "Canvas|Path");
        addRecord(FragmentTabActivity.class, "FragmentTabHost", "FragmentTabHost|Fragment");
        addRecord(ActionActivity.class, "Action view send", "Intent.ACTION_VIEW|Intent.ACTION_SEND");
    }

    /**
     * 追加例子Item到list数据。
     * @param activityClass activity的class对象。
     * @param title 标题
     * @param detail 私用的控件
     */
    private void addRecord(Class activityClass, String title, String detail) {
        ViewDataItem item = new ViewDataItem();
        item.setaClass(activityClass);
        item.setTitle(title);
        item.setDetail(detail);
        mData.add(item);
    }

    @Override
    public List<ViewDataItem> getRecordList() {
        return mData;
    }
}

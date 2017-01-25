package com.vigorx.viewdemo.mainview;

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
        // 初始化列表数据。
        addRecord(LifeCycleDemoActivity.class, "activity事件的调用顺序。", "AlertDialog|PopupWindow|PopupMenu|ContextMenu|RatingBar|ToggleButton");
        addRecord(CustomTabDemoActivity.class, "模仿微信的自定义Tab的例子。", "ViewPager|Fragment|CustomComponent");
        addRecord(DialogDemoActivity.class, "DialogFragment的例子。", "DialogFragment|AlertDialog|SimpleCursorAdapter|CursorLoader|Contacts");
        addRecord(ProgressDemoActivity.class, "ProgressBar的例子。", "ProgressBar|RatingBar|SeekBar");
        addRecord(FragmentDemoActivity.class, "Fragment的通信、事物的例子。", "Fragment|RecyclerView");
        addRecord(ViewPagerDemoActivity.class, "Viewpager的例子。", "FragmentPagerAdapter|PagerAdapter|FragmentPagerStateAdapter");
        addRecord(ViewFlipperDemoActivity.class, "ViewFlipper实现引导页面的例子。", "ViewFlipper|GestureDetector");
        addRecord(NotificationDemoActivity.class, "Notification的例子", "NotificationCompat|PendingIntent|NotificationManager|RemoteViews");
        addRecord(SwipeRefreshLayoutDemoActivity.class, "SwipeRefreshLayout的例子", "SwipeRefreshLayout");
        addRecord(PDFViewDemoActivity.class, "PDFView的例子。", "PDFView|assets");
        addRecord(SurfaceViewDemoActivity.class, "SurfaceView的例子。", "SurfaceView|Camera");
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

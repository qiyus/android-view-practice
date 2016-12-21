package com.vigorx.viewdemo.mainview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songlei on 16/10/21.
 */

class ViewDataModel implements IMainContract.IViewDataModel {
    private List<ViewDataItem> mData = new ArrayList<>();

    /**
     * 初期化
     */
    ViewDataModel() {
        initData();
    }

    /**
     * 初始化列表数据。
     */
    private void initData() {
        addRecord(0, "activity事件的调用顺序。", "AlertDialog|PopupWindow|PopupMenu|ContextMenu|RatingBar|ToggleButton");
        addRecord(1, "模仿微信的自定义Tab的例子。", "ViewPager|Fragment|CustomComponent");
        addRecord(2, "DialogFragment的例子。", "DialogFragment|AlertDialog|SimpleCursorAdapter|CursorLoader|Contacts");
        addRecord(3, "ProgressBar的例子。", "ProgressBar|RatingBar|SeekBar");
        addRecord(4, "Fragment的通信、事物的例子。", "Fragment|RecyclerView");
        addRecord(5, "Viewpager的例子。", "FragmentPagerAdapter|PagerAdapter|FragmentPagerStateAdapter");
        addRecord(6, "引导页面的例子。", "未学习");
        addRecord(7, "Android Notification通知栏的例子", "未学习");
        addRecord(8, "PullRefreshView的例子", "未学习");
        addRecord(9, "PDFView的例子。", "PDFView|assets");
        addRecord(10, "SurfaceView的例子。", "SurfaceView|Camera");
    }

    /**
     * 追加例子Item到list数据。
     * @param type activity标记
     * @param title 标题
     * @param detail 私用的控件
     */
    private void addRecord(int type, String title, String detail) {
        ViewDataItem item = new ViewDataItem();
        item.setType(type);
        item.setTitle(title);
        item.setDetail(detail);
        mData.add(item);
    }

    @Override
    public List<ViewDataItem> getRecordList() {
        return mData;
    }
}

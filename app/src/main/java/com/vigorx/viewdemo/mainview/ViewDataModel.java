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
        addRecord(1, "ViewPager和Fragment实现Tab效果。", "ViewPager|Fragment");
        addRecord(2, "DialogFragment的使用。", "DialogFragment|AlertDialog|SimpleCursorAdapter|CursorLoader|Contacts");
        addRecord(3, "ProgressBar的使用。", "ProgressBar|RatingBar|SeekBar");
        addRecord(4, "CardView的使用。", "未学习");
        addRecord(5, "Viewpager的使用。", "FragmentPagerAdapter|PagerAdapter|FragmentPagerStateAdapter");
        addRecord(6, "ViewFlipper的使用。", "未学习");
        addRecord(7, "Android Notification通知栏的使用", "未学习");
        addRecord(8, "PullRefreshView的使用", "未学习");
        addRecord(9, "PdfView的使用", "未学习");
        addRecord(10, "SurfaceView的使用。", "未学习");
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

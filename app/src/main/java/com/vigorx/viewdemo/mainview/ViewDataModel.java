package com.vigorx.viewdemo.mainview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songlei on 16/10/21.
 */

public class ViewDataModel implements IMainContract.IViewDataModel {
    private List<ViewDataItem> mData;

    /**
     * 初期化
     */
    public ViewDataModel() {
        mData = new ArrayList<>();
        initData();
    }

    /**
     * 初始化列表数据。
     */
    private void initData() {
        addRecord(0, "查看activity的事件调用顺序。", "AlertDialog|PopupWindow|PopupMenu|ContextMenu|RatingBar|ToggleButton");
        addRecord(1, "对话框的使用。", "2016-10-21");
        addRecord(2, "进度条的使用。", "2016-10-21");
        addRecord(3, "SurfaceView的使用。", "2016-10-21");
        addRecord(4, "CardView的使用。", "2016-10-29");
        addRecord(5, "Viewpager的使用。", "2016-10-21");
        addRecord(6, "ViewFlipper的使用。", "2016-10-29");
        addRecord(7, "Android Notification通知栏的使用", "2016-11-12");
        addRecord(8, "PullRefreshView的使用", "2016-12-12");
        addRecord(9, "PdfView的使用", "2016-12-12");
        addRecord(10, "Menu的使用。", "ContextMenu");
    }

    /**
     * 追加例子Item到list数据。
     * @param title 标题
     * @param date 日期
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

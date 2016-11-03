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
        addRecord(0, "利用log查看activity的事件顺序，SnackBar、PopupWindow、PopupMenu、AlertDialog对事件的影响。", "2016-10-28");
        addRecord(1, "对话框的使用。", "2016-10-21");
        addRecord(3, "进度条的使用。", "2016-10-21");
        addRecord(4, "SurfaceView的使用。", "2016-10-21");
        addRecord(5, "Viewpager的使用。", "2016-10-21");
        addRecord(6, "Viewflipper的使用。", "2016-10-29");
        addRecord(7, "CardView的使用。", "2016-10-29");
        addRecord(8, "PopupWindow和PopupMenu的使用", "2016-11-11");
        addRecord(9, "Android Notification通知栏", "2016-11-12");
        addRecord(10, "调用另一个activity", "2016-11-13");
        addRecord(11, "PullRefreshView的使用", "2016-12-12");
        addRecord(12, "PdfView的使用", "2016-12-12");
        addRecord(13, "PdfView的使用", "2016-12-12");
    }

    /**
     * 追加例子Item到list数据。
     * @param title 标题
     * @param date 日期
     */
    private void addRecord(int type, String title, String date) {
        ViewDataItem item = new ViewDataItem();
        item.setType(type);
        item.setTitle(title);
        item.setDate(date);
        mData.add(item);
    }

    @Override
    public List<ViewDataItem> getRecordList() {
        return mData;
    }
}

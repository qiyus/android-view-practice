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
        addRecord(0, "多种风格的进度条使用展示。", "2016-10-21");
        addRecord(1, "多种风格的进度条使用展示。", "2016-10-21");
        addRecord(3, "多种风格的进度条使用展示。", "2016-10-21");
        addRecord(4, "多种风格的进度条使用展示。", "2016-10-21");
        addRecord(5, "多种风格的进度条使用展示。", "2016-10-21");
        addRecord(6, "多种风格的进度条使用展示。", "2016-10-29");
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

package com.vigor.component;

/**
 * Created by Vigor on 16/10/20.
 * 列表项
 */

public class MainDataItem {
    private String title;
    private Class className;
    private int iconId;

    public MainDataItem(Class className, String title, int iconId) {
        this.title = title;
        this.className = className;
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public Class getClassName() {
        return className;
    }

    public int getIconId() {
        return iconId;
    }
}

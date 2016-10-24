package com.vigorx.viewdemo.mainview;

import java.util.List;

/**
 * Created by songlei on 16/10/20.
 */

public interface IMainContract {
    /**
     * Created by songlei on 16/10/20.
     */

    interface IMainView {
        void initList(List<ViewDataItem> data);
        void toastMessage(String message);
    }

    /**
     * Created by songlei on 16/10/20.
     */

    interface IMainPresenter {
        void initList();
        void startToActivity(int type);
    }

    /**
     * Created by songlei on 16/10/21.
     */

    interface IViewDataModel {
        List<ViewDataItem> getRecordList();
    }
}

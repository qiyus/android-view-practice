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
        void toNextActivity(int type);
        void message(String message);
    }

    /**
     * Created by songlei on 16/10/20.
     */
    interface IMainPresenter {
        void initList();
    }

    /**
     * Created by songlei on 16/10/21.
     */

    interface IViewDataModel {
        List<ViewDataItem> getRecordList();
    }
}

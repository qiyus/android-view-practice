package com.vigorx.viewdemo.mainview;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songlei on 16/10/20.
 */

public class MainPresenter implements IMainContract.IMainPresenter {
    private IMainContract.IMainView mView;
    private IMainContract.IViewDataModel mModel;

    public MainPresenter(IMainContract.IMainView view, IMainContract.IViewDataModel model) {
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void initList() {
        mView.initList(mModel.getRecordList());
    }
}

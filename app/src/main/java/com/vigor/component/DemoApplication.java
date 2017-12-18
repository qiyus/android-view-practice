package com.vigor.component;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Vigor on 2017/1/6.
 */

public class DemoApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}

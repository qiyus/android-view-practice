package com.vigorx.viewdemo.viewflipper;

import android.content.Context;

/**
 * Created by songlei on 2017/1/6.
 */

public class Singleton {
    private static Singleton instance;
    private Context mContext;
    private Singleton(Context context){
        this.mContext = context;
    }

    public static Singleton getInstance(Context context){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton(context);
                }
            }
        }
        return instance;
    }
}

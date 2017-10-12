package com.xiufeng.wedleairplane.customerize;

import android.app.Application;


import com.baidu.mapapi.SDKInitializer;

import org.xutils.x;

/**
 * Created by huangxiufeng on 17/4/14.
 */

public class WBApplication extends Application {
    private static WBApplication app;

    public static WBApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SDKInitializer.initialize(getApplicationContext());
        x.Ext.init(this);
    }
}
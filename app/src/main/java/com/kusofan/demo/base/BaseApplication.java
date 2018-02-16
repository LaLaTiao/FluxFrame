package com.kusofan.demo.base;

import android.app.Application;
import android.content.Context;

import com.kusofan.demo.base.net.ConfigManager;
import com.kusofan.demo.base.net.RequestManager;


/**
 * Created by heming on 2017/12/13.
 */

public abstract class BaseApplication extends Application{
    public static boolean DEBUG = false;
    public static ConfigManager sConfigManager;
    public static RequestManager sRequestManager;
    public static Context sAppContext;

    public BaseApplication() {
    }

    public void onCreate() {
        super.onCreate();
        this.init();
    }

    private void init() {
        sAppContext = this;
        sConfigManager = ConfigManager.getInstance();
//        sRequestManager = RequestManager.getInstance(this.getHttpClient());
        sRequestManager = RequestManager.getInstance();
        refreshConfig();
    }

    public static void refreshConfig() {
        DEBUG = sConfigManager.mDebugOn;
        sRequestManager.refreshConfig();
    }

//    protected abstract OkHttpClient getHttpClient();
    protected abstract void getHttpClient();
}

package com.kusofan.demo.base.net;

/**
 * Created by heming on 2017/12/13.
 */

public class ConfigManager {
    private static ConfigManager sInstance;
    public boolean mDebugOn = true;
    public String mBaseUrl = "https://api.github.com";

    public ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if(sInstance == null) {
            sInstance = new ConfigManager();
        }

        return sInstance;
    }
}

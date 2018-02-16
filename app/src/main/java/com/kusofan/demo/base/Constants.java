package com.kusofan.demo.base;


/**
 * Created by xujian on 16/2/3.
 */
public class Constants {

    public static final boolean DEBUG = true;

    public enum ENVIRONMENT {
        PRD,
        STG_HTTPS,
        DEV,
    }

    public static ENVIRONMENT ENV = ENVIRONMENT.STG_HTTPS;

    //分享WiFi
    public static final String H5_SHARE_WIFI = getEndPoint() + "/mifi-uc/shareAct/course/shareWiFi.html";

    public static String getEndPoint() {
        switch (ENV) {
            case PRD:
                return "https://mifi.pingan.com.cn";
            case STG_HTTPS:
                return "http://gank.io/api/";
            case DEV:
                return "http://10.180.203.18:6080";

        }
        return "https://test-mifi-web-stg1.pingan.com.cn:3043";
    }
}

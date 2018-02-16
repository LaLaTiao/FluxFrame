package com.kusofan.demo.utils;

import android.text.TextUtils;
import android.util.Log;

import com.kusofan.demo.base.BaseApplication;


/**
 * Created by heming on 2017/12/13.
 */

public class LogUtil {
    private static final int MAX_LENGTH = 500;
    private static final String EMPTY = "---empty log---";

    public LogUtil() {
    }

    public static void i(String tag, String msg) {
        if (BaseApplication.DEBUG) {
            if (TextUtils.isEmpty(msg)) {
                msg = "---empty log---";
            }

            while (msg.length() > 500) {
                Log.i(tag, msg.substring(0, 500));
                msg = msg.substring(500);
            }

            Log.i(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if (BaseApplication.DEBUG) {
            if (TextUtils.isEmpty(msg)) {
                msg = "---empty log---";
            }

            while (msg.length() > 500) {
                Log.v(tag, msg.substring(0, 500));
                msg = msg.substring(500);
            }

            Log.v(tag, msg);
        }

    }

    public static void d(String tag, String msg) {
        if (BaseApplication.DEBUG) {
            if (TextUtils.isEmpty(msg)) {
                msg = "---empty log---";
            }

            while (msg.length() > 500) {
                Log.d(tag, msg.substring(0, 500));
                msg = msg.substring(500);
            }

            Log.d(tag, msg);
        }

    }

    public static void e(String tag, String msg) {
        if (BaseApplication.DEBUG) {
            if (TextUtils.isEmpty(msg)) {
                msg = "---empty log---";
            }

            while (msg.length() > 500) {
                Log.e(tag, msg.substring(0, 500));
                msg = msg.substring(500);
            }

            Log.e(tag, msg);
        }

    }

    public static void w(String tag, String msg) {
        if (BaseApplication.DEBUG) {
            if (TextUtils.isEmpty(msg)) {
                msg = "---empty log---";
            }

            while (msg.length() > 500) {
                Log.w(tag, msg.substring(0, 500));
                msg = msg.substring(500);
            }

            Log.w(tag, msg);
        }
    }
}

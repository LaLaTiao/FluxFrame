package com.kusofan.demo.utils;

/**
 * Created by heming on 2017/12/13.
 */

public class StringUtils {
    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static int length(CharSequence str) {
        return str == null?0:str.length();
    }

    public static String nullStrToEmpty(Object str) {
        return str == null?"":(str instanceof String?(String)str:str.toString());
    }
}

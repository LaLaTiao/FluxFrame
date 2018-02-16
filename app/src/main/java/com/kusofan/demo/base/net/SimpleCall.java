package com.kusofan.demo.base.net;

/**
 * Created by heming on 2017/12/13.
 */

public interface SimpleCall<T> {
    void cancel();

    void enqueue(ErrorHandlingCallBack<T> var1);

    SimpleCall<T> clone();
}

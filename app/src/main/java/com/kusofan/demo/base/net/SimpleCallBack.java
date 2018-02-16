package com.kusofan.demo.base.net;

/**
 * Created by heming on 2017/12/13.
 */

public interface SimpleCallBack<T> {
    void onSuccess(T var1);

    void onFailure(Object var1);

    void onError(Exception var1);
}

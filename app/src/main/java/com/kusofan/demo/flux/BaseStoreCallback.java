package com.kusofan.demo.flux;

/**
 * Created by xujian on 16/2/14.
 */
public interface BaseStoreCallback<T> {

    void getNew();

    void useCache(T cache);
}

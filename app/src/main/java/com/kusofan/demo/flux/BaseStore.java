package com.kusofan.demo.flux;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xujian on 16/2/14.
 */
public abstract class BaseStore {

    private Dispatcher mDispatcher;
    private Map<String, Long> mLastStoreTimeMap;

    public BaseStore() {
        mDispatcher = Dispatcher.getInstance();
        mLastStoreTimeMap = new HashMap<>();
    }

    protected abstract Map<String, Long> getCacheTimeMap();
    public abstract <T> T getCache(String key);

    public void register() {
        mDispatcher.register(this);
    }

    public void unregister() {
        mDispatcher.unregister(this);
    }

    public void post(BaseEvent event) {
        mDispatcher.post(event);
    }

    private boolean needRefresh(String key) {
        long time = System.currentTimeMillis();
        return time - getLastStoreTime(key) > getCacheTime(key);
    }

    protected void refreshStoreTime(String key) {
        mLastStoreTimeMap.put(key, System.currentTimeMillis());
    }

    /**
     * 获取当前store中储存的状态数据，如果需要刷新，则回调callback
     * @param key 任意的String类型的key，在当前store中做区分即可，建议定义类常量
     * @param callback
     */
    public void getData(String key, BaseStoreCallback callback) {
        if (needRefresh(key)) {
            callback.getNew();
        } else if (getCache(key) != null){
            callback.useCache(getCache(key));
        }
    }

    private long getCacheTime(String key) {
        Map<String, Long> timeMap = getCacheTimeMap();
        if (timeMap == null || timeMap.size() == 0 || !timeMap.containsKey(key)) {
            return 0;
        }
        return timeMap.get(key);
    }

    private long getLastStoreTime(String key) {
        if (mLastStoreTimeMap.containsKey(key)) {
            return mLastStoreTimeMap.get(key);
        }
        return 0;
    }
}

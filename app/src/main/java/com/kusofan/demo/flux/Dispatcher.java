package com.kusofan.demo.flux;


import org.greenrobot.eventbus.EventBus;

/**
 * Created by xujian on 16/2/14.
 */
public class Dispatcher {

    private static Dispatcher sInstance;

    public static Dispatcher getInstance() {
        if (sInstance == null) {
            sInstance = new Dispatcher();
        }
        return sInstance;
    }

    private final EventBus mBus;

    public Dispatcher() {
        mBus = new EventBus();
    }

    public void register(final Object o) {
        mBus.register(o);
    }

    public void unregister(final Object o) {
        mBus.unregister(o);
    }

    public void dispatch(BaseAction action) {
        mBus.post(action);
    }

    public void post(BaseEvent event) {
        mBus.post(event);
    }
}

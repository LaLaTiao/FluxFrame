package com.kusofan.demo.home.stores;


import com.kusofan.demo.flux.BaseEvent;
import com.kusofan.demo.flux.BaseStore;
import com.kusofan.demo.home.actions.GankGirlAction;
import com.kusofan.demo.home.model.GirlMode;

import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

/**
 * Created by heming on 2017/12/13.
 */

public class GankStore extends BaseStore {
    @Override
    protected Map<String, Long> getCacheTimeMap() {
        return null;
    }

    @Override
    public <T> T getCache(String key) {
        return null;
    }

    private static GankStore sInstance;

    public static GankStore getInstance() {
        if (sInstance == null) {
            sInstance = new GankStore();
        } else {
            sInstance.unregister();
        }
        return sInstance;
    }

    @Subscribe
    public void onGirl(GankGirlAction action) {
        if (action.getData() != null && !action.getData().error) {
            GirlMode data = action.getData();
            post(new GirlSuccessEvent(data));
        }
    }

    public class GirlSuccessEvent implements BaseEvent {
        private GirlMode mode;

        public GirlSuccessEvent(GirlMode mode) {
            this.mode = mode;
        }

        public GirlMode getMode() {
            return mode;
        }
    }
}

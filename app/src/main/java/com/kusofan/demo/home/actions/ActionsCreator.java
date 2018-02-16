package com.kusofan.demo.home.actions;


import com.kusofan.demo.base.MyBaseActivity;
import com.kusofan.demo.base.net.SimpleCallBack;
import com.kusofan.demo.flux.Dispatcher;
import com.kusofan.demo.home.api.HomeServices;
import com.kusofan.demo.home.model.GirlMode;

/**
 * Created by jinjin on 16/2/16.
 */
public class ActionsCreator {
    
    private static final String TAG = "Mifi-ActionsCreator";
    
    private static ActionsCreator sInstance;
    
    public static ActionsCreator getInstance() {
        if (sInstance == null) {
            sInstance = new ActionsCreator();
        }
        return sInstance;
    }
    
    private Dispatcher mDispatcher;
    
    public ActionsCreator() {
        mDispatcher = Dispatcher.getInstance();
    }
    


    
    /**
     * 随机给个妹子
     */
    public void getRandomGirl(MyBaseActivity activity, int num) {
        activity.addRequest(activity.getService(HomeServices.class).getGirlList(num),
            new SimpleCallBack<GirlMode>() {
                @Override
                public void onSuccess(GirlMode model) {
                    mDispatcher.dispatch(new GankGirlAction(model));
                }
                
                @Override
                public void onFailure(Object o) {
                    mDispatcher.dispatch(new GankGirlAction(null));
                }
                
                @Override
                public void onError(Exception e) {
                    mDispatcher.dispatch(new GankGirlAction(null));
                }
            }, false, false);
    }

}

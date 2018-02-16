package com.kusofan.demo.home.actions;


import com.kusofan.demo.flux.BaseAction;
import com.kusofan.demo.home.model.GirlMode;

/**
 * Created by heming on 2017/12/13.
 */

public class GankGirlAction extends BaseAction {
    GirlMode model;

    public GankGirlAction(GirlMode model) {
        this.model = model;
    }

    @Override
    public GirlMode getData() {
        return model;
    }

}

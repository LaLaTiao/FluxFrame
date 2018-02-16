package com.kusofan.demo.home.api;


import com.kusofan.demo.base.net.SimpleCall;
import com.kusofan.demo.home.model.GirlMode;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by heming on 2017/12/13.
 */

public interface HomeServices {

    //获取随机妹子图片

    /**
     * 妹纸列表
     */
    @GET("random/data/福利/{num}")
    SimpleCall<GirlMode> getGirlList(@Path("num") int num);
//    SimpleCall<List<GankItemModel>> getGirlList(@Path("num") int num);
}

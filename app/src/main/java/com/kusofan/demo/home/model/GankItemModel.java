package com.kusofan.demo.home.model;


import com.kusofan.demo.base.MyBaseModel;

import java.util.List;


/**
 * Created by codeest on 16/8/20.
 */

public class GankItemModel extends MyBaseModel {

    public List<Gril> results;

    private class Gril {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String type;
        public String url;
        public String used;
        public String who;
    }

    /**
     * {
     * "error": false,
     * "results": [
     * {
     * "_id": "56cc6d1d421aa95caa7078ad",
     * "createdAt": "2015-09-15T08:47:12.714Z",
     * "desc": "9.16",
     * "publishedAt": "2015-09-16T03:34:05.100Z",
     * "type": "\u798f\u5229",
     * "url": "http://ww3.sinaimg.cn/large/7a8aed7bgw1ew38eojcpzj20p010kwjr.jpg",
     * "used": true,
     * "who": "\u5f20\u6db5\u5b87"
     * }
     * ]
     * }
     */
}

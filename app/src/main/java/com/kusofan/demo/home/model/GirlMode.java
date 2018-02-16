package com.kusofan.demo.home.model;

import java.util.List;

/**
 * Created by heming on 2017/12/14.
 */

public class GirlMode {

    /**
     * error : false
     * results : [{"_id":"56cc6d1d421aa95caa7078ad","createdAt":"2015-09-15T08:47:12.714Z","desc":"9.16","publishedAt":"2015-09-16T03:34:05.100Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1ew38eojcpzj20p010kwjr.jpg","used":true,"who":"张涵宇"}]
     */

    public boolean error;
    public List<ResultsBean> results;

    public class ResultsBean {
        /**
         * _id : 56cc6d1d421aa95caa7078ad
         * createdAt : 2015-09-15T08:47:12.714Z
         * desc : 9.16
         * publishedAt : 2015-09-16T03:34:05.100Z
         * type : 福利
         * url : http://ww3.sinaimg.cn/large/7a8aed7bgw1ew38eojcpzj20p010kwjr.jpg
         * used : true
         * who : 张涵宇
         */
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String type;
        private String url;
        private boolean used;
        private String who;

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    '}';
        }
    }
}

package com.jeno.movie.networkBean;

import java.util.List;

/**
 *
 {
 "code": 200,
 "msg": "success",
 "data": {
 "limit": 20,
 "total": 419,
 "page": 21,
 "list": [
 {
 "vod_en": "liudehuadianyingheji",
 "vod_blurb": "片子讲述了退休军官老丁(洪金宝饰)因“健忘症”弄丢孙女，惭愧回到老家邻人小女孩春花(陈沛妍 饰)成为他的安慰。可女孩的父亲(刘德华饰)有意卷入了本地黑帮团伙的争斗中。老丁为营救春花自告奋勇，也寻那份作",
 "vod_actor": "洪金宝,刘德华,朱雨辰,李勤勤,冯嘉怡,",
 "vod_id": 351289,
 "vod_name": "刘德华电影合集",
 "vod_status": 1,
 "vod_letter": "L",
 "vod_pic": "http://ws3.sinaimg.cn/large/007FdMgUly1g0tmoup0pij305006ot9c.jpg",
 "vod_remarks": "蓝光",
 "vod_time": 1620242110,
 "vod_version": "",
 "vod_level": 0
 },
 */
public class SearchResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private SearchData data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SearchData getData() {
        return data;
    }

    public class SearchData {
        private int limit;
        private int total;
        private int pagecount;
        List<VodItemBean> list;

        public int getLimit() {
            return limit;
        }

        public int getTotal() {
            return total;
        }

        public int getPageCount() {
            return pagecount;
        }

        public List<VodItemBean> getSearchItems() {
            return list;
        }

        @Override
        public String toString() {
            return "SearchData{" +
                    "limit=" + limit +
                    ", total=" + total +
                    ", pagecount=" + pagecount +
                    ", list=" + list +
                    '}';
        }
    }


}

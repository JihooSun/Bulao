package com.jeno.movie.networkBean;

import java.util.List;

/**
 *{
 {
 "code": 200,
 "msg": "success",
 "data": {
 "limit": 6,
 "total": 10000,
 "page": 1667,
 "list": [
 {
 "vod_id": 371122,
 "vod_name": "蔬菜不寂寞 第25季",
 "vod_status": 1,
 "vod_letter": "S",
 "vod_pic": "http://pic5.iqiyipic.com/image/20210527/bd/95/a_100461416_m_601_195_260.jpg",
 "vod_remarks": "全20集",
 "vod_time": 1623149767,
 "vod_version": "",
 "vod_level": 0
 }
 ]
 }
 }
 */
public class DetailRecommendListResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private Data data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Data getData() {
        return data;
    }

    public class Data {
        private int limit;
        private int total;
        private int page;
        private List<VodItemBean> list;

        public int getLimit() {
            return limit;
        }

        public int getTotal() {
            return total;
        }

        public int getPage() {
            return page;
        }

        public List<VodItemBean> getList() {
            return list;
        }
    }

}

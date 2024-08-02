package com.jeno.movie.networkBean;

import java.util.List;

/**
 {
 "code": 200,
 "msg": "success",
 "data": [
 {
 "vod_id": 379304,
 "vod_name": "飘动的红丝带",
 "vod_status": 1,
 "vod_letter": "P",
 "vod_pic": "http://r1.ykimg.com/051600005FDB38702027EE0841CE91E0",
 "vod_remarks": "高清",
 "vod_time": 1644696956,
 "vod_version": "",
 "vod_level": 0,
 "vod_actor": "余晨曦,高剑飞,吴振舟",
 "vod_class": "剧情",
 "vod_area": "内地",
 "vod_year": "2021",
 "vod_score": "3.0",
 "vod_lang": "普通话"
 }
 ]
 }
 */
public class GetListByIdsResp {
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
        private List<VodItemBean> list;

        public List<VodItemBean> getList() {
            return list;
        }
    }
}

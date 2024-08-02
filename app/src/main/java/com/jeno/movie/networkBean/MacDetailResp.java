package com.jeno.movie.networkBean;

import java.util.List;

/**
 {
 "code": 200,
 "msg": "success",
 "data": {
 "limit": 20,
 "total": 13839,
 "page": 692,
 "list": [
 {
 "vod_id": 371921,
 "vod_name": "喜羊羊与灰太狼之喜气羊羊过蛇年",
 "vod_status": 1,
 "vod_letter": "X",
 "vod_pic": "http://i0.hdslb.com/bfs/bangumi/image/68d914e9ba9b6038a40193d2f93c1f3dc0b226a5.png@230w_324h",
 "vod_remarks": "更新至0集",
 "vod_time": 1627582201,
 "vod_version": "",
 "vod_level": 0
 }
 ]
 }
 }
 */
public class MacDetailResp {
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

        public List<VodItemBean> getVodDetailItemList() {
            return list;
        }
    }

}

package com.jeno.movie.networkBean;

import java.util.List;

/**
 {
 "code": 200,
 "msg": "成功",
 "data": {
 "id": 1,
 "tag": "chuanshanjia",
 "name": "穿山甲",
 "adid": "5001121",
 "open_status": 1,
 "item_list": [
 {
 "id": 1,
 "advert_id": 1,
 "tag": "kaiping",
 "adid": "801121648",
 "time": "0",
 "open_status": 1
 },
 {
 "id": 2,
 "advert_id": 1,
 "tag": "xinxiliu",
 "adid": "901121253",
 "time": "0",
 "open_status": 1
 },
 {
 "id": 3,
 "advert_id": 1,
 "tag": "jili",
 "adid": "901121365",
 "time": "7200",
 "open_status": 1
 }
 ]
 }
 }
 */
public class GetAdvertResp {
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

    public class Data{
        String tag;
        List<AdItem> item_list;
        String adid;
        int open_status;

        public String getTag() {
            return tag;
        }

        public List<AdItem> getList() {
            return item_list;
        }

        public String getAdid() {
            return adid;
        }

        public int getOpen_status() {
            return open_status;
        }
    }

    public class AdItem {
        String tag;
        String time;
        String adid;
        int open_status;

        public String getTag() {
            return tag;
        }

        public String getTime() {
            return time;
        }

        public String getAdid() {
            return adid;
        }

        public int getOpen_status() {
            return open_status;
        }
    }
}

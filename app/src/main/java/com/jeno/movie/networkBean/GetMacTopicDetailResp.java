package com.jeno.movie.networkBean;

import java.util.List;

/**
 {
 "code": 200,
 "msg": "success",
 "data": {
 "topic_id": 26,
 "topic_name": "庆祝建党100周年",
 "topic_en": "qingzhujiandang100zhounian",
 "vod_list": [
 {
 "vod_id": 220115,
 "vod_name": "建党伟业",
 "vod_status": 1,
 "vod_letter": "J",
 "vod_pic": "http://2img.hitv.com/preview/internettv/sp_images/ott/2016/dianying/6878/20160621102412857-new.jpg",
 "vod_remarks": "remarks",
 "vod_time": 1627581631,
 "vod_version": "",
 "vod_level": 0
 }
 ]
 }
 }
 */
public class GetMacTopicDetailResp {
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
        private int topic_id;
        private String topic_name;
        private String topic_en;
        List<VodItemBean> vod_list;

        public int getTopic_id() {
            return topic_id;
        }

        public String getTopic_name() {
            return topic_name;
        }

        public String getTopic_en() {
            return topic_en;
        }

        public List<VodItemBean> getVod_list() {
            return vod_list;
        }
    }

}

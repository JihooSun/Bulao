package com.jeno.movie.networkBean;

import java.util.List;

/**
 {
 "code": 200,
 "msg": "success",
 "data": {
 "limit": 19,
 "total": 19,
 "page": 1,
 "list": [
 {
 "topic_id": 24,
 "topic_name": "属于硬汉的精彩",
 "topic_en": "shuyuyinghandejingcai",
 "topic_pic": "https://img12.360buyimg.com/ddimg/jfs/t1/192420/18/8451/786983/60c98b4aE5c32d28a/84deb775bef5af2a.png",
 "topic_blurb": ""
 }
 ]
 }
 }
 */
public class GetMacTopicAllResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private List<TopicItem> data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<TopicItem> getData() {
        return data;
    }

    public class TopicItem {
        private Integer topic_id;
        private String topic_name;
        private String topic_en;
        private String topic_pic;
        private String topic_sub;

        public Integer getTopic_id() {
            return topic_id;
        }

        public String getTopic_name() {
            return topic_name;
        }

        public String getTopic_en() {
            return topic_en;
        }

        public String getTopic_pic() {
            return topic_pic;
        }

        public String getTopic_sub() {
            return topic_sub;
        }
    }

}

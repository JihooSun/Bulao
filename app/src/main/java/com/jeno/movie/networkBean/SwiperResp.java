package com.jeno.movie.networkBean;

import java.util.List;

/**
 *
 {
 "code": 200,
 "msg": "成功",
 "data": [
 {
 "banner_title": "title_1",
 "banner_subTitle": "子title_1",
 "banner_jumpType": 1,
 "banner_pic": "upload/banner/20230405-1/c096b7d7aa9565d6f8b23578dfe8833a.png",
 "banner_link": "28096"
 },
 {
 "banner_title": "title_1",
 "banner_subTitle": "子title_1",
 "banner_jumpType": 2,
 "banner_pic": "upload/banner/20230405-1/52fb9e42d548fbc41e8c3a35a18d7215.png",
 "banner_link": "1"
 },
 {
 "banner_title": "title_1",
 "banner_subTitle": "子title_1",
 "banner_jumpType": 3,
 "banner_pic": "upload/banner/20230405-1/a2ca61a7cbff951001c7efbeaf84d457.png",
 "banner_link": "www.baidu.com"
 }
 ]
 }
 */
public class SwiperResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private List<SwiperItem> data;

    private List<HotItem> extData;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<SwiperItem> getData() {
        return data;
    }

    public List<HotItem> getExtData() {
        return extData;
    }

    public class HotItem {
        private String typeName;
        private String typeId;
        private List<HotVod> vod_list;

        public String getTypeName() {
            return typeName;
        }

        public String getTypeId() {
            return typeId;
        }

        public List<HotVod> getVod_list() {
            return vod_list;
        }
    }

    public class HotVod {
        private int vod_id;
        private String vod_name;
        private String vod_pic;
        private String vod_actor;

        public int getVod_id() {
            return vod_id;
        }

        public String getVod_name() {
            return vod_name;
        }

        public String getVod_pic() {
            return vod_pic;
        }

        public String getVod_actor() {
            return vod_actor;
        }
    }

    public class SwiperItem {
        private String banner_title;
        private String banner_subTitle;
        // 0  vodid  1 topicId 3 url
        private int banner_jumpType;
        private String banner_pic;
        private String banner_link;

        public String getBanner_title() {
            return banner_title;
        }

        public String getBanner_subTitle() {
            return banner_subTitle;
        }

        public int getBanner_jumpType() {
            return banner_jumpType;
        }

        public String getBanner_pic() {
            return banner_pic;
        }

        public String getBanner_link() {
            return banner_link;
        }
    }

}

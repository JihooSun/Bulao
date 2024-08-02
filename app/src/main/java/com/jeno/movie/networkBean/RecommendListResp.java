package com.jeno.movie.networkBean;

import java.util.List;

/**
 {
 "code": 200,
 "msg": "成功",
 "data": {
 "code": 1,
 "msg": "数据列表",
 "page": 1,
 "pagecount": 1,
 "limit": 999,
 "total": 3,
 "list": [
 {
 "topic_id": 3,
 "topic_name": "电影热门推荐",
 "topic_type": "recommend2",
 "topic_rel_vod": "28090,28091,28093,28097,28099,28100",
 "vod_list": [
 {
 "vod_id": 28091,
 "type_id": 9,
 "vod_name": "天降兔仙闯皇庭",
 "vod_sub": "",
 "vod_class": "玄幻,青春,动漫",
 "vod_pic": "https://0img.hitv.com/preview/sp_images/2023/03/03/202303030949198204269.jpg",
 "vod_pic_thumb": "",
 "vod_pic_slide": "https://3img.hitv.com/preview/sp_images/2023/03/03/202303030949261268931.jpg",
 "vod_actor": "未知",
 "vod_blurb": "天上的兔仙苏萌萌被陷害而死，不甘心的她在神龙的帮助下历劫修炼再返天庭！等等，为何神龙把她变成了一个小女孩？等等，那个太后为何会是仇人的分身？在这个地狱模式下，她真的能够活下来吗？",
 "vod_remarks": "更新至3集",
 "vod_score": "8.0",
 "vod_content": "天上的兔仙苏萌萌被陷害而死，不甘心的她在神龙的帮助下历劫修炼再返天庭！等等，为何神龙把她变成了一个小女孩？等等，那个太后为何会是仇人的分身？在这个地狱模式下，她真的能够活下来吗？"
 },
 */
public class RecommendListResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private PageData data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public PageData getData() {
        return data;
    }

    public class PageData {
        private int limit;
        private int total;
        private int pagecount;
        private List<VodItem> list;

        public int getLimit() {
            return limit;
        }

        public int getTotal() {
            return total;
        }

        public int getPage() {
            return pagecount;
        }

        public List<VodItem> getList() {
            return list;
        }
    }

    public class VodItem {
        private int topic_id;
        private String topic_name;
        private String topic_type;
        private List<VodItemBean> vod_list;

        public int getTopic_id() {
            return topic_id;
        }

        public String getTopic_name() {
            return topic_name;
        }

        public String getTopic_type() {
            return topic_type;
        }

        public List<VodItemBean> getVod_list() {
            return vod_list;
        }
    }

}

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
 "pagecount": 3303,
 "limit": 20,
 "total": 66050,
 "list": [
 {
 "vod_id": 93941,
 "type_id": 3,
 "vod_name": "乘风破浪的姐姐 越南语字幕版",
 "vod_sub": "",
 "vod_class": "明星,真人秀,选秀,综艺",
 "vod_pic": "https://pic.rmb.bdstatic.com/bjh/6a42e562d57e2f51b69ee42c5d0ee5ea.jpeg",
 "vod_pic_thumb": "",
 "vod_pic_slide": "",
 "vod_actor": "未知",
 "vod_blurb": "《乘风破浪的姐姐》，30位30 的女艺人，通过3个月的培训与比赛，在专业制作团队的帮助和观众的投票选拔中，最终优选成团出道。节目是有史以来，首个为30岁以上的女艺人提供舞台，为全民打造逆龄音乐女团的节",
 "vod_remarks": "更新至2020-09-04期",
 "vod_score": "10.0",
 "vod_content": "《乘风破浪的姐姐》，30位30的女艺人，通过3个月的培训与比赛，在专业制作团队的帮助和观众的投票选拔中，最终优选成团出道。节目是有史以来，首个为30岁以上的女艺人提供舞台，为全民打造逆龄音乐女团的节目。"
 },
 */
public class VodHotResp {
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
        private Integer pagecount;
        private List<VodItemBean> list;

        public Integer getPagecount() {
            return pagecount;
        }

        public List<VodItemBean> getList() {
            return list;
        }
    }

}

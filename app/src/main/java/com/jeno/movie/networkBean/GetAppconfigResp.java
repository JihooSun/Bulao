package com.jeno.movie.networkBean;

import java.util.List;

/**
 {
 "code": 200,
 "msg": "成功",
 "data": [
 {
 "id": 1,
 "configKey": "share_logo",
 "configValue": "www.baidu.com",
 "open_status": 1
 },
 {
 "id": 2,
 "configKey": "share_url",
 "configValue": "www.baidu.com2",
 "open_status": 1
 },
 {
 "id": 3,
 "configKey": "is_required",
 "configValue": "0",
 "open_status": 1
 },
 {
 "id": 5,
 "configKey": "url",
 "configValue": "www.baidu.com",
 "open_status": 1
 },
 {
 "id": 6,
 "configKey": "registerd",
 "configValue": "{\"status\":\"1\",\"title\":\"公告\",\"content\":\"如遇到软件卡顿或者播放卡顿，请前往浏览器打开：sz66.app下载手指影视使用。带给你更好的流畅播放体验！\",\"type\":\"registerd\"}",
 "open_status": 1
 },
 {
 "id": 7,
 "configKey": "roll_notice",
 "configValue": "{\"status\":\"1\",\"title\":null,\"content\":\"如长时间无法播放请点击换来源，如遇卡顿请点击修复，如没有来源可换，请联系客服进行添加播放来源！\",\"type\":\"roll_notice\"}",
 "open_status": 1
 },
 {
 "id": 8,
 "configKey": "hot_key",
 "configValue": "[\"哈利波特\",\"X战警\",\"亮剑\",\"众神之战\",\"鬼吹灯\",\"蜀山\"]",
 "open_status": 1
 }
 ]
 }
 */
public class GetAppconfigResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private List<ConfigData> data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<ConfigData> getData() {
        return data;
    }


    public class ConfigData {
        private int id;
        private String configKey;
        private String configValue;
        private int open_status;

        public int getId() {
            return id;
        }

        public String getConfigKey() {
            return configKey;
        }

        public String getConfigValue() {
            return configValue;
        }

        public int getOpen_status() {
            return open_status;
        }
    }
}

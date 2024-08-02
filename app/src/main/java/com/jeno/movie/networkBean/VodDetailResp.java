package com.jeno.movie.networkBean;

import java.util.List;

/**
 * {
 *     "code": 200,
 *     "msg": "success",
 *     "data": "nlyE6POd6xKDtjW8PuOAYrin6MeQXQpkJu4eI8K8mZETanHCarEPQ0U7OQfmnvKvWkZWODT9DpE04InXZBYqQcPH9NWTuU0FhAk1qBJgUS97TdREbYvGuApokEb4ZH6l7auTKwSeX/ns1dCQ8gti1RshWlSdddnifXBPqiOXlLU="
 * }
 */
public class VodDetailResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private vodDetail data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public vodDetail getData() {
        return data;
    }

    public class vodDetail{
        private int vod_id;
        private int type_id;
        private String vod_name;
        private String vod_sub;
        private String vod_class;
        private String vod_pic;
        private String vod_pic_slide;
        private String vod_actor;
        private String vod_blurb;
        private String vod_remarks;
        private String vod_score;
        private String vod_content;
        private String vod_play_from;
        private String vod_year;
        private String vod_area;
        private List<VodPlayList> vod_play_list;

        public int getVod_id() {
            return vod_id;
        }

        public int getType_id() {
            return type_id;
        }

        public String getVod_name() {
            return vod_name;
        }

        public String getVod_sub() {
            return vod_sub;
        }

        public String getVod_class() {
            return vod_class;
        }

        public String getVod_pic() {
            return vod_pic;
        }

        public String getVod_pic_slide() {
            return vod_pic_slide;
        }

        public String getVod_actor() {
            return vod_actor;
        }

        public String getVod_blurb() {
            return vod_blurb;
        }

        public String getVod_remarks() {
            return vod_remarks;
        }

        public String getVod_score() {
            return vod_score;
        }

        public String getVod_content() {
            return vod_content;
        }

        public String getVod_play_from() {
            return vod_play_from;
        }

        public String getVod_year() {
            return vod_year;
        }

        public String getVod_area() {
            return vod_area;
        }

        public List<VodPlayList> getVod_play_list() {
            return vod_play_list;
        }
    }

    public class VodPlayList {
        private int url_count;
        private PlayerInfo player_info;
        private List<URL> urls;

        public int getUrl_count() {
            return url_count;
        }

        public PlayerInfo getPlayer_info() {
            return player_info;
        }

        public List<URL> getUrls() {
            return urls;
        }
    }

    public class PlayerInfo {
        private String from;
        private String show;
        private String playHead;

        public String getFrom() {
            return from;
        }

        public String getShow() {
            return show;
        }

        public String getPlayHead() {
            return playHead;
        }
    }

    public class URL {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}



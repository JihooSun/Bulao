package com.jeno.movie.networkBean;

import java.util.List;

/**
 {"code":200,"msg":"成功","data":"2023-09-03"}
 */
public class GetBarragesResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private List<Data> data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<Data> getData() {
        return data;
    }

    public class Data {
        private String vodId;
        private String content;
        private long timeOffset;
        public boolean isAdd = false;

        public String getVodId() {
            return vodId;
        }

        public String getContent() {
            return content;
        }

        public long getTimeOffset() {
            return timeOffset;
        }
    }

}

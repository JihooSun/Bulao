package com.jeno.movie.networkBean;

/**
 {"code":200,"msg":"success","data":true}
 */
public class GetApkParse1Resp {
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
        private String url;

        public String getUrl() {
            return url;
        }
    }
}

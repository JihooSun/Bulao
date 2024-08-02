package com.jeno.movie.networkBean;

/**
 {"code":200,"msg":"成功","data":{"vipPorid":"2023-09-03","isVip":1}}
 */
public class GetMemberValidityResp {
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
        private int isVip;
        private String vipPorid;

        public int getIsVip() {
            return isVip;
        }

        public String getVipPorid() {
            return vipPorid;
        }
    }
}

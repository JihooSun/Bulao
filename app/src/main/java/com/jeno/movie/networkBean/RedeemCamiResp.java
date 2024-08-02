package com.jeno.movie.networkBean;

/**
 {"code":200,"msg":"成功","data":"2023-09-03"}
 */
public class RedeemCamiResp {
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
        private String validityTime;
        private int leaveTryCount;

        public String getValidityTime() {
            return validityTime;
        }

        public int getLeaveTryCount() {
            return leaveTryCount;
        }
    }

}

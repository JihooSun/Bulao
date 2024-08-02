package com.jeno.movie.networkBean;

/**
 {"code":200,"msg":"success","data":"sdasd"}
 */
public class GetBaseURLResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private String data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }
}

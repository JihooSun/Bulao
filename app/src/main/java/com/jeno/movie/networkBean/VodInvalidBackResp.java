package com.jeno.movie.networkBean;

/**
 {"code":200,"msg":"success","data":true}
 */
public class VodInvalidBackResp {
    private String TAG = "OKHTTP";
    private int code;
    private String msg;
    private boolean data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean getData() {
        return data;
    }
}

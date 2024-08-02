package com.jeno.movie.networkBean;

/**
 {"code":200,"msg":"success","data":true}
 */
public class GetApkParse2Resp {
    private String TAG = "OKHTTP";
    private int status;
    private String info;

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
}

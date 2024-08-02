package com.jeno.movie.networkBean;

/**
 {
 "code": 200,
 "msg": "成功",
 "data": [
 {
 "id": 1,
 "nami": "月会员",
 "price": "0.01",
 "link_img": null,
 "type": 0
 },
 */
public class GetSResp {
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

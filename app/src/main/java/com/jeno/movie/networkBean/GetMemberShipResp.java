package com.jeno.movie.networkBean;

import java.util.List;

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
public class GetMemberShipResp {
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
        private int id;
        private String name;
        private String price;
        private String link_img;
        private int type;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public String getLink_img() {
            return link_img;
        }

        public int getType() {
            return type;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price='" + price + '\'' +
                    ", link_img='" + link_img + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}

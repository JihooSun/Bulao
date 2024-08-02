package com.jeno.movie.networkBean;

import java.util.List;

/**
 * {
 *     "code": 200,
 *     "msg": "success",
 *     "data": {
 *         "list": [
 *             {
 *                 "type_id": 1,
 *                 "type_name": "电影",
 *                 "type_en": "dianying",
 *                 "type_sort": 1,
 *                 "type_mid": 1,
 *                 "type_pid": 0,
 *                 "type_status": 1,
 *                 "type_extend": {
 *                     "class": "喜剧,动作,爱情,惊悚,犯罪,冒险,科幻,悬疑,剧情,动画,武侠,战争,歌舞,奇幻,传记,警匪,历史,运动,灾难,西部,魔幻,枪战,恐怖,记录",
 *                     "area": "大陆,美国,香港,韩国,英国,台湾,日本,法国,意大利,德国,西班牙,泰国,其它",
 *                     "lang": "国语,英语,粤语,闽南语,韩语,日语,法语,德语,其它",
 *                     "year": "2021,2020,2019,2018,2017,2016,2015,2014,2013,2012,2011,2010,2009,2008,2006,2005,2004",
 *                     "star": "王宝强,黄渤,周迅,周冬雨,范冰冰,陈学冬,陈伟霆,郭采洁,邓超,成龙,葛优,林正英,张家辉,梁朝伟,徐峥,郑恺,吴彦祖,刘德华,周星驰,林青霞,周润发,李连杰,甄子丹,古天乐,洪金宝,姚晨,倪妮,黄晓明,彭于晏,汤唯,陈小春",
 *                     "director": "冯小刚,张艺谋,吴宇森,陈凯歌,徐克,王家卫,姜文,周星驰,李安",
 *                     "state": "正片,预告片,花絮",
 *                     "version": "高清版,剧场版,抢先版,OVA,TV,影院版"
 *                 }
 *             },
 */
public class MacTypesResp {
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
        private List<TypeItem> list;

        public List<TypeItem> getList() {
            return list;
        }
    }

    public class TypeItem {
        private int type_id;
        private String type_name;
        private String type_en;
        private TypeExtend type_extend;

        public int getType_id() {
            return type_id;
        }

        public String getType_name() {
            return type_name;
        }

        public String getType_en() {
            return type_en;
        }

        public TypeExtend getType_extend() {
            return type_extend;
        }
    }

    public class TypeExtend {

        private String type_class;
        private String area;
        private String lang;
        private String year;
        private String star;

        public String getClasstype() {
            return type_class;
        }

        public String getArea() {
            return area;
        }

        public String getLang() {
            return lang;
        }

        public String getYear() {
            return year;
        }

        public String getStar() {
            return star;
        }

    }
}

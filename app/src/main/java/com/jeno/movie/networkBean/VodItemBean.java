package com.jeno.movie.networkBean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class VodItemBean implements Parcelable {
    private int vod_id;
    private int type_id;
    private String vod_name;
    private String vod_sub;
    private String vod_class;
    private String vod_pic;
    private String vod_pic_thumb;
    private String vod_pic_slide;
    private String vod_actor;
    private String vod_blurb;
    private String vod_remarks;
    private String vod_score;
    private String vod_content;

    public int getVod_id() {
        return vod_id;
    }

    public int getType_id() {
        return type_id;
    }

    public String getVod_name() {
        return vod_name;
    }

    public String getVod_sub() {
        return vod_sub;
    }

    public String getVod_class() {
        return vod_class;
    }

    public String getVod_pic() {
        return vod_pic;
    }

    public String getVod_pic_thumb() {
        return vod_pic_thumb;
    }

    public String getVod_pic_slide() {
        return vod_pic_slide;
    }

    public String getVod_actor() {
        return vod_actor;
    }

    public String getVod_blurb() {
        return vod_blurb;
    }

    public String getVod_remarks() {
        return vod_remarks;
    }

    public String getVod_score() {
        return vod_score;
    }

    public String getVod_content() {
        return vod_content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(vod_id);
        dest.writeInt(type_id);
        dest.writeString(vod_name);
        dest.writeString(vod_sub);
        dest.writeString(vod_class);
        dest.writeString(vod_pic);
        dest.writeString(vod_pic_thumb);
        dest.writeString(vod_pic_slide);
        dest.writeString(vod_actor);
        dest.writeString(vod_blurb);
        dest.writeString(vod_remarks);
        dest.writeString(vod_score);
        dest.writeString(vod_content);
    }

    protected VodItemBean(Parcel in) {
        vod_id = in.readInt();
        type_id = in.readInt();
        vod_name = in.readString();
        vod_sub = in.readString();
        vod_class = in.readString();
        vod_pic = in.readString();
        vod_pic_thumb = in.readString();
        vod_pic_slide = in.readString();
        vod_actor = in.readString();
        vod_blurb = in.readString();
        vod_remarks = in.readString();
        vod_score = in.readString();
        vod_content = in.readString();
    }

    public static final Creator<VodItemBean> CREATOR = new Creator<VodItemBean>() {
        @Override
        public VodItemBean createFromParcel(Parcel in) {
            return new VodItemBean(in);
        }

        @Override
        public VodItemBean[] newArray(int size) {
            return new VodItemBean[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "{vod_name:" + vod_name + "}";
    }
}
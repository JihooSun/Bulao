package com.jeno.movie.networkBean;

import android.os.Parcel;
import android.os.Parcelable;

public class VodRankDetailItem implements Parcelable {
    private int vod_id;
    private String vod_name;
    private int vod_status;
    private String vod_letter;
    private String vod_pic;
    private String vod_remarks;
    private long vod_time;
    private String vod_version;
    private int vod_level;
    private String vod_actor;

    protected VodRankDetailItem(Parcel in) {
        vod_id = in.readInt();
        vod_name = in.readString();
        vod_status = in.readInt();
        vod_letter = in.readString();
        vod_pic = in.readString();
        vod_remarks = in.readString();
        vod_time = in.readLong();
        vod_version = in.readString();
        vod_level = in.readInt();
        vod_actor = in.readString();
    }

    public static final Creator<VodRankDetailItem> CREATOR = new Creator<VodRankDetailItem>() {
        @Override
        public VodRankDetailItem createFromParcel(Parcel in) {
            return new VodRankDetailItem(in);
        }

        @Override
        public VodRankDetailItem[] newArray(int size) {
            return new VodRankDetailItem[size];
        }
    };

    public int getVod_id() {
        return vod_id;
    }

    public String getVod_name() {
        return vod_name;
    }

    public int getVod_status() {
        return vod_status;
    }

    public String getVod_letter() {
        return vod_letter;
    }

    public String getVod_pic() {
        return vod_pic;
    }

    public String getVod_remarks() {
        return vod_remarks;
    }

    public long getVod_time() {
        return vod_time;
    }

    public String getVod_version() {
        return vod_version;
    }

    public int getVod_level() {
        return vod_level;
    }

    public String getVod_actor() {
        return vod_actor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(vod_id);
        dest.writeString(vod_name);
        dest.writeInt(vod_status);
        dest.writeString(vod_letter);
        dest.writeString(vod_pic);
        dest.writeString(vod_remarks);
        dest.writeLong(vod_time);
        dest.writeString(vod_version);
        dest.writeInt(vod_level);
        dest.writeString(vod_actor);
    }

    @Override
    public String toString() {
        return "VodDetailItem{" +
                "vod_id=" + vod_id +
                ", vod_name='" + vod_name + '\'' +
                ", vod_status=" + vod_status +
                ", vod_letter='" + vod_letter + '\'' +
                ", vod_pic='" + vod_pic + '\'' +
                ", vod_remarks='" + vod_remarks + '\'' +
                ", vod_time=" + vod_time +
                ", vod_version='" + vod_version + '\'' +
                ", vod_level=" + vod_level +
                '}';
    }
}
package com.jeno.movie.network;

public interface DownloadListener {

    void onStart();//下载开始

    void onFinish(int count);//下载完成

    void onFail(String errorInfo);//下载失败

}

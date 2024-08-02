package com.jeno.movie.network;

import android.os.Environment;
import android.util.Log;

import com.jeno.movie.BuildConfig;
import com.jeno.movie.constant.SpConstant;
import com.jeno.movie.networkBean.GetBaseURLResp;
import com.jeno.movie.utils.SPUtils;
import com.jeno.movie.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UrlDataSource {
    private String TAG = "UrlDataSource";
    private static String baseUrl = "http://47.106.251.246:5566/";
    private static UrlDataSource urlDataSource;
    private static Retrofit retrofit;
    private static DataSource dataSource;
    private static int sBufferSize = 8192;

    private UrlDataSource() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .proxySelector(new ProxySelector() {
                    @Override
                    public List<Proxy> select(URI uri) {
                        return Collections.singletonList(Proxy.NO_PROXY);
                    }

                    @Override
                    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                    }
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别

            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }

        // 缓存目录
        File file = new File(Environment.getDataDirectory(), "a_cache");
        // 缓存大小
        int cacheSize = 10 * 1024 * 1024;

        OkHttpClient client = builder.cache(new Cache(file, cacheSize)).build();
        String spBaseUrl = SPUtils.getInstance().getString(SpConstant.GET_URL_ADDRESS);
        if (!StringUtil.isEmpty(spBaseUrl)) {
            baseUrl = spBaseUrl;
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataSource = retrofit.create(DataSource.class);
    }

    public static UrlDataSource getInstance() {
        if (urlDataSource != null) {
            return urlDataSource;
        }else {
            urlDataSource = new UrlDataSource();
            return urlDataSource;
        }
    }

    public void getRzysBaseURL(Callback callback) {
        if (callback == null) {
            Log.i(TAG, "getRzysBaseURL callback is null,return");
            return;
        }
        Log.i(TAG, "getAdvert");
        Call<GetBaseURLResp> call = dataSource.getRzysBaseURList();
        call.enqueue(callback);
    }
}

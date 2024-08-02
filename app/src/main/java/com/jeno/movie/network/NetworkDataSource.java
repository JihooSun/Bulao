package com.jeno.movie.network;

import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jeno.movie.BuildConfig;
import com.jeno.movie.constant.SpConstant;
import com.jeno.movie.networkBean.DetailRecommendListResp;
import com.jeno.movie.networkBean.GetAdvertResp;
import com.jeno.movie.networkBean.GetAppconfigResp;
import com.jeno.movie.networkBean.GetBarragesResp;
import com.jeno.movie.networkBean.GetListByIdsResp;
import com.jeno.movie.networkBean.GetMacTopicAllResp;
import com.jeno.movie.networkBean.GetMacTopicDetailResp;
import com.jeno.movie.networkBean.GetMemberShipResp;
import com.jeno.movie.networkBean.GetMemberValidityResp;
import com.jeno.movie.networkBean.GetParseResp;
import com.jeno.movie.networkBean.GetSResp;
import com.jeno.movie.networkBean.MacDetailResp;
import com.jeno.movie.networkBean.MacTypesResp;
import com.jeno.movie.networkBean.RecommendListResp;
import com.jeno.movie.networkBean.RedeemCamiResp;
import com.jeno.movie.networkBean.SearchResp;
import com.jeno.movie.networkBean.SwiperResp;
import com.jeno.movie.networkBean.UrgeUpdateResp;
import com.jeno.movie.networkBean.VodDetailResp;
import com.jeno.movie.networkBean.VodHotResp;
import com.jeno.movie.utils.ContextUtils;
import com.jeno.movie.utils.DeviceUuidFactory;
import com.jeno.movie.utils.RSAUtils;
import com.jeno.movie.utils.SPUtils;
import com.jeno.movie.utils.StringUtil;
import com.jeno.movie.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
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

public class NetworkDataSource {
    private String TAG = "NetworkDataSource";
    private static String baseUrl = "http://www.rzys.cc/";
    private static List<String> testUrlList = new ArrayList<>(Arrays.asList("http://www.rzys.cc/", "http://rzys.xyz/", "http://rzys666.xyz/", "http://bzys1.xyz/", "http://bzys2.xyz/"));
    private static NetworkDataSource urlDataSource;
    private static Retrofit retrofit;
    private static DataSource dataSource;
    private static int sBufferSize = 8192;
    public static MutableLiveData<Integer> errorTimes = new MutableLiveData<>(0);
    public static boolean errDialogIsShow = false;
    private static OkHttpClient client;
    private int OS = 2;

    private NetworkDataSource() {
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
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

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

        client = builder.cache(new Cache(file, cacheSize)).build();

        if (BuildConfig.DEBUG) {
            String debugIP = SPUtils.getInstance().getString(SpConstant.DEBUG_IP);
            if (!StringUtil.isEmpty(debugIP)) {
                baseUrl = debugIP;
                ToastUtils.showLongToast("当前访问的为调试IP：" + baseUrl);
            }
        }
        baseUrl = testUrlList.get(SPUtils.getInstance().getInt(SpConstant.CUR_TEST_IP, 0));
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataSource = retrofit.create(DataSource.class);
    }

    public void changeTestUrl(int pos) {
        baseUrl = testUrlList.get(pos);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataSource = retrofit.create(DataSource.class);
    }

    public static NetworkDataSource getInstance() {
        if (urlDataSource != null) {
            return urlDataSource;
        }else {
            urlDataSource = new NetworkDataSource();
            return urlDataSource;
        }
    }

    public void getAdvert(Callback callback) {
        if (callback == null) {
            Log.i(TAG, "getAdvert callback is null,return");
            return;
        }
        Log.i(TAG, "getAdvert");
        Call<GetAdvertResp> call = dataSource.getAdvert(RSAUtils.getS(),OS, DeviceUuidFactory.getDeviceUuid().toString());
        call.enqueue(callback);
    }

    public void getMacTypes(Callback callback) {
        if (callback == null) {
            Log.i(TAG, "getMacTypes callback is null,return");
            return;
        }
        Log.i(TAG, "getMacTypes");
        Call<MacTypesResp> call = dataSource.macTypes(RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getSwiper(Callback callback) {
        if (callback == null) {
            Log.i(TAG, "getSwiper callback is null,return");
            return;
        }
        Log.i(TAG, "getSwiper");
        Call<SwiperResp> call = dataSource.getSwiper(RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getRecommendList(Callback callback, Integer page, Integer limit) {
        if (callback == null) {
            Log.i(TAG, "getRecommendList callback is null,return");
            return;
        }
        Log.i(TAG, "getRecommendList");
        Call<RecommendListResp> call = dataSource.getRecommendList(page, limit, RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getVodDetail(Callback callback, String vodId) {
        if (callback == null) {
            Log.i(TAG, "getVodDetail callback is null,return");
            return;
        }
        Log.i(TAG, "getVodDetail");
        Call<VodDetailResp> call = dataSource.getVodDetail(vodId, RSAUtils.getS(), DeviceUuidFactory.getDeviceUuid().toString(),OS);
        call.enqueue(callback);
    }

    public void getSearchList(Callback callback, String searchKey, int page) {
        if (callback == null) {
            Log.i(TAG, "getSearchList callback is null,return");
            return;
        }
        Log.i(TAG, "getSearchList");
        Call<SearchResp> call = dataSource.getSearchList(searchKey, page, 12, RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getMacDetailList(Callback callback, String classtype, String area, String year, String typeid, int page) {
        if (callback == null) {
            Log.i(TAG, "getSearchList callback is null,return");
            return;
        }
        Log.i(TAG, "getMacDetailList");
        Call<MacDetailResp> call = dataSource.getMacDetailList(classtype, area, year, typeid, page, 30, RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getDetailRecommendList(Callback callback, int id) {
        if (callback == null) {
            Log.i(TAG, "getDetailRecommendList callback is null,return");
            return;
        }
        Log.i(TAG, "getDetailRecommendList");
        Call<DetailRecommendListResp> call = dataSource.getDetailRecommendList(id, RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getVodHotList(Callback callback, int typeid, int page) {
        if (callback == null) {
            Log.i(TAG, "getDetailRecommendList callback is null,return");
            return;
        }
        Log.i(TAG, "getVodHotList");
        Call<VodHotResp> call = dataSource.getVodHotList(typeid, 12, page, RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getListByIds(Callback callback, String ids) {
        if (callback == null) {
            Log.i(TAG, "GetListByIds callback is null,return");
            return;
        }
        Call<GetListByIdsResp> call = dataSource.getListByIds(ids, RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void urgeUpdate(Callback callback, String id) {
        if (callback == null) {
            Log.i(TAG, "urgeUpdate callback is null,return");
            return;
        }
        Call<UrgeUpdateResp> call = dataSource.urgeUpdate(id, RSAUtils.getS(),OS);
        call.enqueue(callback);
    }



    public void getMacTopicAll(Callback callback) {
        if (callback == null) {
            Log.i(TAG, "getMacTopicAll callback is null,return");
            return;
        }
        Call<GetMacTopicAllResp> call = dataSource.getMacTopicAll(RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getMacTopicDetail(Callback callback, Integer topicId) {
        if (callback == null) {
            Log.i(TAG, "getMacTopicDetail callback is null,return");
            return;
        }
        Call<GetMacTopicDetailResp> call = dataSource.getMacTopicDetail(topicId, RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getParse(Callback callback,String url, String from, int skip) {
        if (callback == null) {
            Log.i(TAG, "version callback is null,return");
            return;
        }
        url = RSAUtils.decryptLongRSA(url);
        Call<GetParseResp> call = dataSource.getParse(url, from, ContextUtils.getVersionCode()+"", skip, System.currentTimeMillis(), RSAUtils.getS(),OS);
        call.enqueue(callback);
    }

    public void getAppConfig(Callback callback) {
        if (callback == null) {
            Log.i(TAG, "getAppConfig callback is null,return");
            return;
        }
        Call<GetAppconfigResp> call = dataSource.getAppconfig(RSAUtils.getS(),OS, DeviceUuidFactory.getDeviceUuid().toString(), ContextUtils.getVersionCode()+"");
        call.enqueue(callback);
    }

    public void getMembership(Callback callback) {
        if (callback == null) {

            Log.i(TAG, "getMembership callback is null,return");
            return;
        }
        Call<GetMemberShipResp> call = dataSource.getMembership(RSAUtils.getS(), OS);
        call.enqueue(callback);
    }

    public void checkMemberValidity(Callback callback) {
        if (callback == null) {
            Log.i(TAG, "checkMemberValidity callback is null,return");
            return;
        }
        //Call<GetMemberValidityResp> call = dataSource.checkMemberValidity("12345", RSAUtils.getS(), 2);
        Call<GetMemberValidityResp> call = dataSource.checkMemberValidity(DeviceUuidFactory.getDeviceUuid().toString(), RSAUtils.getS(), OS);
        call.enqueue(callback);
    }

    public void redeemCami(Callback callback, String cami) {
        if (callback == null) {
            Log.i(TAG, "redeemCami callback is null,return");
            return;
        }
        Call<RedeemCamiResp> call = dataSource.redeemCami(DeviceUuidFactory.getDeviceUuid().toString(), cami, RSAUtils.getS(), OS);
        call.enqueue(callback);
    }


    public void getBarrages(Callback callback, String vodId, String vodSerial, int totalTime) {
        if (callback == null) {
            Log.i(TAG, "getBarrages callback is null,return");
            return;
        }
        Call<GetBarragesResp> call = dataSource.getBarrages(vodId, vodSerial, totalTime, RSAUtils.getS(), OS);
        call.enqueue(callback);
    }

    public void getS(Callback callback) {
        if (callback == null) {
            Log.i(TAG, "getS callback is null,return");
            return;
        }
        Call<GetSResp> call = dataSource.getS(OS, ContextUtils.getVersionCode()+"");
        call.enqueue(callback);
    }
}

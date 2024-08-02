package com.jeno.movie;

import android.app.Activity;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.jeno.movie.constant.SpConstant;
import com.jeno.movie.network.NetworkDataSource;
import com.jeno.movie.networkBean.GetSResp;
import com.jeno.movie.utils.BackgroundTaskUtils;
import com.jeno.movie.utils.ContextUtils;
import com.jeno.movie.utils.RSAUtils;
import com.jeno.movie.utils.SPUtils;
import com.kongzue.dialogx.DialogX;

import java.util.Locale;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;
import me.jessyan.autosize.utils.AutoSizeLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApplication extends android.app.Application {
    private String TAG = "MovieApplication";

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        ContextUtils.setApplicationContext(this);
        AutoSizeConfig.getInstance()
                .setExcludeFontScale(true)
                //屏幕适配监听器
                .setOnAdaptListener(new onAdaptListener() {
                    @Override
                    public void onAdaptBefore(Object target, Activity activity) {
                        AutoSizeLog.d(String.format(Locale.ENGLISH, "%s onAdaptBefore!", target.getClass().getName()));
                    }

                    @Override
                    public void onAdaptAfter(Object target, Activity activity) {
                        AutoSizeLog.d(String.format(Locale.ENGLISH, "%s onAdaptAfter!", target.getClass().getName()));
                    }
                })
                .setLog(false)
                .setUseDeviceSize(true)
                .setBaseOnWidth(true);
        DialogX.init(this);
        initApplication();
    }


    private void initApplication() {
        BackgroundTaskUtils.post(new Runnable() {
            @Override
            public void run() {

                NetworkDataSource.getInstance().getS(new Callback<GetSResp>() {
                    @Override
                    public void onResponse(Call<GetSResp> call, Response<GetSResp> response) {
                        if (response.code() >= 400) {
                            Log.e(TAG, "连接服务器失败，请检查网络");
                            return;
                        }
                        String s = response.body().getData();
                        SPUtils.getInstance().put(SpConstant.S, RSAUtils.decryptLongRSA(s));
                    }

                    @Override
                    public void onFailure(Call<GetSResp> call, Throwable t) {

                    }
                });

            }

        });
    }

}

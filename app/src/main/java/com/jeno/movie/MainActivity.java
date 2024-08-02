package com.jeno.movie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.jeno.movie.constant.BroadConstant;
import com.jeno.movie.constant.SpConstant;
import com.jeno.movie.databinding.ActivityMainBinding;
import com.jeno.movie.network.NetworkDataSource;
import com.jeno.movie.networkBean.SearchResp;
import com.jeno.movie.service.MonitorService;
import com.jeno.movie.utils.BackgroundTaskUtils;
import com.jeno.movie.utils.MonitorUtils;
import com.jeno.movie.utils.SPUtils;
import com.jeno.movie.utils.StatusBarUtils;
import com.suke.widget.SwitchButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "MainActivity";
    private ActivityMainBinding activityMainBinding;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            activityMainBinding.lastTime.setText(intent.getStringExtra("time"));
            activityMainBinding.lastResult.setText(intent.getStringExtra("netData"));
        }
    };

    /**
     * 注册广播
     */
    private void registerBroadcast() {
        Log.i(TAG, "registerBroadcast");
        IntentFilter filter = new IntentFilter(BroadConstant.REFRESH_REQ_RESULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        registerBroadcast();
        startService(new Intent(this, MonitorService.class));

        activityMainBinding.lastTime.setText(MonitorUtils.date);
        activityMainBinding.lastResult.setText(MonitorUtils.reqResult);
        activityMainBinding.btnMusicSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton switchButton, boolean b) {
                MonitorUtils.needPlayMusicTip = b;
            }
        });
        activityMainBinding.btnReqNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityMainBinding.btnReqNow.setText("请求中...");
                BackgroundTaskUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        NetworkDataSource.getInstance().getSearchList(new Callback<SearchResp>() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onResponse(Call<SearchResp> call, Response<SearchResp> response) {
                                activityMainBinding.btnReqNow.setText("立即请求");
                                if (response.code() >= 400) {
                                    Log.e(TAG, "连接服务器失败，请检查网络");
                                    return;
                                }
                                SearchResp.SearchData searchData = response.body().getData();
                                long currMS = System.currentTimeMillis();
                                Date date = new Date(currMS); // 将时间戳转换为Date对象

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 定义日期格式
                                String formattedDate = sdf.format(date);

                                Intent intent = new Intent(BroadConstant.REFRESH_REQ_RESULT);
                                intent.putExtra("netData", searchData == null ? "错误，请求结果为空！" : searchData.toString());
                                intent.putExtra("time", formattedDate);
                                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);

                                MonitorUtils.date = formattedDate;
                                MonitorUtils.reqResult = searchData == null ? "错误，请求结果为空！" : searchData.toString();
                            }

                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onFailure(Call<SearchResp> call, Throwable throwable) {
                                activityMainBinding.btnReqNow.setText("立即请求");
                                long currMS = System.currentTimeMillis();
                                Date date = new Date(currMS); // 将时间戳转换为Date对象

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 定义日期格式
                                String formattedDate = sdf.format(date);

                                Intent intent = new Intent(BroadConstant.REFRESH_REQ_RESULT);
                                intent.putExtra("netData", "请求服务器失败，可能为网络原因，请手动检查服务器！报错信息：" + throwable.getMessage());
                                intent.putExtra("time", formattedDate);
                                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);

                                MonitorUtils.date = formattedDate;
                                MonitorUtils.reqResult = "请求服务器失败，可能为网络原因，请手动检查服务器！报错信息：" + throwable.getMessage();
                            }
                        }, "周星驰", 1);
                    }
                });
            }
        });
        int pos = SPUtils.getInstance().getInt(SpConstant.CUR_TEST_IP, 0);
        switch (pos) {
            case 0:
                activityMainBinding.radioBtn1.setChecked(true);
                break;
            case 1:
                activityMainBinding.radioBtn2.setChecked(true);
                break;
            case 2:
                activityMainBinding.radioBtn3.setChecked(true);
                break;
            case 3:
                activityMainBinding.radioBtn4.setChecked(true);
                break;
            case 4:
                activityMainBinding.radioBtn5.setChecked(true);
                break;
        }
        activityMainBinding.radioBtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NetworkDataSource.getInstance().changeTestUrl(0);
                    SPUtils.getInstance().put(SpConstant.CUR_TEST_IP, 0);
                }
            }
        });
        activityMainBinding.radioBtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NetworkDataSource.getInstance().changeTestUrl(1);
                    SPUtils.getInstance().put(SpConstant.CUR_TEST_IP, 1);
                }
            }
        });
        activityMainBinding.radioBtn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NetworkDataSource.getInstance().changeTestUrl(2);
                    SPUtils.getInstance().put(SpConstant.CUR_TEST_IP, 2);
                }
            }
        });
        activityMainBinding.radioBtn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NetworkDataSource.getInstance().changeTestUrl(3);
                    SPUtils.getInstance().put(SpConstant.CUR_TEST_IP, 3);
                }
            }
        });
        activityMainBinding.radioBtn5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NetworkDataSource.getInstance().changeTestUrl(4);
                    SPUtils.getInstance().put(SpConstant.CUR_TEST_IP, 4);
                }
            }
        });
    }

    private void initDataBinding() {
        StatusBarUtils.makeStatusBarTransparent(this);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {

    }
}

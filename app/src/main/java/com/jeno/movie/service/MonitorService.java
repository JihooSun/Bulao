package com.jeno.movie.service;

import static android.app.Notification.FLAG_ONGOING_EVENT;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.jeno.movie.MainActivity;
import com.jeno.movie.R;
import com.jeno.movie.constant.BroadConstant;
import com.jeno.movie.network.NetworkDataSource;
import com.jeno.movie.networkBean.SearchResp;
import com.jeno.movie.utils.BackgroundTaskUtils;
import com.jeno.movie.utils.MonitorUtils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonitorService extends Service{
    private String TAG = "MonitorService";
    private Notification notification;
    private NotificationManager notificationManager;
    private ScheduledExecutorService scheduledExecutorService;
    PowerManager pm;
    PowerManager.WakeLock wakeLock;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "assi:wakeTag");
        wakeLock.acquire();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotification();
        }


        scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                BackgroundTaskUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        NetworkDataSource.getInstance().getSearchList(new Callback<SearchResp>() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onResponse(Call<SearchResp> call, Response<SearchResp> response) {
                                if (response.code() >= 400) {
                                    Log.e(TAG, "连接服务器失败，请检查网络");
                                    refreshNotification("网络请求失败" ,false);
                                    playTishiMusic();
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
                                LocalBroadcastManager.getInstance(MonitorService.this).sendBroadcast(intent);

                                MonitorUtils.date = formattedDate;
                                MonitorUtils.reqResult = searchData == null ? "错误，请求结果为空！" : searchData.toString();

                                if (searchData == null || searchData.getSearchItems() == null || searchData.getSearchItems().size() == 0) {
                                    refreshNotification(formattedDate ,false);
                                    playTishiMusic();
                                }else {
                                    refreshNotification(formattedDate ,true);
                                }
                            }

                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onFailure(Call<SearchResp> call, Throwable throwable) {
                                long currMS = System.currentTimeMillis();
                                Date date = new Date(currMS); // 将时间戳转换为Date对象

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 定义日期格式
                                String formattedDate = sdf.format(date);

                                Intent intent = new Intent(BroadConstant.REFRESH_REQ_RESULT);
                                intent.putExtra("netData", "请求服务器失败，可能为网络原因，请手动检查服务器！报错信息：" + throwable.getMessage());
                                intent.putExtra("time", formattedDate);
                                LocalBroadcastManager.getInstance(MonitorService.this).sendBroadcast(intent);

                                MonitorUtils.date = formattedDate;
                                MonitorUtils.reqResult = "请求服务器失败，可能为网络原因，请手动检查服务器！报错信息：" + throwable.getMessage();

                                refreshNotification("http_fail" ,false);
                            }
                        }, "周星驰", 1);
                    }
                });

            }
        },2,60, TimeUnit.SECONDS);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isBetween2AMand6AM() {
        LocalTime currentTime = LocalTime.now();
        LocalTime start = LocalTime.of(2, 0); // 2:00 AM
        LocalTime end = LocalTime.of(6, 0); // 6:00 AM

        return !currentTime.isBefore(start) && !currentTime.isAfter(end);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void playTishiMusic() {
        if (!MonitorUtils.needPlayMusicTip) {
            return;
        }
        if (isBetween2AMand6AM()) {
            return;
        }

        BackgroundTaskUtils.post(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mediaPlayer = MediaPlayer.create(MonitorService.this, R.raw.tip);
                mediaPlayer.start();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotification(){
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("BulaoFloatView","补牢监控",NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(notificationChannel);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notification = new NotificationCompat.Builder(this,"BulaoFloatView")
                .setAutoCancel(false)
                .setContentTitle("补牢监控")
                .setContentText("补牢监控正在运行中...")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_heartbeat)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_heartbeat))
                .setContentIntent(pendingIntent)
                //在build()方法之前还可以添加其他方法
                .build();
        notification.flags |= FLAG_ONGOING_EVENT;
        notificationManager.notify(2, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void refreshNotification(String time, boolean result){
        String content;
        if (time.equals("http_fail")) {
            content = "请求服务器失败，可能为网络原因，请手动检查服务器！";
        }else {
            if (result) {
                content = "服务器运行正常，测试时间：" + time;
            }else {
                content = "警告！服务器异常！服务器异常！测试时间：" + time;
            }
        }
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("BulaoFloatView","补牢监控",NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(notificationChannel);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notification = new NotificationCompat.Builder(this,"BulaoFloatView")
                .setAutoCancel(false)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_heartbeat)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_heartbeat))
                .setContentIntent(pendingIntent)
                .build();
        notification.flags |= FLAG_ONGOING_EVENT;
        notificationManager.notify(2, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        if (notificationManager != null) {
            notificationManager.cancel(2);
        }
        if (wakeLock!=null) {
            wakeLock.release();
        }

    }

}

package com.jeno.movie.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class BackgroundTaskUtils {

    public static final HandlerThread WORKER_THREAD = new HandlerThread("BackgroundTaskUtils");

    static {
        WORKER_THREAD.start();
    }

    public static final HandlerThread WORKER_THREAD2 = new HandlerThread("BackgroundTaskUtils2");

    static {
        WORKER_THREAD2.start();
    }

    private static final Looper LOOPER = WORKER_THREAD.getLooper() != null ? WORKER_THREAD.getLooper() : Looper.getMainLooper();
    private static final Handler WORKER = new Handler(LOOPER);
    public static Handler getBackgroundHandler() {
        return WORKER;
    }

    private static final Looper LOOPER2 = WORKER_THREAD2.getLooper() != null ? WORKER_THREAD2.getLooper() : Looper.getMainLooper();
    private static final Handler WORKER2 = new Handler(LOOPER2);
    public static Handler getBackgroundHandler2() {
        return WORKER2;
    }

    public static Handler getMainHandler() {
        return MAIN_HANDLER;
    }

    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static void post(Runnable task) {
        WORKER.post(task);
    }

    public static void postDelayed(Runnable task, long delayMillis) {
        WORKER.postDelayed(task, delayMillis);
    }

    public static void post2(Runnable task) {
        WORKER2.post(task);
    }

    public static void postDelayed2(Runnable task, long delayMillis) {
        WORKER2.postDelayed(task, delayMillis);
    }

    public static void postMainDelayed(Runnable task, long delayMillis) {
        MAIN_HANDLER.postDelayed(task, delayMillis);
    }

    public static boolean isMainThread()
    {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }


}

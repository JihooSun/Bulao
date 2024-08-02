package com.jeno.movie.utils;

import android.widget.Toast;

public class ToastUtils {
    static public void showLongToast(String str) {
        Toast.makeText(ContextUtils.getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}

package com.jeno.movie.utils;

public class StringUtil {
    public static boolean isEmpty(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }else {
            return false;
        }
    }
}

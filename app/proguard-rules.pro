# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-ignorewarnings

# 视频播放器
# 视频内核
-keep class com.yc.kernel.**{*;}
# 视频播放器
-keep class com.yc.video.**{*;}
# 视频缓存
-keep class com.yc.videocache.**{*;}
# 视频悬浮view
-keep class com.yc.videoview.**{*;}
# 视频位置记录
-keep class com.yc.videosqllite.**{*;}
# 视频m3u8分片下载和合成
-keep class com.yc.m3u8.**{*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# keep the class and specified members from being removed or renamed
-keep class com.jeno.movie.utils.SPUtils { *; }

# keep everything in this package from being removed or renamed
-keep class com.jeffmony.** { *; }

# keep everything in this package from being removed or renamed
-keep class com.jeno.movie.dbModule.** { *; }

# keep everything in this package from being removed or renamed
-keep class com.jeno.movie.db.** { *; }

# keep everything in this package from being removed or renamed
-keep class com.jeno.movie.networkBean.** { *; }

# keep everything in this package from being removed or renamed
-keep class com.bykv.** { *; }

# keep everything in this package from being removed or renamed
-keep class com.byted.** { *; }

# keep everything in this package from being removed or renamed
-keep class com.bytedance.** { *; }

# keep everything in this package from being removed orcsj renamed
-keep class com.ss.** { *; }

-keep class aegon.chrome.** { *; }

-keep class com.badlogic.** { *; }

-keep class com.google.** { *; }

-keep class com.kuaishou.** { *; }
-keep class com.kwad.** { *; }
-keep class com.kwai.** { *; }
-keep class com.yxcorp.** { *; }
-keep class com.asus.** { *; }
-keep class com.bun.** { *; }
-keep class com.huawei.** { *; }
-keep class com.netease.** { *; }
-keep class com.samsung.** { *; }
-keep class com.zui.** { *; }
-keep class com.qq.** { *; }
-keep class com.youxiao.** { *; }



package com.jeno.movie.utils;

import android.content.Context;

import com.google.android.exoplayer2.util.Log;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;


/**
 * @author Mr.Zheng
 * @date 2014年8月22日 下午1:44:23
 */
public final class RSAUtils
{

    static {
        System.loadLibrary("movie");
    }

    private static String TAG = "RSAUtils";

    /*android客户端用这个*/
    private static String RSA = "RSA/ECB/PKCS1Padding";

    private static Context mContext = ContextUtils.getApplicationContext();

    private static native String gp(Context context, int version);

    private static native String gs(Context context, String t, String v);

    private static String KEY = gk();

    public static boolean showReal = false;

    private static final int MAX_DECRYPT_BLOCK = 128;
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    private static String gk() {
        String k = gp(mContext, getVersion());
        k = k.replace('-', 's');
        return k;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersion() {
        Log.i(TAG, "version:" + ContextUtils.getVersionCode());
        return ContextUtils.getVersionCode();
    }

    public static String getS() {
        return encrypt(gs(ContextUtils.getApplicationContext(),String.valueOf(System.currentTimeMillis() / 1000),getVersion()+""));
    }

    public static String decryptLongRSA(String resString) {
        if (KEY.equals("error2")) {
            Map<String, String> attributes = new HashMap<String, String>();
            attributes.put("reason", "error2");
            return "error2";
        }

        if (KEY.equals("error")) {
            Map<String, String> attributes = new HashMap<String, String>();
            attributes.put("reason", "error");
            return "error";
        }
        byte[] resByteList = Base64.decode(resString);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] plainText;
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(KEY));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            int inputLen = resByteList.length;
            int offSet = 0;
            int i = 0;
            byte[] cache;
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(resByteList, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(resByteList, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            plainText = out.toByteArray();
            out.close();

        }catch (Exception e) {
            return "error";
        }
        if (plainText == null) {
            return "error";
        }else {
            return new String(plainText);
        }
    }


    /**
     * 得到私钥
     *
     * @param key
     *            密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        return publicKey;
    }


    /**
     * RSA加密
     *
     * @param data      待加密数据
     */
    public static String encrypt(String data) {
        byte[] encryptedData = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(KEY));
            int inputLen = data.getBytes().length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_ENCRYPT_BLOCK;
            }
            encryptedData = out.toByteArray();
            out.close();
        }catch (Exception e) {
            return "error";
        }
        return Base64.encode(encryptedData);
    }

}

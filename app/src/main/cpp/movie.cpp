#include <jni.h>
#include <string>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("movie");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("movie")
//      }
//    }

jint getT(JNIEnv *env, jobject context_object, jclass context_class) {

    jclass clazz;

    clazz = env->FindClass("com/jeno/movie/utils/SPUtils");
    if (clazz == NULL)
        return -1;

    jmethodID gTmedthodId = env->GetStaticMethodID(clazz, "gT", "()I");
    if (gTmedthodId == NULL)
        return -1;

    return env->CallStaticIntMethod(clazz, gTmedthodId);
}

jstring getS(JNIEnv *env) {

    jclass clazz;

    clazz = env->FindClass("com/jeno/movie/utils/SPUtils");
    if (clazz == NULL)
        return (jstring) "";

    jmethodID gTmedthodId = env->GetStaticMethodID(clazz, "gS", "()Ljava/lang/String;");
    if (gTmedthodId == NULL)
        return (jstring) "";

    return static_cast<jstring>(env->CallStaticObjectMethod(clazz, gTmedthodId));
}

void addT(JNIEnv *env, jobject context_object, jclass context_class) {

    jclass clazz;

    clazz = env->FindClass("com/jeno/movie/utils/SPUtils");
    if (clazz == NULL)
        return;

    jmethodID aTmedthodId = env->GetStaticMethodID(clazz, "aT", "()V");
    if (aTmedthodId == NULL)
        return;

    env->CallStaticVoidMethod(clazz, aTmedthodId);
}

void resetT(JNIEnv *env, jobject context_object, jclass context_class) {

    jclass clazz;

    clazz = env->FindClass("com/jeno/movie/utils/SPUtils");
    if (clazz == NULL)
        return;

    jmethodID aTmedthodId = env->GetStaticMethodID(clazz, "resetT", "()V");
    if (aTmedthodId == NULL)
        return;

    env->CallStaticVoidMethod(clazz, aTmedthodId);
}



/**
 * 获取PackageManager
 */
jobject getPackageManager(JNIEnv *env, jobject context_object, jclass context_class) {

    jmethodID methodId = env->GetMethodID(context_class, "getPackageManager",
                                          "()Landroid/content/pm/PackageManager;");
    return env->CallObjectMethod(context_object, methodId);
}

/**
 * 获取getPackageName
 */
jstring getPackageName(JNIEnv *env, jclass context_class, jobject context_object) {
    jmethodID methodId = env->GetMethodID(context_class, "getPackageName", "()Ljava/lang/String;");
    jstring packageName = (jstring) env->CallObjectMethod(context_object, methodId);
    return packageName;
}

/**
 * 获取PackageInfo对象
 */
jobject getPackageInfo(JNIEnv *env, jobject package_manager, jstring package_name) {
    jclass pack_manager_class = env->GetObjectClass(package_manager);
    jmethodID methodId = env->GetMethodID(pack_manager_class, "getPackageInfo",
                                          "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    env->DeleteLocalRef(pack_manager_class);
    jobject package_info = env->CallObjectMethod(package_manager, methodId, package_name, 0x40);
    return package_info;
}


/**
 * 获取签名信息
 */
jobject getSignature(JNIEnv *env, jobject package_info) {
    jclass package_info_class = env->GetObjectClass(package_info);
    jfieldID fieldId = env->GetFieldID(package_info_class, "signatures",
                                       "[Landroid/content/pm/Signature;");
    env->DeleteLocalRef(package_info_class);
    jobjectArray signature_object_array = (jobjectArray) env->GetObjectField(package_info, fieldId);
    if (signature_object_array == NULL)
        return NULL;
    return env->GetObjectArrayElement(signature_object_array, 0);
}

jbyteArray getSHA1(JNIEnv *env, jobject signature_object) {
    //签名信息转换成sha1值
    jclass signature_class = env->GetObjectClass(signature_object);
    jmethodID methodId = env->GetMethodID(signature_class, "toByteArray", "()[B");
    env->DeleteLocalRef(signature_class);
    jbyteArray signature_byte = (jbyteArray) env->CallObjectMethod(signature_object, methodId);
    jclass byte_array_input_class = env->FindClass("java/io/ByteArrayInputStream");
    methodId = env->GetMethodID(byte_array_input_class, "<init>", "([B)V");
    jobject byte_array_input = env->NewObject(byte_array_input_class, methodId, signature_byte);
    jclass certificate_factory_class = env->FindClass("java/security/cert/CertificateFactory");
    methodId = env->GetStaticMethodID(certificate_factory_class, "getInstance",
                                      "(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;");
    jstring x_509_jstring = env->NewStringUTF("X.509");
    jobject cert_factory = env->CallStaticObjectMethod(certificate_factory_class, methodId,
                                                       x_509_jstring);
    methodId = env->GetMethodID(certificate_factory_class, "generateCertificate",
                                ("(Ljava/io/InputStream;)Ljava/security/cert/Certificate;"));
    jobject x509_cert = env->CallObjectMethod(cert_factory, methodId, byte_array_input);
    env->DeleteLocalRef(certificate_factory_class);
    jclass x509_cert_class = env->GetObjectClass(x509_cert);
    methodId = env->GetMethodID(x509_cert_class, "getEncoded", "()[B");
    jbyteArray cert_byte = (jbyteArray) env->CallObjectMethod(x509_cert, methodId);
    env->DeleteLocalRef(x509_cert_class);
    return cert_byte;
}
#define ALGORITHM_SHA1 "SHA1"

char *digest(JNIEnv *env, const char *algorithm, jbyteArray cert_byte) {
    jclass message_digest_class = env->FindClass("java/security/MessageDigest");
    jmethodID methodId = env->GetStaticMethodID(message_digest_class, "getInstance",
                                                "(Ljava/lang/String;)Ljava/security/MessageDigest;");
    jstring algorithm_jstring = env->NewStringUTF(algorithm);
    jobject digest = env->CallStaticObjectMethod(message_digest_class, methodId, algorithm_jstring);
    methodId = env->GetMethodID(message_digest_class, "digest", "([B)[B");

    jbyteArray sha1_byte = (jbyteArray) env->CallObjectMethod(digest, methodId, cert_byte);
    env->DeleteLocalRef(message_digest_class);

    //转换成char
    jsize array_size = env->GetArrayLength(sha1_byte);
    jbyte *sha1 = env->GetByteArrayElements(sha1_byte, NULL);
    char *hex = new char[array_size * 2 + 1]();
    for (int i = 0; i < array_size; ++i) {
        sprintf(hex + 2 * i, "%02x", (unsigned char) sha1[i]);
    }
//    LOGD("%s:%s", algorithm, hex);
    return hex;
}

#define SHA1 "b90cd8f1cfd177ff861252d3b130380c7fd0b515"

char* jstringToChar(JNIEnv* env, jstring jstr) {
    char* rtn = NULL;
    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("GB2312");
    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, strencode);
    jsize alen = env->GetArrayLength(barr);
    jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
    if (alen > 0) {
        rtn = (char*) malloc(alen + 1);
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    env->ReleaseByteArrayElements(barr, ba, 0);
    return rtn;

}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_jeno_movie_utils_RSAUtils_gs(JNIEnv *env, jclass clazz, jobject context_object, jstring time, jstring version) {
    char buff[100];
    memset(buff, 0, sizeof(buff));

    jstring salt = getS(env);
    char* saltChar = jstringToChar(env, salt);
    if(saltChar != nullptr && strlen(saltChar) == 0) {
        saltChar = "rzys875";
    } else if(saltChar == nullptr)
    {
        saltChar = "rzys875";
    }

    strcpy(buff, saltChar);
    strcat(buff, env->GetStringUTFChars(time, NULL));
    strcat(buff, env->GetStringUTFChars(version, NULL));
    return env->NewStringUTF(buff);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_jeno_movie_utils_RSAUtils_gp(JNIEnv *env, jclass clazz, jobject context_object, jint version) {
    std::string key = "MIGdMA0GCS?GSIb3DQEBAQUAA4GLADCBhwKBgQCAliLnnNy2iPebSiw-fSlc1dV2\n"
                      "gyQZjBVVAACMLmFa4-vzF2X-M26dQ2A?XjDIMurDn8EfrevDMu?5TG?IFCaawEYd\n"
                      "zWQFE3IMmnxEkxieEK7Lr0o0BjSZ5NLcaTnoWL0/+To837i5XPhzaZWHy4d9G+Aa\n"
                      "J9MJDA3FBR5CQ7vi1wIBAw==";
//pkcs8
    std::string error = "error2";
    jclass context_class = env->GetObjectClass(context_object);
    bool allPass = true;

    int k = version % 10;
    version = version / 10;
    int j = version % 10;
    version = version / 10;
    int i = version % 10;

    if (((i*i)+(j*2)+(i*j*k))%11 != 0)
    {
        addT(env, context_object, context_class);
        allPass = false;
    }

    //反射获取PackageManager
    jobject package_manager = getPackageManager(env, context_object, context_class);
    if (package_manager == NULL)
        return env->NewStringUTF(error.c_str());

    //反射获取包名
    jstring package_name = getPackageName(env, context_class, context_object);
    if (package_name == NULL)
        return env->NewStringUTF(error.c_str());
    env->DeleteLocalRef(context_class);

    const char* packageName = env->GetStringUTFChars(package_name, NULL);

    if (strcmp(packageName, "com.jeno.bulao") != 0)
    {
        addT(env, context_object, context_class);
        allPass = false;
    }

    if (allPass) {
        resetT(env, context_object, context_class);
    }

    jint time = getT(env, context_object, context_class);
    if (time > 20)
    {
        return env->NewStringUTF("error");
    }

    //获取PackageInfo对象
    jobject package_info = getPackageInfo(env, package_manager, package_name);
    if (package_info == NULL)
        return env->NewStringUTF(error.c_str());
    env->DeleteLocalRef(package_manager);

    //获取签名信息
    jobject signature_object = getSignature(env, package_info);
    if (signature_object == NULL)
        return env->NewStringUTF(error.c_str());
    env->DeleteLocalRef(package_info);
    jbyteArray cert_byte = getSHA1(env, signature_object);


    char *hex_sha = digest(env, ALGORITHM_SHA1, cert_byte);

   /* if (strcmp(hex_sha, SHA1) != 0) {
      return env->NewStringUTF(error.c_str());
    }*/
    std::replace(key.begin(), key.end(), '?', 'q');

    return env->NewStringUTF(key.c_str());
}


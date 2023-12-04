#include <jni.h>
#include "string"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_linhtetko_network_utils_NativeStore_baseUrlFromJNI(JNIEnv *env, jobject thiz){
    std::string  baseUrl= "https://api.weatherapi.com/v1/";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_linhtetko_network_utils_NativeStore_apiKeyFromJNI(JNIEnv *env, jobject thiz){
    std::string apiKey = "b58cabed85c14d8491e142059230112";
    return env->NewStringUTF(apiKey.c_str());
}
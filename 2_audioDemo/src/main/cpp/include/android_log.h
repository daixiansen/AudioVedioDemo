#ifndef _ANDROID_LOG_
#define _ANDROID_LOG_

#include <android/log.h>

#define LOG_TAG  "AudioDemo-JNI"
#define   LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#endif

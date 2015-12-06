
package com.example.anhtuan.bnn.utils;

import android.util.Log;


public class LogUtils {

    private static final String LOG_TAG_API = "dogtelligen_api";

    private static final String LOG_TAG_APPLI = "dogtelligen";

    private static boolean mIsShowLog = true;

    public static void setShowLog(boolean isShowLog) {
        mIsShowLog = isShowLog;
    }

    public static void logApi() {
        outputLog(Log.INFO, LOG_TAG_API, null, null);
    }

    public static void logApi(String responseString) {
        int index = 0;
        int max = responseString.length();
//        while (index + 100 < max) {
//            Log.d("", "" + responseString.substring(index, index + 100));
//            index += 100;
//        }
        Log.d(LOG_TAG_API, "" + responseString.substring(0, max));
//        Logger.debug("", responseString.substring(index));
    }

    public static void logVerbose() {
        outputLog(Log.VERBOSE, LOG_TAG_APPLI, null, null);
    }

    public static void logVerbose(String message) {
        outputLog(Log.VERBOSE, LOG_TAG_APPLI, message, null);
    }

    public static void logVerbose(String message, Throwable throwable) {
        outputLog(Log.VERBOSE, LOG_TAG_APPLI, message, throwable);
    }

    public static void logDebug() {
        outputLog(Log.DEBUG, LOG_TAG_APPLI, null, null);
    }

    public static void logDebug(String message) {
        outputLog(Log.DEBUG, LOG_TAG_APPLI, message, null);
    }

    public static void logDebug(String message, Throwable throwable) {
        outputLog(Log.DEBUG, LOG_TAG_APPLI, message, throwable);
    }

    public static void logInfo() {
        outputLog(Log.INFO, LOG_TAG_APPLI, null, null);
    }

    public static void logInfo(String message) {
        outputLog(Log.INFO, LOG_TAG_APPLI, message, null);
    }

    public static void logInfo(String message, Throwable throwable) {
        outputLog(Log.INFO, LOG_TAG_APPLI, message, throwable);
    }

    public static void logWarning() {
        outputLog(Log.WARN, LOG_TAG_APPLI, null, null);
    }

    public static void logWarning(String message) {
        outputLog(Log.WARN, LOG_TAG_APPLI, message, null);
    }

    public static void logWarning(String message, Throwable throwable) {
        outputLog(Log.WARN, LOG_TAG_APPLI, message, throwable);
    }

    public static void logError() {
        outputLog(Log.ERROR, LOG_TAG_APPLI, null, null);
    }

    public static void logError(String message) {
        outputLog(Log.ERROR, LOG_TAG_APPLI, message, null);
    }

    public static void logError(String message, Throwable throwable) {
        outputLog(Log.ERROR, LOG_TAG_APPLI, message, throwable);
    }

    private static void outputLog(int type, String tag, String message, Throwable throwable) {
        if (!mIsShowLog) {
            return;
        }

        if (message == null) {
            message = getStackTraceInfo();
        } else {
            message = getStackTraceInfo() + message;
        }

        switch (type) {
            case Log.VERBOSE:
                if (throwable == null) {
                    Log.v(tag, message);
                } else {
                    Log.v(tag, message, throwable);
                }
                break;

            case Log.DEBUG:
                if (throwable == null) {
                    Log.d(tag, message);
                } else {
                    Log.d(tag, message, throwable);
                }
                break;

            case Log.INFO:
                if (throwable == null) {
                    Log.i(tag, message);
                } else {
                    Log.i(tag, message, throwable);
                }
                break;

            case Log.WARN:
                if (throwable == null) {
                    Log.w(tag, message);
                } else {
                    Log.w(tag, message, throwable);
                }
                break;

            case Log.ERROR:
                if (throwable == null) {
                    Log.e(tag, message);
                } else {
                    Log.e(tag, message, throwable);
                }
        }
    }

    private static String getStackTraceInfo() {
        StackTraceElement element = Thread.currentThread().getStackTrace()[5];

        String fullName = element.getClassName();
        String className = fullName.substring(fullName.lastIndexOf(".") + 1);
        String methodName = element.getMethodName();
        int lineNumber = element.getLineNumber();

        return "<<" + className + "#" + methodName + ":" + lineNumber + ">> ";
    }

}

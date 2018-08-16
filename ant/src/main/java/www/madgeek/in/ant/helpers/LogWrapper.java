package www.madgeek.in.ant.helpers;

import android.util.Log;

import com.danylovolokh.androidlogger.AndroidLogger;

import static www.madgeek.in.ant.utility.Constants.DEVELOPMENT_MODE;

/**
 * Created by Madgeek Pvt Ltd on 12/08/18.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public class LogWrapper {

    public static int v(final String TAG, final String message) {
        if (DEVELOPMENT_MODE)
            Log.v(TAG, message);
        return AndroidLogger.v(TAG, message);
    }

    public static int d(final String TAG, final String message) {
        if (DEVELOPMENT_MODE)
            Log.d(TAG, message);
        return AndroidLogger.d(TAG, message);
    }

    public static int inf(final String TAG, final String message) {
        if (DEVELOPMENT_MODE)
            Log.i(TAG, message);
        return AndroidLogger.i(TAG, message);
    }

    public static int w(final String TAG, final String message) {
        if (DEVELOPMENT_MODE)
            Log.w(TAG, message);
        return AndroidLogger.w(TAG, message);
    }

    public static int err(final String TAG, final String message) {
        if (DEVELOPMENT_MODE)
            Log.e(TAG, message);
        return AndroidLogger.e(TAG, message);
    }
}

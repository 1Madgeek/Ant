package www.madgeek.in.ant.core;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.danylovolokh.androidlogger.AndroidLogger;

import java.io.File;
import java.io.IOException;

import www.madgeek.in.ant.utility.AppConfig;

/**
 * Created by Madgeek Pvt Ltd on 11/08/18.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public class AppController extends Application {
    private RequestQueue mRequestQueue;
    private static AppController mInstance;
    public static final String TAG = AppController.class.getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        File logsDirectory = AndroidLogger.getDefaultLogFilesDirectory(this);
        int logFileMaxSizeBytes = 2 * 1024 * 1024; // 2Mb
        try {
            AndroidLogger.initialize(
                    this,
                    logsDirectory,
                    "Log_File_Name",
                    logFileMaxSizeBytes,
                    false
            );
        } catch (IOException e) {
            // Some error happened - most likely there is no free space on the system
        }
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_RETRY_TIME, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}

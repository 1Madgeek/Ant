package www.madgeek.in.ant.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import www.madgeek.in.ant.core.AppController;
import www.madgeek.in.ant.utility.AppConfig;


/**
 * Created by Madgeek Pvt Ltd on 03/12/16.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public class NetworkManager {

    private static NetworkManager instance = null;
    private Context context;


    private NetworkManager(Context context) {
        this.context = context;
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkManager getInstance() {
        if (null == instance) {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void StringRequest(final String tag, final HashMap<String, String> param, String url, final VolleyResponse<String> listener, int method) {


        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.error(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return param;
            }

            @Override
            public void setHeaders(Map<String, String> headers) {
                headers.put("Accept","application/json");
                super.setHeaders(headers);
            }
        };
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_RETRY_TIME, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request, tag);
    }

    public void RequestMultiPart(File file, String filename, final String reqUrl, String fileField, Map<String, String> params, final VolleyResponse<String> listener) {

        MultipartRequest imageUploadReq = new MultipartRequest(reqUrl, params, file, filename, fileField,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Multipart Request Url: ", reqUrl);
                        Log.d("Multipart ERROR", "error => " + error.toString());
                        listener.error(error);
                    }
                },
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MediaSent Response", response);
                        listener.success(response);

                    }
                }
        ) {

            /* The following method sets the cookies in the header, I needed it for my server
             but you might want to remove it if it is not useful in your case */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return new HashMap<>();
            }

        };

        imageUploadReq.setRetryPolicy(new DefaultRetryPolicy(AppConfig.DEFAULT_RETRY_TIME, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(imageUploadReq);
    }
}

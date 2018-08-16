package www.madgeek.in.ant.network;

import com.android.volley.error.VolleyError;

/**
 * Created by Madgeek Pvt Ltd on 03/12/16.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public interface VolleyResponse<T> {

    void success(String response);

    void error(VolleyError error);
}
